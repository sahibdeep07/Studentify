package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import cheema.hardeep.sahibdeep.studentify.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
    }
}
