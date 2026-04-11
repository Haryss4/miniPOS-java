package notification;

/**
 * Notifikasi via Push Notification (mobile app).
 */
public class PushNotification implements Notifiable {

    private String deviceToken;

    public PushNotification(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("  [PUSH] Device Token  : " + deviceToken);
        System.out.println("  [PUSH] Terkirim ke   : " + recipient);
        System.out.println("  [PUSH] Pesan         : " + message);
    }

    @Override
    public String getNotificationType() {
        return "Push Notification";
    }
}