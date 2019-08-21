package cheema.hardeep.sahibdeep.studentify.notifications;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;

import static cheema.hardeep.sahibdeep.studentify.notifications.NotificationSchedular.KEY_STUDENT_CLASS_ID;
import static cheema.hardeep.sahibdeep.studentify.notifications.NotificationSchedular.KEY_TASK_ID;

public class NotificationWorker extends Worker {

    private static final int NEGATIVE_ONE = -1;

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data inputData = getInputData();
        int classId = inputData.getInt(KEY_STUDENT_CLASS_ID, NEGATIVE_ONE);
        int taskId = inputData.getInt(KEY_TASK_ID, NEGATIVE_ONE);

        if (classId != NEGATIVE_ONE) {
            handleStudentClassWork(classId);
        } else if (taskId != NEGATIVE_ONE) {
            handleTaskWork(taskId);
        }
        return Result.success();
    }

    private void handleTaskWork(int taskId) {
        Task task = StudentifyDatabaseProvider
                .getTaskDao(getApplicationContext())
                .getTask(taskId);

        NotificationHandler.showTaskNotification(getApplicationContext(), task);
    }

    private void handleStudentClassWork(int classId) {
        StudentClass studentClass = StudentifyDatabaseProvider
                .getStudentClassDao(getApplicationContext())
                .getStudentClass(classId);

        NotificationHandler.showStudentClassNotification(getApplicationContext(), studentClass);
    }
}
