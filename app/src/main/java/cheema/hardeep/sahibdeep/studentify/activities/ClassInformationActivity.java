package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.utils.DatabaseUtils;

public class ClassInformationActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, ClassInformationActivity.class);
    }

    @BindView(R.id.className)
    TextInputEditText className;

    @BindView(R.id.professorName)
    TextInputEditText professorName;

    @BindView(R.id.roomNumber)
    TextInputEditText roomNumber;

    @BindView(R.id.mondayButton)
    Button mondayButton;

    @BindView(R.id.tuesdayButton)
    Button tuesdayButton;

    @BindView(R.id.wednesdayButton)
    Button wednesdayButton;

    @BindView(R.id.thursdayButton)
    Button thursdayButton;

    @BindView(R.id.fridayButton)
    Button fridayButton;

    @BindView(R.id.saturdayButton)
    Button saturdayButton;

    @BindView(R.id.startTime)
    Button startTimeButton;

    @BindView(R.id.endTime)
    Button endTimeButton;

    @BindView(R.id.cancelButton)
    Button cancelButton;

    @BindView(R.id.addButton)
    Button addButton;

    List<String> daysList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_information);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        daysList = new ArrayList<>();

        cancelButton.setOnClickListener(v -> finish());
        addButton.setOnClickListener(v -> {
            StudentifyDatabaseProvider
                    .getStudentClassDao(ClassInformationActivity.this)
                    .insertStudentClass(getClassDetails());
            finish();
        });

        mondayButton.setOnClickListener(v -> handleButtonBackground(mondayButton));
        tuesdayButton.setOnClickListener(v -> handleButtonBackground(tuesdayButton));
        wednesdayButton.setOnClickListener(v -> handleButtonBackground(wednesdayButton));
        thursdayButton.setOnClickListener(v -> handleButtonBackground(thursdayButton));
        fridayButton.setOnClickListener(v -> handleButtonBackground(fridayButton));
        saturdayButton.setOnClickListener(v -> handleButtonBackground(saturdayButton));
    }

    private void handleButtonBackground(Button button) {
        if (!button.isSelected()) {
            daysList.add(button.getText().toString());
            button.setBackground(getDrawable(R.drawable.transparent_curved_button));
            button.setTextColor(button.getResources().getColor(R.color.primaryTextColor));
            button.setSelected(true);
        } else {
            daysList.remove(button.getText().toString());
            button.setBackground(getDrawable(R.drawable.curved_square_button_white));
            button.setTextColor(button.getResources().getColor(R.color.secondaryTextColor));
            button.setSelected(false);
        }
    }

    //TODO: Validations and Dates (Comeback)
    public StudentClass getClassDetails() {
        StudentClass studentClass = new StudentClass();
        studentClass.setName(className.getText().toString());
        studentClass.setProfessorName(professorName.getText().toString());
        studentClass.setRoomNumber(roomNumber.getText().toString());
        studentClass.setDays((daysList));
        studentClass.setStartTime(new Date());
        studentClass.setEndTime(new Date());
        studentClass.setTermId(DatabaseUtils.getUserTerm(this).getId());
        return studentClass;
    }
}
