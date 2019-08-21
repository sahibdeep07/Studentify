package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.TermDetails;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.Term;
import cheema.hardeep.sahibdeep.studentify.models.tables.UserInformation;
import cheema.hardeep.sahibdeep.studentify.notifications.NotificationScheduler;

public class SplashActivity extends AppCompatActivity {
    final static int TRANSITION_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            if(SharedPreferencesProvider.isFirstLaunch(SplashActivity.this)) {
                startActivity(new Intent(SplashActivity.this, UserInformationActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
            finish();
        }, TRANSITION_TIME);


        UserInformation userInformation = new UserInformation();
        userInformation.setName("Sandeep Kaur");
        userInformation.setAddress("The Hood");
        userInformation.setCollegeName("Gareeba Da");
        userInformation.setDateOfBirth("Summer To Phela");
        userInformation.setStudentId("DoubleChin");
        userInformation.setTermName("Fall 2019");
        StudentifyDatabaseProvider.getUserInformationDao(this).insertUserInformation(userInformation);

        Log.d("HSING", "-------------------------------------------------------------------");
        UserInformation userInformationResult = StudentifyDatabaseProvider.getUserInformationDao(this).getUserInformation("DoubleChin");
        Log.d("HSING", "UserInformation Result: " + userInformationResult.getId() + " " + userInformationResult.getName());


        Term term = new Term();
        term.setName(userInformation.getTermName());
        term.setStartDate(new Date());
        term.setEndDate(new Date());
        StudentifyDatabaseProvider.getTermDao(this).insertTerm(term);

        Log.d("HSING", "-------------------------------------------------------------------");
        Term termResult = StudentifyDatabaseProvider.getTermDao(this).getTerm(userInformation.getTermName());
        Log.d("HSING", "Term Result: " + termResult.getId() + " " +termResult.getName());

        StudentClass studentClass1 = new StudentClass();
        studentClass1.setName("Class 1");
        studentClass1.setDays(Arrays.asList("Mon", "Wed"));
        studentClass1.setStartTime(new Date());
        studentClass1.setEndTime(new Date());
        studentClass1.setTermId(termResult.getId());
        studentClass1.setProfessorName("Chandler Bing");
        studentClass1.setRoomNumber("54");
        StudentifyDatabaseProvider.getStudentClassDao(this).insertStudentClass(studentClass1);

        StudentClass studentClass2 = new StudentClass();
        studentClass2.setName("Class 2");
        studentClass2.setDays(Arrays.asList("Tue", "Thur"));
        studentClass2.setStartTime(new Date());
        studentClass2.setEndTime(new Date());
        studentClass2.setTermId(termResult.getId());
        studentClass2.setProfessorName("Chandler Bing 2");
        studentClass2.setRoomNumber("56");
        StudentifyDatabaseProvider.getStudentClassDao(this).insertStudentClass(studentClass2);

        StudentClass studentClass3 = new StudentClass();
        studentClass3.setName("Class 3");
        studentClass3.setDays(Arrays.asList("Fri", "Sat"));
        studentClass3.setStartTime(new Date());
        studentClass3.setEndTime(new Date());
        studentClass3.setTermId(termResult.getId());
        studentClass3.setProfessorName("Chandler Bing 3");
        studentClass3.setRoomNumber("59");
        StudentifyDatabaseProvider.getStudentClassDao(this).insertStudentClass(studentClass3);

        StudentClass studentClass4 = new StudentClass();
        studentClass3.setName("Class 4");
        studentClass3.setDays(Arrays.asList("Sat"));
        studentClass3.setStartTime(new Date());
        studentClass3.setEndTime(new Date());
        studentClass3.setTermId(20);
        studentClass3.setProfessorName("Chandler Bing 3");
        studentClass3.setRoomNumber("59");
        StudentifyDatabaseProvider.getStudentClassDao(this).insertStudentClass(studentClass4);

        Log.d("HSING", "-------------------------------------------------------------------");
        List<StudentClass> studentClassesWithDay = StudentifyDatabaseProvider.getStudentClassDao(this).getStudentClassesWithDay("%Tue%");
        Log.d("HSING", "Student Classes with Day Size: " + studentClassesWithDay.size());
        for(StudentClass studentClass: studentClassesWithDay) {
            Log.d("HSING", "Student Classes with Day: " + studentClass.getName());
        }

        Log.d("HSING", "-------------------------------------------------------------------");
        TermDetails termDetails = StudentifyDatabaseProvider.getTermDao(this).getTermWithClasses(termResult.getName());
        Log.d("HSING", "Term Details: " + termDetails.getTerm().getName());
        for(StudentClass studentClass: termDetails.getStudentClasses()) {
            Log.d("HSING", "Student Classes with Term: " + studentClass.getName());
        }
        Log.d("HSING", "-------------------------------------------------------------------");

        NotificationScheduler.scheduleClassNotification(this, studentClass1.getId());
    }
}
