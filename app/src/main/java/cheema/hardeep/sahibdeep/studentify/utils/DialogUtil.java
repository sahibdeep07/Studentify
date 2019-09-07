package cheema.hardeep.sahibdeep.studentify.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TimePicker;

import java.util.Calendar;

public class DialogUtil {

    private static final String CHOOSE_SEMESTER = "Choose Semester";
    private static final String DELETE = "Delete";
    private static final String DELETE_MESSAGE = "Are you sure you want to delete?";
    private static final String YES = "Yes";
    private static final String CANCEL = "Cancel";
    private static final int THOUSAND = 1000;

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
}
