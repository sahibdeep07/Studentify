package cheema.hardeep.sahibdeep.studentify.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabase;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.TermDetails;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.Term;
import cheema.hardeep.sahibdeep.studentify.models.tables.UserInformation;

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
        Log.d("HSING", "UserInformation Result: " + userInformationResult.getId() + " " + userInformationResult.getName());


        Term term = new Term();
        term.setName(userInformation.getTermName());
        term.setStartDate(new Date());
        term.setEndDate(new Date());
        StudentifyDatabaseProvider.getDao(this).insertTerm(term);

        Term termResult = StudentifyDatabaseProvider.getDao(this).getTerm(userInformation.getTermName());
        Log.d("HSING", "Term Result: " + termResult.getId() + " " +termResult.getName());

        StudentClass studentClass1 = new StudentClass();
        studentClass1.setName("Class 1");
        studentClass1.setDays(Arrays.asList("Mon", "Wed"));
        studentClass1.setStartTime("3.00");
        studentClass1.setEndTime("4.00");
        studentClass1.setTermId(termResult.getId());
        studentClass1.setProfessorName("Chandler Bing");
        studentClass1.setRoomNumber("54");
        StudentifyDatabaseProvider.getDao(this).insertStudentClass(studentClass1);

        StudentClass studentClass2 = new StudentClass();
        studentClass2.setName("Class 2");
        studentClass2.setDays(Arrays.asList("Tue", "Thur"));
        studentClass2.setStartTime("2.00");
        studentClass2.setEndTime("5.00");
        studentClass2.setTermId(termResult.getId());
        studentClass2.setProfessorName("Chandler Bing 2");
        studentClass2.setRoomNumber("56");
        StudentifyDatabaseProvider.getDao(this).insertStudentClass(studentClass2);

        StudentClass studentClass3 = new StudentClass();
        studentClass3.setName("Class 3");
        studentClass3.setDays(Arrays.asList("Fri", "Sat"));
        studentClass3.setStartTime("11.00");
        studentClass3.setEndTime("12.00");
        studentClass3.setTermId(termResult.getId());
        studentClass3.setProfessorName("Chandler Bing 3");
        studentClass3.setRoomNumber("59");
        StudentifyDatabaseProvider.getDao(this).insertStudentClass(studentClass3);

        StudentClass studentClass4 = new StudentClass();
        studentClass3.setName("Class 4");
        studentClass3.setDays(Arrays.asList("Sat"));
        studentClass3.setStartTime("10.00");
        studentClass3.setEndTime("2.00");
        studentClass3.setTermId(20);
        studentClass3.setProfessorName("Chandler Bing 3");
        studentClass3.setRoomNumber("59");
        StudentifyDatabaseProvider.getDao(this).insertStudentClass(studentClass4);


        List<StudentClass> studentClassesWithDay = StudentifyDatabaseProvider.getDao(this).getStudentClassesWithDay(Arrays.asList("Tue"));
        Log.d("HSING", "Student Classes with Day Size: " + studentClassesWithDay.size());
        for(StudentClass studentClass: studentClassesWithDay) {
            Log.d("HSING", "Student Classes with Day: " + studentClass.getName());
        }

        TermDetails termDetails = StudentifyDatabaseProvider.getDao(this).getTermWithClasses(termResult.getName());
        Log.d("HSING", "Term Details: " + termDetails.getTerm().getName());
        for(StudentClass studentClass: termDetails.getStudentClasses()) {
            Log.d("HSING", "Student Classes with Term: " + studentClass.getName());
        }
    }
}
