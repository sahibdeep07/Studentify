package cheema.hardeep.sahibdeep.studentify.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;
import cheema.hardeep.sahibdeep.studentify.models.tables.TaskType;

import static cheema.hardeep.sahibdeep.studentify.notifications.NotificationScheduler.KEY_STUDENT_CLASS_ID;
import static cheema.hardeep.sahibdeep.studentify.notifications.NotificationScheduler.KEY_TASK_ID;

public class NotificationReceiver extends BroadcastReceiver {

    private static final int NEGATIVE_ONE = -1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(NotificationReceiver.class.getSimpleName(), "Notification Data Received...");

        int classId = intent.getIntExtra(KEY_STUDENT_CLASS_ID, NEGATIVE_ONE);
        int taskId = intent.getIntExtra(KEY_TASK_ID, NEGATIVE_ONE);

        if (classId != NEGATIVE_ONE) {
            handleStudentClassWork(context, classId);
        } else if (taskId != NEGATIVE_ONE) {
            handleTaskWork(context, taskId);
        }
        Log.d(StudentifyWorker.class.getSimpleName(), "Notification Data Processed!");
    }

    private void handleStudentClassWork(Context context, int classId) {
        StudentClass studentClass = StudentifyDatabaseProvider
                .getStudentClassDao(context)
                .getStudentClass(classId);

        NotificationHandler.showStudentClassNotification(context, studentClass);
    }

    private void handleTaskWork(Context context, int taskId) {
        Task task = StudentifyDatabaseProvider
                .getTaskDao(context.getApplicationContext())
                .getTask(taskId);

        if (task.getType() == TaskType.HOMEWORK) {
            StudentifyDatabaseProvider
                    .getStudentClassDao(context.getApplicationContext())
                    .updateStudentClassFinishedHomework(task.getStudentClassId());
        } else if (task.getType() == TaskType.TEST) {
            StudentifyDatabaseProvider
                    .getStudentClassDao(context.getApplicationContext())
                    .updateStudentClassCompletedTest(task.getStudentClassId());
        }

        NotificationHandler.showTaskNotification(context, task);
    }
}
