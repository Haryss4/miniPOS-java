import exception.PaymentException;
import model.*;
import notification.Notifiable;
import payment.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Kelas utama POS System.
 */
public class POSSystem {

    private String storeName;
    private List<Transaction> transactionHistory;
    private List<Notifiable> notifiers;

    public POSSystem(String storeName) {
        this.storeName = storeName;
        this.transactionHistory = new ArrayList<>();
        this.notifiers = new ArrayList<>();
    }

    public void addNotifier(Notifiable notifier) {
        notifiers.add(notifier);
    }

    public void processTransaction(Transaction transaction,
                                   PaymentMethod payment,
                                   String customerContact,
                                   String customerName) throws PaymentException {

        System.out.println("\n▶ Memproses transaksi: " + transaction.getTransactionId());
        System.out.println("──────────────────────────────────────────");

        if (transaction.getTotalAmount() <= 0) {
            transaction.setStatus(PaymentStatus.FAILED);
            transactionHistory.add(transaction);
            throw new PaymentException("ERR_AMOUNT", "Total transaksi tidak valid: Rp 0");
        }

        boolean success = payment.processPayment(transaction.getTotalAmount());

        if (success) {
            transaction.setStatus(PaymentStatus.SUCCESS);
        } else {
            transaction.setStatus(PaymentStatus.FAILED);
            transactionHistory.add(transaction);
            throw new PaymentException("ERR_PAYMENT",
                    "Pembayaran gagal untuk transaksi " + transaction.getTransactionId());
        }

        transactionHistory.add(transaction);
        System.out.println();
        transaction.printReceipt();

        System.out.println("\n▶ Mengirim notifikasi...");
        String pesan = "Transaksi " + transaction.getTransactionId() +
                " sebesar Rp " + String.format("%,.0f", transaction.getTotalAmount()) +
                " berhasil. Terima kasih, " + customerName + "!";

        for (Notifiable notifier : notifiers) {
            System.out.println("  → Via " + notifier.getNotificationType() + ":");
            notifier.sendNotification(customerContact, pesan);
        }
    }

    public void printTransactionHistory() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         HISTORY TRANSAKSI                ║");
        System.out.println("╠══════════════════════════════════════════╣");
        if (transactionHistory.isEmpty()) {
            System.out.println("  Belum ada transaksi.");
        } else {
            for (Transaction t : transactionHistory) {
                System.out.println("  " + t);
            }
        }
        System.out.println("╚══════════════════════════════════════════╝");
    }

    public String getStoreName() { return storeName; }
}