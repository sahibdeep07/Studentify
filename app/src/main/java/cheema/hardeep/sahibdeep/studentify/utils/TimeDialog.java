package cheema.hardeep.sahibdeep.studentify.utils;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.TimePicker;

import java.util.Calendar;

import cheema.hardeep.sahibdeep.studentify.R2;

public class TimeDialog {
    public static void createTimeDialog(Context context){
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        TimePickerDialog TimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                eReminderTime.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, false);
        TimePicker.setTitle("Select Time");
        TimePicker.show();
    }
}
