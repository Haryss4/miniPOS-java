import exception.PaymentException;
import model.*;
import notification.*;
import payment.*;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       MINI POS SYSTEM — OOP DEMO         ║");
        System.out.println("╚══════════════════════════════════════════╝");

        POSSystem pos = new POSSystem("Toko Serba Ada");

        // Daftarkan semua notifier (polymorphism via interface Notifiable)
        pos.addNotifier(new EmailNotification());
        pos.addNotifier(new SMSNotification());
        pos.addNotifier(new PushNotification("device-token-abc123"));

        // TRANSAKSI 1 — Cash + diskon 10%
        System.out.println("\n════════ TRANSAKSI 1 — CASH (Diskon 10%) ════════");
        List<Product> produk1 = Arrays.asList(
                new Product("Nasi Goreng Spesial", 25000, 2),
                new Product("Es Teh Manis", 5000, 2)
        );
        Transaction tx1 = new Transaction(produk1, 10.0, new CashPayment(70000));
        try {
            pos.processTransaction(tx1, new CashPayment(70000), "budi@email.com", "Budi");
        } catch (PaymentException e) {
            System.out.println("  ✗ Gagal: " + e);
        }

        // TRANSAKSI 2 — E-Wallet
        System.out.println("\n════════ TRANSAKSI 2 — E-WALLET (GoPay) ════════");
        List<Product> produk2 = Arrays.asList(
                new Product("Laptop Stand", 150000, 1),
                new Product("Mouse Wireless", 85000, 1)
        );
        Transaction tx2 = new Transaction(produk2, 0, new EWalletPayment("GoPay", "0812-3456-7890", 300000));
        try {
            pos.processTransaction(tx2, new EWalletPayment("GoPay", "0812-3456-7890", 300000), "0812-3456-7890", "Siti");
        } catch (PaymentException e) {
            System.out.println("  ✗ Gagal: " + e);
        }

        // TRANSAKSI 3 — Bank Transfer + diskon 5%
        System.out.println("\n════════ TRANSAKSI 3 — BANK TRANSFER ════════");
        List<Product> produk3 = Arrays.asList(
                new Product("Kopi Arabika 500g", 120000, 1),
                new Product("Tumbler Stainless", 75000, 2)
        );
        Transaction tx3 = new Transaction(produk3, 5.0, new BankTransferPayment("BCA", "1234567890", "Ahmad"));
        try {
            pos.processTransaction(tx3, new BankTransferPayment("BCA", "1234567890", "Ahmad"), "+6281234567890", "Ahmad");
        } catch (PaymentException e) {
            System.out.println("  ✗ Gagal: " + e);
        }

        // TRANSAKSI 4 — E-Wallet GAGAL (saldo kurang)
        System.out.println("\n════════ TRANSAKSI 4 — E-WALLET GAGAL ════════");
        List<Product> produk4 = Arrays.asList(
                new Product("TV 55 inch", 8500000, 1)
        );
        Transaction tx4 = new Transaction(produk4, 0, new EWalletPayment("OVO", "0899-9999-9999", 500000));
        try {
            pos.processTransaction(tx4, new EWalletPayment("OVO", "0899-9999-9999", 500000), "dewi@email.com", "Dewi");
        } catch (PaymentException e) {
            System.out.println("  ✗ Transaksi Gagal: " + e);
        }

        // History semua transaksi
        pos.printTransactionHistory();

        System.out.println("\n✔ Program selesai. Terima kasih!\n");
    }
}