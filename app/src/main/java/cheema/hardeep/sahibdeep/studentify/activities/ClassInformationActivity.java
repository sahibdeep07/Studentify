package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.notifications.NotificationScheduler;
import cheema.hardeep.sahibdeep.studentify.utils.DatabaseUtil;
import cheema.hardeep.sahibdeep.studentify.utils.DateUtils;
import cheema.hardeep.sahibdeep.studentify.utils.DialogUtil;

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
    TextView startTime;

    @BindView(R.id.endTime)
    TextView endTime;

    @BindView(R.id.cancelButton)
    Button cancelButton;

    @BindView(R.id.addButton)
    Button addButton;

    List<String> daysList;

    Calendar startTimeCalendar;
    Calendar endTimeCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_information);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        daysList = new ArrayList<>();

        cancelButton.setOnClickListener(v -> finish());
        addButton.setOnClickListener(v -> {
            if (fieldCheck())
                Toast.makeText(this, "Please Fill All The Fields And Select Days", Toast.LENGTH_SHORT).show();
            else {
                StudentClass studentClass = getClassDetails();
                StudentifyDatabaseProvider
                        .getStudentClassDao(ClassInformationActivity.this)
                        .insertStudentClass(studentClass);
                NotificationScheduler.scheduleClassNotification(this, studentClass);
                finish();
            }
        });

        mondayButton.setOnClickListener(v -> handleButtonBackground(mondayButton));
        tuesdayButton.setOnClickListener(v -> handleButtonBackground(tuesdayButton));
        wednesdayButton.setOnClickListener(v -> handleButtonBackground(wednesdayButton));
        thursdayButton.setOnClickListener(v -> handleButtonBackground(thursdayButton));
        fridayButton.setOnClickListener(v -> handleButtonBackground(fridayButton));
        saturdayButton.setOnClickListener(v -> handleButtonBackground(saturdayButton));

        startTime.setOnClickListener(v -> {
            startTimeCalendar = Calendar.getInstance();
            DialogUtil.createTimeDialog(ClassInformationActivity.this, (view, hour, minute) -> {
                startTimeCalendar.set(Calendar.HOUR, hour);
                startTimeCalendar.set(Calendar.MINUTE, minute);
                startTime.setText(DateUtils.formatDisplayTime(startTimeCalendar));
            });
        });
        endTime.setOnClickListener(v -> {
            endTimeCalendar = Calendar.getInstance();
            DialogUtil.createTimeDialog(ClassInformationActivity.this, (view, hour, minute) -> {
                endTimeCalendar.set(Calendar.HOUR, hour);
                endTimeCalendar.set(Calendar.MINUTE, minute);
                endTime.setText(DateUtils.formatDisplayTime(endTimeCalendar));
            });
        });
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

    public StudentClass getClassDetails() {
        StudentClass studentClass = new StudentClass();
        studentClass.setName(className.getText().toString());
        studentClass.setProfessorName(professorName.getText().toString());
        studentClass.setRoomNumber(roomNumber.getText().toString());
        studentClass.setDays((daysList));
        studentClass.setStartTime(startTimeCalendar.getTime());
        studentClass.setEndTime(endTimeCalendar.getTime());
        studentClass.setTermId(DatabaseUtil.getUserTerm(this).getId());
        return studentClass;
    }

    private boolean fieldCheck() {
        return className.getText().toString().isEmpty()
                || professorName.getText().toString().isEmpty()
                || roomNumber.getText().toString().isEmpty()
                || daysList.isEmpty();
    }
}
