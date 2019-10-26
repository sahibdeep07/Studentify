package cheema.hardeep.sahibdeep.studentify.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabase;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.Term;
import cheema.hardeep.sahibdeep.studentify.models.tables.UserInformation;
import cheema.hardeep.sahibdeep.studentify.utils.DatabaseUtil;
import cheema.hardeep.sahibdeep.studentify.utils.DateUtils;
import cheema.hardeep.sahibdeep.studentify.utils.DialogUtil;

public class UserInformationActivity extends AppCompatActivity implements DialogUtil.InputDialogInterface {

    public static Intent createIntent(Context context) {
        return new Intent(context, UserInformationActivity.class);
    }

    public static final String EMPTY = "";

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
    Calendar startDate = Calendar.getInstance();
    Calendar endDate = Calendar.getInstance();

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

        termStartDate.setVisibility(term.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
        termStartDate.setOnClickListener(v -> DialogUtil.createDobDateDialog(v.getContext(), (view, year, month, dayOfMonth) -> {
            startDate.set(Calendar.YEAR, year);
            startDate.set(Calendar.MONTH, month);
            startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            termStartDate.setText(DateUtils.formatDisplayDate(startDate));
        }));

        termEndDate.setVisibility(term.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
        termEndDate.setOnClickListener(v -> DialogUtil.createDobDateDialog(v.getContext(), (view, year, month, dayOfMonth) -> {
            endDate.set(Calendar.YEAR, year);
            endDate.set(Calendar.MONTH, month);
            endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            termEndDate.setText(DateUtils.formatDisplayDate(endDate));
        }));

        addButton.setOnClickListener(v -> DialogUtil.createInputDialog(this, this));
    }

    @Override
    public void onTermNameSave(String termName) {
        if(!termName.isEmpty()) {
            term.setText(termName);
            termStartDate.setVisibility(View.VISIBLE);
            termEndDate.setVisibility(View.VISIBLE);
            termStartDate.setText(EMPTY);
            termEndDate.setText(EMPTY);
        } else {
            Toast.makeText(this, "Empty term name is not accepted", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleSaveUpdateButton() {
        if (fieldCheck()) {
            Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
        } else {
            if (SharedPreferencesProvider.isFirstLaunch(this)) {
                UserInformation userInfo = createUserInformation();
                StudentifyDatabaseProvider.getUserInformationDao(this).insertUserInformation(userInfo);
                StudentifyDatabaseProvider.getTermDao(this).insertTerm(getTerm());
                SharedPreferencesProvider.saveUserId(this, userInfo.getStudentId());
                SharedPreferencesProvider.saveFirstLaunchCompleted(this);
            } else {
                updateUserInformationWithFields(userInformation);
                StudentifyDatabaseProvider.getUserInformationDao(this).updateUserInformation(userInformation);
                StudentifyDatabaseProvider.getTermDao(this).insertTerm(getTerm());
            }
            startActivity(HomeActivity.createIntent(UserInformationActivity.this));
            finish();
        }
    }

    private void termDialog(View v) {
        List<Term> allTerms = StudentifyDatabaseProvider.getTermDao(this).getAllTerms();
        String[] termNames = new String[allTerms.size()];
        for (int i = 0; i < allTerms.size(); i++) {
            termNames[i] = allTerms.get(i).getName();
        }

        if (termNames.length == 0) {
            Toast.makeText(this, "There are no terms available", Toast.LENGTH_SHORT).show();
        } else {
            DialogUtil.createTermDialog(v.getContext(), termNames, (dialog, which) -> {
                ((InputMethodManager) UserInformationActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(term.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                String termName = termNames[selectedPosition];
                term.setText(termName);
                Term termDb = StudentifyDatabaseProvider.getTermDao(this).getTerm(termName);
                term.setText(termDb.getName());
                termStartDate.setText(DateUtils.formatDisplayDate(termDb.getStartDate()));
                termEndDate.setText(DateUtils.formatDisplayDate(termDb.getEndDate()));

                //Update UserInformation TermName
                UserInformation userInformation = DatabaseUtil.getUserInformation(this);
                userInformation.setTermName(termDb.getName());
                StudentifyDatabaseProvider.getUserInformationDao(this).updateUserInformation(userInformation);
                dialog.dismiss();
            });
        }
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
                || term.getText().toString().isEmpty()
                || termStartDate.getText().toString().isEmpty()
                || termEndDate.getText().toString().isEmpty();
    }

    public Term getTerm() {
        Term result = new Term();
        result.setName(term.getText().toString());
        result.setStartDate(startDate.getTime());
        result.setEndDate(endDate.getTime());
        return result;
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
            Term term = StudentifyDatabaseProvider.getTermDao(this).getTerm(userInformation.getTermName());
            termStartDate.setText(DateUtils.formatDisplayDate(term.getStartDate()));
            termEndDate.setText(DateUtils.formatDisplayDate(term.getEndDate()));
        }
    }
}
