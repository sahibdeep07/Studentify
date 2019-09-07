package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.adapters.TaskAdapter;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.interfaces.ViewRefreshInterface;
import cheema.hardeep.sahibdeep.studentify.models.tables.TaskType;

import static cheema.hardeep.sahibdeep.studentify.activities.TasksDetailsActivity.NEGATIVE_TASK_ID;

public class TasksActivity extends AppCompatActivity implements ViewRefreshInterface {

    private static final String KEY_CLASS_ID = "key-class-id";

    public static Intent createIntent(Context context, int classId) {
        Intent intent = new Intent(context, TasksActivity.class);
        intent.putExtra(KEY_CLASS_ID, classId);
        return intent;
    }

    @BindView(R.id.addHomeworkButton)
    ImageView addHomeworkButton;

    @BindView(R.id.addTestButton)
    ImageView addTestButton;

    @BindView(R.id.homeworkRecyclerView)
    RecyclerView homeworkRecyclerView;

    @BindView(R.id.noTest)
    TextView noTest;

    @BindView(R.id.noHomework)
    TextView noHomework;

    @BindView(R.id.testRecyclerView)
    RecyclerView testRecyclerView;

    @BindView(R.id.doneButton)
    Button doneButton;

    TaskAdapter homeworkAdapter;
    TaskAdapter testAdapter;

    int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        classId = getIntent().getIntExtra(KEY_CLASS_ID, -1);
        setupHomeworkRV();
        setupTestRV();

        addHomeworkButton.setOnClickListener(v ->
                startActivity(TasksDetailsActivity.createIntent(v.getContext(), true, classId, NEGATIVE_TASK_ID)));
        addTestButton.setOnClickListener(v ->
                startActivity(TasksDetailsActivity.createIntent(v.getContext(), false, classId, NEGATIVE_TASK_ID)));
        doneButton.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshTest();
        refreshHomework();
    }

    @Override
    public void refreshTest() {
        testAdapter.updateList(
                StudentifyDatabaseProvider
                        .getTaskDao(this)
                        .getTaskWithType(classId, TaskType.TEST.name())
        );
        if (!StudentifyDatabaseProvider
                .getTaskDao(this)
                .getTaskWithType(classId, TaskType.TEST.name()).isEmpty())
            noTest.setVisibility(View.GONE);
    }

    @Override
    public void refreshHomework() {
        homeworkAdapter.updateList(
                StudentifyDatabaseProvider
                        .getTaskDao(this)
                        .getTaskWithType(classId, TaskType.HOMEWORK.name())
        );
        if (!StudentifyDatabaseProvider
                .getTaskDao(this)
                .getTaskWithType(classId, TaskType.HOMEWORK.name()).isEmpty())
            noHomework.setVisibility(View.GONE);
    }

    private void setupTestRV() {
        testAdapter = new TaskAdapter(this, false);
        testRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        testRecyclerView.setAdapter(testAdapter);
    }

    private void setupHomeworkRV() {
        homeworkAdapter = new TaskAdapter(this, true);
        homeworkRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        homeworkRecyclerView.setAdapter(homeworkAdapter);
    }
}
