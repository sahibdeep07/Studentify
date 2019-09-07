package cheema.hardeep.sahibdeep.studentify.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.adapters.ClassAdapter;
import cheema.hardeep.sahibdeep.studentify.adapters.ScheduleDaysAdapter;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.interfaces.ScheduleInterface;
import cheema.hardeep.sahibdeep.studentify.models.ScheduleDay;
import cheema.hardeep.sahibdeep.studentify.utils.DatabaseUtil;
import cheema.hardeep.sahibdeep.studentify.utils.DateUtils;

import static cheema.hardeep.sahibdeep.studentify.R2.id.date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment implements ScheduleInterface {

    private static final String WILDCARD = "%";
    private static final int NUMBER_OF_MONTHS = 3;

    @BindView(R.id.scheduleSemester)
    TextView semester;

    @BindView(R.id.daysRecyclerView)
    RecyclerView daysRecyclerView;

    @BindView(R.id.classesRecyclerView)
    RecyclerView classesRecyclerView;

    @BindView(R.id.noClassSchedule)
    TextView noClassSchedule;

    private ScheduleDaysAdapter daysAdapter;
    private ClassAdapter classAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this, view);

        semester.setText(DatabaseUtil.getUserInformation(getContext()).getTermName());

        daysRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        daysAdapter = new ScheduleDaysAdapter(this);
        daysAdapter.updateDaysList(generateDates());
        daysRecyclerView.setAdapter(daysAdapter);

        classesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        classAdapter = new ClassAdapter(true);
        classesRecyclerView.setAdapter(classAdapter);
        classAdapter.updateList(StudentifyDatabaseProvider
                .getStudentClassDao(getContext())
                .getStudentClassesWithDay(WILDCARD + getCurrentDay() + WILDCARD));
        if(!StudentifyDatabaseProvider
                .getStudentClassDao(getContext())
                .getStudentClassesWithDay(WILDCARD + getCurrentDay() + WILDCARD).isEmpty())
            noClassSchedule.setVisibility(View.GONE);
        return view;
    }

    // TODO: 26-08-2019  
    @Override
    public void onDaySelected(String day) {
        if(!StudentifyDatabaseProvider
                .getStudentClassDao(getContext())
                .getStudentClassesWithDay(WILDCARD + day + WILDCARD).isEmpty())
            noClassSchedule.setVisibility(View.GONE);
        classAdapter.updateList(StudentifyDatabaseProvider
                .getStudentClassDao(getContext())
                .getStudentClassesWithDay(WILDCARD + day + WILDCARD));
    }

    private List<ScheduleDay> generateDates() {
        List<ScheduleDay> scheduleDays = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);
        currentMonth = currentMonth + NUMBER_OF_MONTHS;
        while (currentMonth != cal.get(Calendar.MONTH)) {
            scheduleDays.add(new ScheduleDay(cal.get(Calendar.DATE), cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US)));
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return scheduleDays;
    }

    private String getCurrentDay(){
        Calendar cal = Calendar.getInstance();
        String currentDay = DateUtils.formatDisplayDay(cal);
        return currentDay;
    }
}
