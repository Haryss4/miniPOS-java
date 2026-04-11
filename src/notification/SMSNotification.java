package notification;

/**
 * Notifikasi via SMS.
 */
public class SMSNotification implements Notifiable {

    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("  [SMS] Terkirim ke    : " + recipient);
        System.out.println("  [SMS] Pesan          : " + message);
    }

    @Override
    public String getNotificationType() {
        return "SMS";
    }
}