package cheema.hardeep.sahibdeep.studentify.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import cheema.hardeep.sahibdeep.studentify.R;

public class DialogUtil {

    private static final String CHOOSE_SEMESTER = "Choose Semester";
    private static final String DELETE = "Delete";
    private static final String DELETE_MESSAGE = "Are you sure you want to delete?";
    private static final String YES = "Yes";
    private static final String CANCEL = "Cancel";
    private static final String SAVE = "Save";
    private static final int THOUSAND = 1000;
    private static final int DIALOG_SPACING_SIDES = 60;
    private static final int DIALOG_SPACING_LENGTH = 20;
    private static final float DIALOG_TEXT_SIZE = 20f;
    private static final String DIALOG_TERM_NAME = "Term Name";

    public interface InputDialogInterface {
        void onTermNameSave(String termName);
    }

    public static void createTermDialog(Context context, CharSequence[] items, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(CHOOSE_SEMESTER)
                .setSingleChoiceItems(items, -1, listener)
                .show();
    }

    public static void createDeleteConfirmationDialog(Context context, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(DELETE)
                .setMessage(DELETE_MESSAGE)
                .setPositiveButton(YES, listener)
                .setNegativeButton(CANCEL, null)
                .show();
    }

    public static void createTimeDialog(Context context, TimePickerDialog.OnTimeSetListener listener) {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        TimePickerDialog timePicker = new TimePickerDialog(context, listener, hour, minute, false);
        timePicker.show();
    }

    public static void createDateDialog(Context context, DatePickerDialog.OnDateSetListener listener) {
        Calendar currentTime = Calendar.getInstance();
        int year = currentTime.get(Calendar.YEAR);
        int month = currentTime.get(Calendar.MONTH);
        int dayOfMonth = currentTime.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker = new DatePickerDialog(context, listener, year, month, dayOfMonth);
        datePicker.getDatePicker().setMinDate(currentTime.getTimeInMillis() - THOUSAND);
        datePicker.show();
    }

    public static void createDobDateDialog(Context context, DatePickerDialog.OnDateSetListener listener) {
        Calendar currentTime = Calendar.getInstance();
        int year = currentTime.get(Calendar.YEAR);
        int month = currentTime.get(Calendar.MONTH);
        int dayOfMonth = currentTime.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker = new DatePickerDialog(context, listener, year, month, dayOfMonth);
        datePicker.show();
    }

    public static void createInputDialog(Context context, InputDialogInterface inputDialogInterface) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(DIALOG_TERM_NAME);

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setTextSize(DIALOG_TEXT_SIZE);
        input.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.colorPrimaryDark)));

        builder.setPositiveButton(SAVE, (dialog, which) -> inputDialogInterface.onTermNameSave(input.getText().toString()));
        builder.setNegativeButton(CANCEL, (dialog, which) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.setView(input, DIALOG_SPACING_SIDES, DIALOG_SPACING_LENGTH, DIALOG_SPACING_SIDES, DIALOG_SPACING_LENGTH);
        alertDialog.show();
    }
}
