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

public class UserInformationActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, UserInformationActivity.class);
    }

    @BindView(R.id.name)
    TextInputEditText name;

    @BindView(R.id.collegeName)
    TextInputEditText collegeName;

    @BindView(R.id.studentId)
    TextInputEditText studentID;

    @BindView(R.id.phoneNumber)
    TextInputEditText phoneNumber;

    @BindView(R.id.dateOfBirth)
    TextInputEditText dob;

    @BindView(R.id.address)
    TextInputEditText address;

    @BindView(R.id.term)
    TextInputEditText term;

    @BindView(R.id.save)
    Button saveButton;

    @BindView(R.id.clearTerm)
    Button clearTermButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

    }
}
