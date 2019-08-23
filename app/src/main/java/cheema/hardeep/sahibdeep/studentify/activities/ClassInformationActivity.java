package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Time;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;

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
            StudentifyDatabaseProvider.getStudentClassDao(ClassInformationActivity.this).insertStudentClass(getClassDetails());
            finish();
        });
        mondayButton.setOnClickListener(v -> handleMondayButton());
        tuesdayButton.setOnClickListener(v -> handleTuesdayButton());
        wednesdayButton.setOnClickListener(v -> handleWednesdayButton());
        thursdayButton.setOnClickListener(v -> handleThursdayButton());
        fridayButton.setOnClickListener(v -> handleFridayButton());
        saturdayButton.setOnClickListener(v -> handleSaturdayButton());
    }

    private void handleSaturdayButton() {
        if (!saturdayButton.isSelected()) {
            daysList.add("Saturday");
            saturdayButton.setBackground(getDrawable(R.drawable.transparent_curved_button));
            saturdayButton.setTextColor(saturdayButton.getResources().getColor(R.color.primaryTextColor));
            saturdayButton.setSelected(true);
        } else if (saturdayButton.isSelected()) {
            daysList.remove("Saturday");
            saturdayButton.setBackground(getDrawable(R.drawable.curved_square_button_white));
            saturdayButton.setTextColor(saturdayButton.getResources().getColor(R.color.secondaryTextColor));
            saturdayButton.setSelected(false);
        }
    }

    private void handleFridayButton() {
        if (!fridayButton.isSelected()) {
            daysList.add("Friday");
            fridayButton.setBackground(getDrawable(R.drawable.transparent_curved_button));
            fridayButton.setTextColor(fridayButton.getResources().getColor(R.color.primaryTextColor));
            fridayButton.setSelected(true);
        } else if (fridayButton.isSelected()) {
            daysList.remove("Friday");
            fridayButton.setBackground(getDrawable(R.drawable.curved_square_button_white));
            fridayButton.setTextColor(fridayButton.getResources().getColor(R.color.secondaryTextColor));
            fridayButton.setSelected(false);
        }
    }

    private void handleThursdayButton() {
        if (!thursdayButton.isSelected()) {
            daysList.add("Thursday");
            thursdayButton.setBackground(getDrawable(R.drawable.transparent_curved_button));
            thursdayButton.setTextColor(thursdayButton.getResources().getColor(R.color.primaryTextColor));
            thursdayButton.setSelected(true);
        } else if (thursdayButton.isSelected()) {
            daysList.remove("Thursday");
            thursdayButton.setBackground(getDrawable(R.drawable.curved_square_button_white));
            thursdayButton.setTextColor(thursdayButton.getResources().getColor(R.color.secondaryTextColor));
            thursdayButton.setSelected(false);
        }
    }

    private void handleWednesdayButton() {
        if (!wednesdayButton.isSelected()) {
            daysList.add("Wednesday");
            wednesdayButton.setBackground(getDrawable(R.drawable.transparent_curved_button));
            wednesdayButton.setTextColor(wednesdayButton.getResources().getColor(R.color.primaryTextColor));
            wednesdayButton.setSelected(true);
        } else if (wednesdayButton.isSelected()) {
            daysList.remove("Wednesday");
            wednesdayButton.setBackground(getDrawable(R.drawable.curved_square_button_white));
            wednesdayButton.setTextColor(wednesdayButton.getResources().getColor(R.color.secondaryTextColor));
            wednesdayButton.setSelected(false);
        }
    }

    private void handleTuesdayButton() {
        if (!tuesdayButton.isSelected()) {
            daysList.add("Tuesday");
            tuesdayButton.setBackground(getDrawable(R.drawable.transparent_curved_button));
            tuesdayButton.setTextColor(tuesdayButton.getResources().getColor(R.color.primaryTextColor));
            tuesdayButton.setSelected(true);
        } else if (tuesdayButton.isSelected()) {
            daysList.remove("Tuesday");
            tuesdayButton.setBackground(getDrawable(R.drawable.curved_square_button_white));
            tuesdayButton.setTextColor(tuesdayButton.getResources().getColor(R.color.secondaryTextColor));
            tuesdayButton.setSelected(false);
        }
    }

    private void handleMondayButton() {
        if (!mondayButton.isSelected()) {
            daysList.add("Monday");
            mondayButton.setBackground(getDrawable(R.drawable.transparent_curved_button));
            mondayButton.setTextColor(mondayButton.getResources().getColor(R.color.primaryTextColor));
            mondayButton.setSelected(true);
        } else if (mondayButton.isSelected()) {
            daysList.remove("Monday");
            mondayButton.setBackground(getDrawable(R.drawable.curved_square_button_white));
            mondayButton.setTextColor(mondayButton.getResources().getColor(R.color.secondaryTextColor));
            mondayButton.setSelected(false);
        }
    }

    public StudentClass getClassDetails() {
        StudentClass studentClass = new StudentClass();
        studentClass.setName(className.getText().toString());
        studentClass.setProfessorName(professorName.getText().toString());
        studentClass.setRoomNumber(roomNumber.getText().toString());
        studentClass.setDays((daysList));
        studentClass.setStartTime(new Date());
        studentClass.setEndTime(new Date());
        studentClass.setTermId(21);
        return studentClass;
    }
}
