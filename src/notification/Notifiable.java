package notification;

/**
 * Interface untuk semua jenis notifikasi.
 * Setiap notifier WAJIB mengimplementasikan sendNotification().
 */
public interface Notifiable {
    void sendNotification(String recipient, String message);
    String getNotificationType();
}