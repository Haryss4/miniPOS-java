package notification;

/**
 * Notifikasi via Email.
 */
public class EmailNotification implements Notifiable {

    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("  [EMAIL] Terkirim ke  : " + recipient);
        System.out.println("  [EMAIL] Pesan        : " + message);
    }

    @Override
    public String getNotificationType() {
        return "Email";
    }
}