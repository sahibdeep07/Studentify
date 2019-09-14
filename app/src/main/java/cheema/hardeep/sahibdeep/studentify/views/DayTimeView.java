package cheema.hardeep.sahibdeep.studentify.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.models.DayTime;
import cheema.hardeep.sahibdeep.studentify.utils.DateUtils;
import cheema.hardeep.sahibdeep.studentify.utils.DialogUtil;

public class DayTimeView extends LinearLayout {

    Calendar startTimeCalendar;
    Calendar endTimeCalendar;

    public DayTimeView(Context context) {
        super(context);
        init();
    }

    public DayTimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DayTimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
    }

    public void addRow(String day) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.day_time_custom_view, this, false);
        TextView textView = view.findViewById(R.id.dayLabel);
        TextView startTime = view.findViewById(R.id.startTime);
        TextView endTime = view.findViewById(R.id.endTime);

        startTime.setOnClickListener(v -> {
            startTimeCalendar = Calendar.getInstance();
            DialogUtil.createTimeDialog(getContext(), (dialog, hour, minute) -> {
                startTimeCalendar.set(Calendar.HOUR_OF_DAY, hour);
                startTimeCalendar.set(Calendar.MINUTE, minute);
                startTime.setText(DateUtils.formatDisplayTime(startTimeCalendar));
            });
        });

        endTime.setOnClickListener(v -> {
            endTimeCalendar = Calendar.getInstance();
            DialogUtil.createTimeDialog(getContext(), (dialog, hour, minute) -> {
                endTimeCalendar.set(Calendar.HOUR_OF_DAY, hour);
                endTimeCalendar.set(Calendar.MINUTE, minute);
                endTime.setText(DateUtils.formatDisplayTime(endTimeCalendar));
            });
        });

        textView.setText(day);
        addView(view);
    }

    public boolean isValid() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            TextView startTime = view.findViewById(R.id.startTime);
            TextView endTime = view.findViewById(R.id.endTime);
            if (startTime.getText().toString().isEmpty() || endTime.getText().toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void deleteRow(String day) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            TextView textView = view.findViewById(R.id.dayLabel);
            if (textView.getText().toString().equals(day)) {
                removeViewAt(i);
            }
        }
    }

    public List<DayTime> getData() {
        List<DayTime> dayTimes = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            TextView day = view.findViewById(R.id.dayLabel);
            TextView startTime = view.findViewById(R.id.startTime);
            TextView endTime = view.findViewById(R.id.endTime);
            dayTimes.add(new DayTime(day.getText().toString(), startTime.getText().toString(), endTime.getText().toString()));
        }
        return dayTimes;
    }
}
