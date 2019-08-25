package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;

public class SplashActivity extends AppCompatActivity {
    final static int TRANSITION_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        new Handler().postDelayed(() -> {
            if (SharedPreferencesProvider.isFirstLaunch(SplashActivity.this)) {
                startActivity(new Intent(SplashActivity.this, UserInformationActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
            finish();
        }, TRANSITION_TIME);
    }
}
