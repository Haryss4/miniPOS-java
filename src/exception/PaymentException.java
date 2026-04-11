package exception;

/**
 * Custom exception untuk error saat proses pembayaran.
 */
public class PaymentException extends Exception {

    private String errorCode;

    public PaymentException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "[" + errorCode + "] " + getMessage();
    }
}