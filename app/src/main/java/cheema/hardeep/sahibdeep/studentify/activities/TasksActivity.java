package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.adapters.HomeworkAdapter;
import cheema.hardeep.sahibdeep.studentify.adapters.TestAdapter;
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.TaskType;

public class TasksActivity extends AppCompatActivity {

    public static Intent createIntent(Context context){
        return new Intent(context, TasksActivity.class);
    }

    @BindView(R.id.addHomeworkButton)
    ImageView addHomeworkButton;

    @BindView(R.id.addTestButton)
    ImageView addTestButton;

    @BindView(R.id.homeworkRecyclerView)
    RecyclerView homeworkRecyclerView;

    @BindView(R.id.testRecyclerView)
    RecyclerView testRecyclerView;

    @BindView(R.id.doneButton)
    Button doneButton;

    HomeworkAdapter homeworkAdapter;

    TestAdapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        addHomeworkButton.setOnClickListener(v -> startActivity(TasksDetailsActivity.createIntent(v.getContext(), true)));
        addTestButton.setOnClickListener(v -> startActivity(TasksDetailsActivity.createIntent(v.getContext(), false)));
        doneButton.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupHomeworkRV();
        setupTestRV();
    }

    private void setupTestRV() {
        testAdapter = new TestAdapter();
        testAdapter.updateList(StudentifyDatabaseProvider.getTaskDao(TasksActivity.this).getTaskWithType(Integer.parseInt(SharedPreferencesProvider.getStudentId(TasksActivity.this)), TaskType.TEST));
        testRecyclerView.setLayoutManager(new LinearLayoutManager(TasksActivity.this, RecyclerView.VERTICAL, false));
        testRecyclerView.setAdapter(testAdapter);
    }

    private void setupHomeworkRV() {
        homeworkAdapter = new HomeworkAdapter();
        homeworkAdapter.updateList(StudentifyDatabaseProvider.getTaskDao(TasksActivity.this).getTaskWithType(Integer.parseInt(SharedPreferencesProvider.getStudentId(TasksActivity.this)), TaskType.HOMEWORK));
        homeworkRecyclerView.setLayoutManager(new LinearLayoutManager(TasksActivity.this, RecyclerView.VERTICAL, false));
        homeworkRecyclerView.setAdapter(homeworkAdapter);
    }
}
