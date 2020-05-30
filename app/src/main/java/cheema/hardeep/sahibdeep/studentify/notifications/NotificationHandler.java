package cheema.hardeep.sahibdeep.studentify.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.util.Random;

import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.activities.HomeActivity;
import cheema.hardeep.sahibdeep.studentify.activities.HomeActivityKt;
import cheema.hardeep.sahibdeep.studentify.activities.TasksActivity;
import cheema.hardeep.sahibdeep.studentify.activities.TasksActivityKt;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;
import cheema.hardeep.sahibdeep.studentify.models.tables.TaskType;

public class NotificationHandler {

    private static final String CHANNEL_ID = "Studentify-Sahib-Channel";
    private static final String CHANNEL_NAME = "Studentify Notification Channel";
    private static final String CHANNEL_DESCRIPTION = "A channel to show reminder notification for Studentify application";

    private static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_DESCRIPTION);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLightColor(Color.BLUE);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public static void showStudentClassNotification(Context context, StudentClass studentClass) {
        showNotification(context,
                studentClass.getName(),
                getClassNotificationBody(studentClass.getProfessorName(), studentClass.getRoomNumber()),
                generateHomeActivityPendingIntent(context)
        );
    }

    public static void showTaskNotification(Context context, Task task) {
        showNotification(context,
                task.getName(),
                getTaskNotificationBody(task.getType(), task.getNotes()),
                generateTaskActivityPendingIntent(context, task.getStudentClassId())
        );
    }

    private static void showNotification(Context context, String title, String body, PendingIntent contentIntent) {
        createNotificationChannel(context);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setSmallIcon(R.drawable.alarm_clock_app_icon)
                .setContentIntent(contentIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(generateRandomNotificationId(), notification.build());
    }

    private static int generateRandomNotificationId() {
        return (int) System.currentTimeMillis();
    }

    private static PendingIntent generateHomeActivityPendingIntent(Context context) {
        return PendingIntent.getActivity(context, 0, HomeActivityKt.createHomeActivityIntent(context), 0);
    }

    private static PendingIntent generateTaskActivityPendingIntent(Context context, int taskClassId) {
        Intent homeActivityIntent = HomeActivityKt.createHomeActivityIntent(context);
        homeActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        Intent taskActivityIntent = TasksActivityKt.createTaskActivityIntent(context, taskClassId);

        return PendingIntent.getActivities(context, 0, new Intent[]{homeActivityIntent, taskActivityIntent}, 0);
    }

    private static String getClassNotificationBody(String professor, String roomNumber) {
        return "Your class with professor " + professor + " will start in 15 minutes, in room number " + roomNumber;
    }

    private static String getTaskNotificationBody(TaskType taskType, String notes) {
        return "Your " + taskType.name().toLowerCase() + " is due in 12 hours. \n" + notes;
    }
}
