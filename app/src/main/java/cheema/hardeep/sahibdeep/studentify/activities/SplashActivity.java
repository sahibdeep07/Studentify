package cheema.hardeep.sahibdeep.studentify.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.UserInformation;

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

        UserInformation userInformation = new UserInformation();
        userInformation.setName("Sandeep Kaur");
        userInformation.setAddress("The Hood");
        userInformation.setCollegeName("Gareeba Da");
        userInformation.setDateOfBirth("Summer To Phela");
        userInformation.setStudentId("DoubleChin");
        userInformation.setTermName("Fall 2019");
        StudentifyDatabaseProvider.getDao(this).insertUserInformation(userInformation);

        UserInformation userInformationResult = StudentifyDatabaseProvider.getDao(this).getUserInformation("DoubleChin");
        Toast.makeText(this, userInformationResult.getId() + userInformation.getName(), Toast.LENGTH_SHORT).show();
    }
}
