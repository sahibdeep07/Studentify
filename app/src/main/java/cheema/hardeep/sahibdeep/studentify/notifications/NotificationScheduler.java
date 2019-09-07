package cheema.hardeep.sahibdeep.studentify.notifications;

import android.content.Context;
import android.util.Log;

import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;
import cheema.hardeep.sahibdeep.studentify.utils.DateUtils;

public class NotificationScheduler {

    private static final String NOTIFICATION_CLASS_WORKER = "notification-class-worker";
    private static final String NOTIFICATION_TASK_WORKER = "notification-task-worker";
    public static final String KEY_STUDENT_CLASS_ID = "key-student-class-id";
    public static final String KEY_TASK_ID = "key-task-id";

    private static final int STUDENT_CLASS_REMINDER_TIME_OFFSET = -15;
    private static final int TASK_REMINDER_TIME_OFFSET = -720;
    private static final int ONE_SECOND = 1000;
    private static final int ONE_DAY_HOURS = 24;
    private static final int SEVEN = 7;

    public static void scheduleClassNotification(Context context, StudentClass studentClass) {
        Log.d(NotificationScheduler.class.getSimpleName(), "Student Class Scheduling...");
        Data inputData = new Data.Builder()
                .putInt(KEY_STUDENT_CLASS_ID, studentClass.getId())
                .build();

//        for (String days : studentClass.getDays()
//             ) {
//
//            SimpleDateFormat sdf = new SimpleDateFormat("EEE, hh:mm a");
//            Date date = null;
//            try {
//                date = sdf.parse(days);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(date);
//        }


        long delay = calculateDelay(studentClass.getStartTime(), STUDENT_CLASS_REMINDER_TIME_OFFSET);
        PeriodicWorkRequest periodicWorkRequest =
                new PeriodicWorkRequest
                        .Builder(StudentifyWorker.class, SEVEN, TimeUnit.DAYS)
                        .setInitialDelay(delay, TimeUnit.SECONDS)
                        .setInputData(inputData)
                        .addTag(NOTIFICATION_CLASS_WORKER)
                        .build();

        WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(NOTIFICATION_CLASS_WORKER, ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest);
        Log.d(NotificationScheduler.class.getSimpleName(), "Student Class Scheduling Complete");
    }

    public static void scheduleTaskNotification(Context context, Task task) {
        Log.d(NotificationScheduler.class.getSimpleName(), "Task Scheduling...");
        Data inputData = new Data.Builder().putInt(KEY_TASK_ID, task.getId()).build();

        long initDelay = task.getDateTime().getTime() - System.currentTimeMillis();
        //How do we introduce last reminder offset?

        OneTimeWorkRequest oneTimeWorkRequest =
                new OneTimeWorkRequest
                        .Builder(StudentifyWorker.class)
                        .setInitialDelay(initDelay, TimeUnit.MILLISECONDS)
                        .setInputData(inputData)
                        .addTag(NOTIFICATION_TASK_WORKER)
                        .build();

        WorkManager.getInstance(context).enqueue(oneTimeWorkRequest);
        Log.d(NotificationScheduler.class.getSimpleName(), "Task Scheduling Complete");
    }

    /**
     * Worker runs with set intervals, for example everyday or every 7 days
     * In order to ensure that work runs at specific time then after that keep running with 7 days delay in between
     * we have to provide initial delay
     * For example
     * If current time is 5pm and we want to show notification 8pm every week on monday
     * - We need to make sure we have initial day of 3 hours (calculated by startTime - currentTime)
     * - Once that delay is over, the worker will run from that time every week
     */
    private static long calculateDelay(Date startTime, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.MINUTE, offset);

        long delta;
        if (calendar.getTime().getTime() < System.currentTimeMillis()) {
            calendar.add(Calendar.HOUR, ONE_DAY_HOURS);
            delta = (calendar.getTimeInMillis() - System.currentTimeMillis()) / ONE_SECOND;
        } else {
            delta = (calendar.getTimeInMillis() - System.currentTimeMillis()) / ONE_SECOND;
        }
        return delta;
    }
}
