package cheema.hardeep.sahibdeep.studentify.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(SharedPreferencesProvider.isFirstLaunch(this)) {
            //show userinformation
        } else {
            //home activity
        }
    }
}
