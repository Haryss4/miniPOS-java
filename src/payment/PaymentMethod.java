package payment;

/**
 * Abstract class yang merepresentasikan metode pembayaran.
 * Setiap jenis pembayaran WAJIB mengimplementasikan processPayment().
 */
public abstract class PaymentMethod {

    protected String paymentType;

    public PaymentMethod(String paymentType) {
        this.paymentType = paymentType;
    }

    // Abstract method → wajib di-override oleh subclass
    public abstract boolean processPayment(double amount);

    // Concrete method yang bisa dipakai semua subclass
    public String getPaymentType() {
        return paymentType;
    }

    @Override
    public String toString() {
        return "Metode Pembayaran: " + paymentType;
    }
}