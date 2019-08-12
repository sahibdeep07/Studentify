package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

    }
}
