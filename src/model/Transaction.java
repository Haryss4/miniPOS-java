package model;

import payment.PaymentMethod;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * Model untuk sebuah transaksi.
 * Bonus: Transaction ID, Payment Status, Transaction History
 */
public class Transaction {

    private String transactionId;
    private List<Product> products;
    private double totalAmount;
    private double discount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private String timestamp;

    public Transaction(List<Product> products, double discount, PaymentMethod paymentMethod) {
        this.transactionId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.products = products;
        this.discount = discount;
        this.paymentMethod = paymentMethod;
        this.status = PaymentStatus.PENDING;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        this.totalAmount = calculateTotal();
    }

    private double calculateTotal() {
        double subtotal = products.stream().mapToDouble(Product::getSubtotal).sum();
        return subtotal - (subtotal * discount / 100);
    }

    public void printReceipt() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║         MINI POS SYSTEM - STRUK          ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("  ID Transaksi : " + transactionId);
        System.out.println("  Waktu        : " + timestamp);
        System.out.println("  Status       : " + status);
        System.out.println("──────────────────────────────────────────");
        System.out.println("  PRODUK:");
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println("──────────────────────────────────────────");
        double subtotal = products.stream().mapToDouble(Product::getSubtotal).sum();
        System.out.printf("  Subtotal     : Rp %,.0f%n", subtotal);
        if (discount > 0) {
            System.out.printf("  Diskon       : %.0f%% (- Rp %,.0f)%n", discount, subtotal * discount / 100);
        }
        System.out.printf("  TOTAL        : Rp %,.0f%n", totalAmount);
        System.out.println("──────────────────────────────────────────");
        System.out.println("  Pembayaran   : " + paymentMethod.getPaymentType());
        System.out.println("╚══════════════════════════════════════════╝");
    }

    public String getTransactionId() { return transactionId; }
    public double getTotalAmount()   { return totalAmount; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    @Override
    public String toString() {
        return transactionId + " | " + timestamp + " | Rp " +
                String.format("%,.0f", totalAmount) + " | " + status;
    }
}