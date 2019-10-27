package cheema.hardeep.sahibdeep.studentify.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.Calendar;

import cheema.hardeep.sahibdeep.studentify.models.DayTime;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;

import static android.content.Context.ALARM_SERVICE;
import static cheema.hardeep.sahibdeep.studentify.utils.DateUtils.getNextDayDate;

public class NotificationScheduler {

    public static final String KEY_STUDENT_CLASS_ID = "key-student-class-id";
    public static final String KEY_TASK_ID = "key-task-id";

    private static final int THOUSAND = 1000;
    private static final int SIXTY = 60;
    private static final int FIFTEEN = 15;
    private static final int TWELVE = 12;
    private static final int SEVEN = 7;
    private static final int STUDENT_CLASS_REMINDER_TIME_OFFSET = THOUSAND * SIXTY * FIFTEEN;
    private static final int TASK_REMINDER_TIME_OFFSET = THOUSAND * SIXTY * SIXTY * TWELVE;

    public static void scheduleClassNotification(Context context, StudentClass studentClass) {
        Log.d(NotificationScheduler.class.getSimpleName(), "Student Class Scheduling...");
        setupPackageManager(context);

        for (DayTime dayTime : studentClass.getDayTimes()) {
            Calendar dayTimeCalendar = getNextDayDate(dayTime);

            Intent intent = new Intent(context, NotificationReceiver.class);
            intent.putExtra(KEY_STUDENT_CLASS_ID, studentClass.getId());

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    dayTimeCalendar.getTimeInMillis() - STUDENT_CLASS_REMINDER_TIME_OFFSET,
                    AlarmManager.INTERVAL_DAY * SEVEN,
                    pendingIntent
            );
        }

        Log.d(NotificationScheduler.class.getSimpleName(), "Student Class Scheduling Complete");
    }

    public static void scheduleTaskNotification(Context context, Task task) {
        Log.d(NotificationScheduler.class.getSimpleName(), "Task Scheduling...");
        setupPackageManager(context);

        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra(KEY_TASK_ID, task.getId());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                task.getDateTime().getTime() - TASK_REMINDER_TIME_OFFSET,
                pendingIntent
        );

        Log.d(NotificationScheduler.class.getSimpleName(), "Task Scheduling Complete!");
    }

    private static void setupPackageManager(Context context) {
        context
                .getPackageManager()
                .setComponentEnabledSetting(
                        new ComponentName(context, NotificationReceiver.class),
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP
                );
    }
}
