package payment;

/**
 * Pembayaran via E-Wallet (GoPay, OVO, Dana, dll).
 */
public class EWalletPayment extends PaymentMethod implements Payable {

    private String walletName;
    private String phoneNumber;
    private double balance;

    public EWalletPayment(String walletName, String phoneNumber, double balance) {
        super("E-Wallet");
        this.walletName = walletName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    @Override
    public boolean processPayment(double amount) {
        if (validatePayment(amount)) {
            balance -= amount;
            System.out.println("  [E-WALLET] Provider  : " + walletName);
            System.out.println("  [E-WALLET] No. HP    : " + phoneNumber);
            System.out.println("  [E-WALLET] Saldo sisa: Rp " + String.format("%,.0f", balance));
            return true;
        }
        return false;
    }

    @Override
    public boolean validatePayment(double amount) {
        if (balance < amount) {
            System.out.println("  [E-WALLET] ERROR: Saldo tidak cukup! Saldo: Rp "
                    + String.format("%,.0f", balance));
            return false;
        }
        return true;
    }

    @Override
    public String getPaymentDetails() {
        return walletName + " (" + phoneNumber + ") | Saldo: Rp " + String.format("%,.0f", balance);
    }
}