package cheema.hardeep.sahibdeep.studentify.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

public class NotificationProvider {

    //https://www.raywenderlich.com/1214490-android-notifications-tutorial-getting-started

    private static final String CHANNEL_ID = "Studentify-Sahib";
    private static final String CHANNEL_NAME = "Studentify Notification Channel";
    private static final String CHANNEL_DESCRIPTION = "A channel to show reminder notification for Studentify application";

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_DESCRIPTION);
            notificationChannel.setShowBadge(true);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
