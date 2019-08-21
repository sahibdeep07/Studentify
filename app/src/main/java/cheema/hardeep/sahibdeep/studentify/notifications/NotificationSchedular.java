package cheema.hardeep.sahibdeep.studentify.notifications;

import android.content.Context;

import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;

public class NotificationSchedular {

    private static final String NOTIFICATION_WORKER = "notification-worker";
    private static final String KEY_NOTIFICATION_TYPE = "key-notification-type";
    public static final String KEY_STUDENT_CLASS_ID = "key-student-class-id";
    public static final String KEY_TASK_ID = "key-task-id";
    private static final String TYPE_STUDENT_CLASS = "type-student-class";
    private static final String TYPE_TASK = "type-task";

    public static void scheduleClassNotification(Context context, StudentClass studentClass) {
        Data inputData = new Data.Builder()
                .putString(KEY_NOTIFICATION_TYPE, TYPE_STUDENT_CLASS)
                .putInt(KEY_STUDENT_CLASS_ID, studentClass.getId())
                .build();

        //Todo: Fix Time
        PeriodicWorkRequest periodicWorkRequest =
                new PeriodicWorkRequest
                        .Builder(NotificationWorker.class, 7, TimeUnit.DAYS)
                        .setInputData(inputData)
                        .addTag(NOTIFICATION_WORKER)
                        .build();

        WorkManager
                .getInstance(context)
                .enqueueUniquePeriodicWork(NOTIFICATION_WORKER, ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest);
    }

    public static void scheduleTaskNotification(Context context, Task task) {
        Data inputData = new Data.Builder()
                .putString(KEY_NOTIFICATION_TYPE, TYPE_TASK)
                .putInt(KEY_TASK_ID, task.getId())
                .build();

        //Todo: Fix Time
        PeriodicWorkRequest periodicWorkRequest =
                new PeriodicWorkRequest
                        .Builder(NotificationWorker.class, 7, TimeUnit.DAYS)
                        .setInputData(inputData)
                        .addTag(NOTIFICATION_WORKER)
                        .build();

        WorkManager
                .getInstance(context)
                .enqueueUniquePeriodicWork(NOTIFICATION_WORKER, ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest);
    }
}
