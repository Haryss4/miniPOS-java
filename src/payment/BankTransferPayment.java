package payment;

/**
 * Pembayaran via Transfer Bank.
 */
public class BankTransferPayment extends PaymentMethod implements Payable {

    private String bankName;
    private String accountNumber;
    private String accountHolder;

    public BankTransferPayment(String bankName, String accountNumber, String accountHolder) {
        super("Bank Transfer");
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
    }

    @Override
    public boolean processPayment(double amount) {
        if (validatePayment(amount)) {
            System.out.println("  [BANK] Bank          : " + bankName);
            System.out.println("  [BANK] No. Rekening  : " + accountNumber);
            System.out.println("  [BANK] Atas Nama     : " + accountHolder);
            System.out.println("  [BANK] Jumlah        : Rp " + String.format("%,.0f", amount));
            System.out.println("  [BANK] Status        : Menunggu konfirmasi transfer...");
            return true;
        }
        return false;
    }

    @Override
    public boolean validatePayment(double amount) {
        if (amount <= 0) {
            System.out.println("  [BANK] ERROR: Jumlah transfer tidak valid!");
            return false;
        }
        return true;
    }

    @Override
    public String getPaymentDetails() {
        return bankName + " | Rek: " + accountNumber + " (" + accountHolder + ")";
    }
}