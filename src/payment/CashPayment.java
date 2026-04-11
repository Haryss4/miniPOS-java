package payment;

/**
 * Pembayaran tunai (Cash).
 * Extends abstract class PaymentMethod + implements interface Payable.
 */
public class CashPayment extends PaymentMethod implements Payable {

    private double cashGiven;

    public CashPayment(double cashGiven) {
        super("Cash");
        this.cashGiven = cashGiven;
    }

    @Override
    public boolean processPayment(double amount) {
        if (validatePayment(amount)) {
            double kembalian = cashGiven - amount;
            System.out.println("  [CASH] Uang diterima : Rp " + String.format("%,.0f", cashGiven));
            System.out.println("  [CASH] Kembalian     : Rp " + String.format("%,.0f", kembalian));
            return true;
        }
        return false;
    }

    @Override
    public boolean validatePayment(double amount) {
        if (cashGiven < amount) {
            System.out.println("  [CASH] ERROR: Uang tidak cukup! Kurang Rp "
                    + String.format("%,.0f", (amount - cashGiven)));
            return false;
        }
        return true;
    }

    @Override
    public String getPaymentDetails() {
        return "Cash | Dibayar: Rp " + String.format("%,.0f", cashGiven);
    }
}