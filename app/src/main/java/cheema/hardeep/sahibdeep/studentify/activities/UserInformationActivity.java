package cheema.hardeep.sahibdeep.studentify.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.Term;
import cheema.hardeep.sahibdeep.studentify.models.tables.UserInformation;
import cheema.hardeep.sahibdeep.studentify.utils.DatabaseUtil;
import cheema.hardeep.sahibdeep.studentify.utils.DateUtils;
import cheema.hardeep.sahibdeep.studentify.utils.DialogUtil;

public class UserInformationActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, UserInformationActivity.class);
    }

    public static final String EMPTY = "";
    public static final String EVEN_SEMESTER = "Even Semester";
    public static final String ODD_SEMESTER = "Odd Semester";

    CharSequence[] termItems = new CharSequence[]{EVEN_SEMESTER, ODD_SEMESTER};

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

    @BindView(R.id.termContainer)
    TextInputLayout termContainer;

    @BindView(R.id.term)
    TextInputEditText term;

    @BindView(R.id.save)
    Button saveButton;

    @BindView(R.id.clearTerm)
    Button clearTermButton;

    @BindView(R.id.addTermButton)
    ImageView addButton;

    @BindView(R.id.termStart)
    TextView termStartDate;

    @BindView(R.id.termEnd)
    TextView termEndDate;

    UserInformation userInformation = null;
    Calendar userDob;

    Calendar termDate = Calendar.getInstance();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        if (!SharedPreferencesProvider.isFirstLaunch(this)) {
            userInformation = DatabaseUtil.getUserInformation(this);
            setUserInformation();
            clearTermButton.setVisibility(View.VISIBLE);
        }

        saveButton.setOnClickListener(v -> handleSaveUpdateButton());

        clearTermButton.setOnClickListener(v -> DialogUtil.createDeleteConfirmationDialog(this, (dialog, which) -> clearTerm()));

        term.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                termDialog(v);
                term.setOnClickListener(v12 -> termDialog(v12));
            }
        });

        dob.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                dobDialog();
                dob.setOnClickListener(v1 -> dobDialog());
            }
        });

        if (term.getText().toString().isEmpty()) {
            termStartDate.setVisibility(View.GONE);
        }
        termStartDate.setOnClickListener(v -> DialogUtil.createDobDateDialog(v.getContext(), (view, year, month, dayOfMonth) -> {
            termDate.set(Calendar.YEAR, year);
            termDate.set(Calendar.MONTH, month);
            termDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            termStartDate.setText(DateUtils.formatDisplayDate(termDate));
        }));

        termEndDate.setVisibility(View.GONE);
        termEndDate.setOnClickListener(v -> DialogUtil.createDobDateDialog(v.getContext(), (view, year, month, dayOfMonth) -> {
            termDate.set(Calendar.YEAR, year);
            termDate.set(Calendar.MONTH, month);
            termDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            termEndDate.setText(DateUtils.formatDisplayDate(termDate));
        }));
    }

    private void handleSaveUpdateButton() {
        if (fieldCheck()) {
            Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
        } else {
            if (SharedPreferencesProvider.isFirstLaunch(this)) {
                UserInformation userInfo = createUserInformation();
                StudentifyDatabaseProvider.getUserInformationDao(this).insertUserInformation(userInfo);
                StudentifyDatabaseProvider.getTermDao(this).insertTerm(getTerm(userInfo.getTermName()));
                SharedPreferencesProvider.saveUserId(this, userInfo.getStudentId());
                SharedPreferencesProvider.saveFirstLaunchCompleted(this);
            } else {
                updateUserInformationWithFields(userInformation);
                StudentifyDatabaseProvider.getUserInformationDao(this).updateUserInformation(userInformation);
                StudentifyDatabaseProvider.getTermDao(this).insertTerm(getTerm(userInformation.getTermName()));
            }
            startActivity(HomeActivity.createIntent(UserInformationActivity.this));
            finish();
        }
    }

    private void termDialog(View v) {
        DialogUtil.createTermDialog(v.getContext(), termItems, (dialog, which) -> {
            ((InputMethodManager) UserInformationActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(term.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
            term.setText(termItems[selectedPosition]);
            dialog.dismiss();
        });
    }

    private void dobDialog() {
        ((InputMethodManager) UserInformationActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(dob.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        DialogUtil.createDobDateDialog(UserInformationActivity.this, (view, year, month, dayOfMonth) -> {
            userDob = Calendar.getInstance();
            userDob.set(Calendar.YEAR, year);
            userDob.set(Calendar.MONTH, month);
            userDob.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dob.setText(DateUtils.formatDisplayDate(userDob));
        });
    }

    private void clearTerm() {
        StudentifyDatabaseProvider.getTaskDao(this).deleteAll();
        StudentifyDatabaseProvider.getTermDao(this).deleteAll();
        StudentifyDatabaseProvider.getStudentClassDao(this).deleteAll();
        term.setText(EMPTY);
    }

    public UserInformation createUserInformation() {
        UserInformation userInformation = new UserInformation();
        updateUserInformationWithFields(userInformation);
        return userInformation;
    }

    public boolean fieldCheck() {
        return name.getText().toString().isEmpty()
                || collegeName.getText().toString().isEmpty()
                || studentID.getText().toString().isEmpty()
                || phoneNumber.getText().toString().isEmpty()
                || dob.getText().toString().isEmpty()
                || address.getText().toString().isEmpty()
                || term.getText().toString().isEmpty();
    }

    public Term getTerm(String termName) {
        Term term = new Term();
        term.setName(termName);
        term.setStartDate(new Date());
        term.setEndDate(new Date());
        return term;
    }

    public void updateUserInformationWithFields(UserInformation userInformation) {
        userInformation.setName(name.getText().toString());
        userInformation.setCollegeName(collegeName.getText().toString());
        userInformation.setStudentId(studentID.getText().toString());
        userInformation.setPhoneNumber(phoneNumber.getText().toString());
        userInformation.setDateOfBirth(dob.getText().toString());
        userInformation.setAddress(address.getText().toString());
        userInformation.setTermName(term.getText().toString());
    }

    public void setUserInformation() {
        if (userInformation != null) {
            name.setText(userInformation.getName());
            collegeName.setText(userInformation.getCollegeName());
            studentID.setText(userInformation.getStudentId());
            phoneNumber.setText(userInformation.getPhoneNumber());
            dob.setText(userInformation.getDateOfBirth());
            address.setText(userInformation.getAddress());
            term.setText(userInformation.getTermName());
        }
    }
}
