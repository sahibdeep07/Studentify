package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cheema.hardeep.sahibdeep.studentify.R;

public class ClassInformationActivity extends AppCompatActivity {

    public static Intent createIntent(Context context){
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_information);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
    }
}
