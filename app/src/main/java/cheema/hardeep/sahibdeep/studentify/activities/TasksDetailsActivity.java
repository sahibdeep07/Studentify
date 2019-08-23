package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabase;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;
import cheema.hardeep.sahibdeep.studentify.models.tables.TaskType;
import cheema.hardeep.sahibdeep.studentify.utils.TimeDialog;

public class TasksDetailsActivity extends AppCompatActivity {
    public static boolean isHomework;

    public static Intent createIntent(Context context, boolean homework){
        isHomework = homework;
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

    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_details);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        cancelButton.setOnClickListener(v -> finish());
        addButton.setOnClickListener(v -> {
            StudentifyDatabaseProvider.getTaskDao(TasksDetailsActivity.this).insertTask(getTask());
            finish();
        });
        mondayButton.setOnClickListener(v -> handleMondayButton());
        tuesdayButton.setOnClickListener(v -> handleTuesdayButton());
        wednesdayButton.setOnClickListener(v -> handleWednesdayButton());
        thursdayButton.setOnClickListener(v -> handleThursdayButton());
        fridayButton.setOnClickListener(v -> handleFridayButton());
        saturdayButton.setOnClickListener(v -> handleSaturdayButton());
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeDialog.createTimeDialog(TasksDetailsActivity.this);
            }
        });
    }

    public Task getTask(){
        task = new Task();
        task.setName(taskName.getText().toString());
        task.setNotes(notes.getText().toString());
        task.setDateTime(new Date());
        if(isHomework) task.setType(TaskType.HOMEWORK);
        else task.setType(TaskType.TEST);
        task.setStudentClassId(Integer.parseInt(SharedPreferencesProvider.getStudentId(TasksDetailsActivity.this)));
        return task;
    }

    // TODO: 23-08-2019  
//    public void setTask(){
//        task = StudentifyDatabaseProvider.getTaskDao(TasksDetailsActivity.this).get
//        taskName.setText();
//        notes.setText();
//        time.setText();
//    }

    public void handleMondayButton(){
        if (!mondayButton.isSelected()) {
            mondayButton.setBackground(getDrawable(R.drawable.transparent_curved_button));
            mondayButton.setTextColor(mondayButton.getResources().getColor(R.color.primaryTextColor));
            mondayButton.setSelected(true);
        } else if (mondayButton.isSelected()) {
            mondayButton.setBackground(getDrawable(R.drawable.curved_square_button_white));
            mondayButton.setTextColor(mondayButton.getResources().getColor(R.color.secondaryTextColor));
            mondayButton.setSelected(false);
        }
    }

    private void handleSaturdayButton() {
        if (!saturdayButton.isSelected()) {
            saturdayButton.setBackground(getDrawable(R.drawable.transparent_curved_button));
            saturdayButton.setTextColor(saturdayButton.getResources().getColor(R.color.primaryTextColor));
            saturdayButton.setSelected(true);
        } else if (saturdayButton.isSelected()) {
            saturdayButton.setBackground(getDrawable(R.drawable.curved_square_button_white));
            saturdayButton.setTextColor(saturdayButton.getResources().getColor(R.color.secondaryTextColor));
            saturdayButton.setSelected(false);
        }
    }

    private void handleFridayButton() {
        if (!fridayButton.isSelected()) {
            fridayButton.setBackground(getDrawable(R.drawable.transparent_curved_button));
            fridayButton.setTextColor(fridayButton.getResources().getColor(R.color.primaryTextColor));
            fridayButton.setSelected(true);
        } else if (fridayButton.isSelected()) {
            fridayButton.setBackground(getDrawable(R.drawable.curved_square_button_white));
            fridayButton.setTextColor(fridayButton.getResources().getColor(R.color.secondaryTextColor));
            fridayButton.setSelected(false);
        }
    }

    private void handleThursdayButton() {
        if (!thursdayButton.isSelected()) {
            thursdayButton.setBackground(getDrawable(R.drawable.transparent_curved_button));
            thursdayButton.setTextColor(thursdayButton.getResources().getColor(R.color.primaryTextColor));
            thursdayButton.setSelected(true);
        } else if (thursdayButton.isSelected()) {
            thursdayButton.setBackground(getDrawable(R.drawable.curved_square_button_white));
            thursdayButton.setTextColor(thursdayButton.getResources().getColor(R.color.secondaryTextColor));
            thursdayButton.setSelected(false);
        }
    }

    private void handleWednesdayButton() {
        if (!wednesdayButton.isSelected()) {
            wednesdayButton.setBackground(getDrawable(R.drawable.transparent_curved_button));
            wednesdayButton.setTextColor(wednesdayButton.getResources().getColor(R.color.primaryTextColor));
            wednesdayButton.setSelected(true);
        } else if (wednesdayButton.isSelected()) {
            wednesdayButton.setBackground(getDrawable(R.drawable.curved_square_button_white));
            wednesdayButton.setTextColor(wednesdayButton.getResources().getColor(R.color.secondaryTextColor));
            wednesdayButton.setSelected(false);
        }
    }

    private void handleTuesdayButton() {
        if (!tuesdayButton.isSelected()) {
            tuesdayButton.setBackground(getDrawable(R.drawable.transparent_curved_button));
            tuesdayButton.setTextColor(tuesdayButton.getResources().getColor(R.color.primaryTextColor));
            tuesdayButton.setSelected(true);
        } else if (tuesdayButton.isSelected()) {
            tuesdayButton.setBackground(getDrawable(R.drawable.curved_square_button_white));
            tuesdayButton.setTextColor(tuesdayButton.getResources().getColor(R.color.secondaryTextColor));
            tuesdayButton.setSelected(false);
        }
    }

}
