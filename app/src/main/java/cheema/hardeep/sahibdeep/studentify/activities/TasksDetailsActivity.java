package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import cheema.hardeep.sahibdeep.studentify.R;

public class TasksDetailsActivity extends AppCompatActivity {

    public static Intent createIntent(Context context){
        return new Intent(context, TasksDetailsActivity.class);
    }

    @BindView(R.id.taskName)
    TextInputEditText taskName;

    @BindView(R.id.taskMondayButton)
    Button mondayButton;

    @BindView(R.id.taskTuesdayButton)
    Button tuesdayButton;

    @BindView(R.id.taskWednesdayButton)
    Button wednesdayButton;

    @BindView(R.id.taskThursdayButton)
    Button thursdayButton;

    @BindView(R.id.taskFridayButton)
    Button fridayButton;

    @BindView(R.id.taskSaturdayButton)
    Button saturdayButton;

    @BindView(R.id.time)
    TextInputEditText time;

    @BindView(R.id.notes)
    TextInputEditText notes;

    @BindView(R.id.taskAddButton)
    Button addButton;

    @BindView(R.id.taskCancelButton)
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_details);
        getSupportActionBar().hide();
    }
}
