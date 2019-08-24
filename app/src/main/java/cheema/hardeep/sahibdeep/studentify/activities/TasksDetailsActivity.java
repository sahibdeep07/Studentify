package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;
import cheema.hardeep.sahibdeep.studentify.models.tables.TaskType;
import cheema.hardeep.sahibdeep.studentify.utils.DialogUtil;

public class TasksDetailsActivity extends AppCompatActivity {

    public static final int NEGATIVE_TASK_ID = -1;
    public static final int DEFAULT_CLASS_ID = 0;
    public static final String KEY_IS_HOMEWORK = "key-is-homework";
    public static final String KEY_CLASS_ID = "key-class-id";
    public static final String KEY_TASK_ID = "key-task-id";
    public static final String DATE_TIME_FORMAT = "MMM dd, YYYY HH:MM";

    public static Intent createIntent(Context context, boolean homework, int classId, int taskId) {
        Intent intent = new Intent(context, TasksDetailsActivity.class);
        intent.putExtra(KEY_IS_HOMEWORK, homework);
        intent.putExtra(KEY_CLASS_ID, classId);
        intent.putExtra(KEY_TASK_ID, taskId);
        return intent;
    }

    @BindView(R.id.taskName)
    TextInputEditText taskName;

    @BindView(R.id.date)
    TextInputEditText time;

    @BindView(R.id.notes)
    TextInputEditText notes;

    @BindView(R.id.taskAddButton)
    Button addButton;

    @BindView(R.id.taskCancelButton)
    Button cancelButton;

    int taskId;
    int classId;
    TaskType taskType;
    Calendar userDateTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_details);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        taskType = intent.getBooleanExtra(KEY_IS_HOMEWORK, false) ? TaskType.HOMEWORK : TaskType.TEST;
        taskId = intent.getIntExtra(KEY_TASK_ID, NEGATIVE_TASK_ID);
        classId = intent.getIntExtra(KEY_CLASS_ID, DEFAULT_CLASS_ID);
        if (taskId != NEGATIVE_TASK_ID) setTaskData();

        cancelButton.setOnClickListener(v -> finish());
        addButton.setOnClickListener(v -> {
            if (taskId == NEGATIVE_TASK_ID) {
                StudentifyDatabaseProvider.getTaskDao(TasksDetailsActivity.this).insertTask(getTask());
            } else {
                StudentifyDatabaseProvider.getTaskDao(TasksDetailsActivity.this).upddateTask(getTask());
            }
            finish();
        });

        time.setOnClickListener(v -> {
            userDateTime = Calendar.getInstance();
            DialogUtil.createDateDialog(this, (datePicker, year, month, dayOfMonth) -> {
                userDateTime.set(Calendar.YEAR, year);
                userDateTime.set(Calendar.MONTH, month);
                userDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                DialogUtil.createTimeDialog(TasksDetailsActivity.this, (timePicker, hour, minute) -> {
                    userDateTime.set(Calendar.HOUR, hour);
                    userDateTime.set(Calendar.MINUTE, minute);
                    formatDisplayDateTime();
                });
            });
        });
    }

    private void formatDisplayDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.US);
        time.setText(sdf.format(userDateTime.getTime()));
    }

    public Task getTask() {
        Task task = new Task();
        task.setName(taskName.getText().toString());
        task.setNotes(notes.getText().toString());
        task.setDateTime(userDateTime.getTime());
        task.setType(taskType);
        task.setStudentClassId(classId);
        return task;
    }

    public void setTaskData() {
        Task task = StudentifyDatabaseProvider.getTaskDao(this).getTask(taskId);
        taskName.setText(task.getName());
        notes.setText(task.getNotes());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(task.getDateTime().getTime());
        userDateTime = calendar;
        formatDisplayDateTime();
    }
}
