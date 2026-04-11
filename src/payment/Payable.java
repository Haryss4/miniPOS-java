package payment;

/**
 * Interface untuk memastikan setiap pembayaran bisa divalidasi.
 */
public interface Payable {
    boolean validatePayment(double amount);
    String getPaymentDetails();
}