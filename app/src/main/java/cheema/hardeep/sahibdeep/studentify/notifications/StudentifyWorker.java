package cheema.hardeep.sahibdeep.studentify.notifications;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabase;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;
import cheema.hardeep.sahibdeep.studentify.models.tables.TaskType;

import static cheema.hardeep.sahibdeep.studentify.notifications.NotificationScheduler.KEY_STUDENT_CLASS_ID;
import static cheema.hardeep.sahibdeep.studentify.notifications.NotificationScheduler.KEY_TASK_ID;

public class StudentifyWorker extends Worker {

    private static final int NEGATIVE_ONE = -1;

    public StudentifyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(StudentifyWorker.class.getSimpleName(), "Work Initiated!");
        Data inputData = getInputData();
        int classId = inputData.getInt(KEY_STUDENT_CLASS_ID, NEGATIVE_ONE);
        int taskId = inputData.getInt(KEY_TASK_ID, NEGATIVE_ONE);

        if (classId != NEGATIVE_ONE) {
            handleStudentClassWork(classId);
        } else if (taskId != NEGATIVE_ONE) {
            handleTaskWork(taskId);
        }
        Log.d(StudentifyWorker.class.getSimpleName(), "Work Finished!");
        return Result.success();
    }

    private void handleStudentClassWork(int classId) {
        StudentClass studentClass = StudentifyDatabaseProvider
                .getStudentClassDao(getApplicationContext())
                .getStudentClass(classId);

        NotificationHandler.showStudentClassNotification(getApplicationContext(), studentClass);
    }

    private void handleTaskWork(int taskId) {
        Task task = StudentifyDatabaseProvider
                .getTaskDao(getApplicationContext())
                .getTask(taskId);

        if (task.getType() == TaskType.HOMEWORK) {
            StudentifyDatabaseProvider
                    .getStudentClassDao(getApplicationContext())
                    .updateStudentClassFinishedHomework(task.getStudentClassId());
        } else if (task.getType() == TaskType.TEST) {
            StudentifyDatabaseProvider
                    .getStudentClassDao(getApplicationContext())
                    .updateStudentClassCompletedTest(task.getStudentClassId());
        }

        NotificationHandler.showTaskNotification(getApplicationContext(), task);
    }
}
