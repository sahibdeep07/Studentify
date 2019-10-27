package cheema.hardeep.sahibdeep.studentify.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.Term;
import cheema.hardeep.sahibdeep.studentify.utils.DatabaseUtil;
import cheema.hardeep.sahibdeep.studentify.utils.DateUtils;
import cheema.hardeep.sahibdeep.studentify.utils.SortingUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment implements ScheduleInterface {

    private static final String WILDCARD = "%";
    private static final int NUMBER_OF_MONTHS = 3;
    private static final int SCROLL_OFFSET = 4;

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
    private int scrollPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this, view);

        semester.setText(DatabaseUtil.getUserInformation(getContext()).getTermName());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        daysRecyclerView.setLayoutManager(layoutManager);
        daysAdapter = new ScheduleDaysAdapter(this);
        daysAdapter.updateDaysList(generateDates());
        daysRecyclerView.setAdapter(daysAdapter);
        daysRecyclerView.smoothScrollToPosition(scrollPosition + SCROLL_OFFSET);

        classesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        classAdapter = new ClassAdapter(true);
        classesRecyclerView.setAdapter(classAdapter);
        initializeView(getCurrentDay());
        return view;
    }

    private void initializeView(String day) {
        List<StudentClass> studentClassesWithDay = StudentifyDatabaseProvider
                .getStudentClassDao(getContext())
                .getStudentClassesWithDay(WILDCARD + day + WILDCARD);
        if (studentClassesWithDay.isEmpty()) {
            noClassSchedule.setVisibility(View.VISIBLE);
            classesRecyclerView.setVisibility(View.GONE);
        } else {
            noClassSchedule.setVisibility(View.GONE);
            classesRecyclerView.setVisibility(View.VISIBLE);
            classAdapter.updateListWithDays(SortingUtil.sortList(studentClassesWithDay, day), day);
        }
    }

    @Override
    public void onDaySelected(String day) {
        initializeView(day);
    }

    private List<ScheduleDay> generateDates() {
        Term term = DatabaseUtil.getUserTerm(getContext());
        List<ScheduleDay> scheduleDays = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(term.getStartDate());
        int index = 0;

        Calendar today = Calendar.getInstance();
        while (cal.getTime().before(term.getEndDate())) {
            scheduleDays.add(new ScheduleDay(cal.get(Calendar.DATE), cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US)));
            cal.add(Calendar.DATE, 1);

            //Scroll Position to Today's Date
            index++;
            if(cal.get(Calendar.MONTH) == today.get(Calendar.MONTH) && cal.get(Calendar.DATE) == today.get(Calendar.DATE)) scrollPosition = index;
        }
        return scheduleDays;
    }

    private String getCurrentDay() {
        Calendar cal = Calendar.getInstance();
        String currentDay = DateUtils.formatDisplayDay(cal);
        return currentDay;
    }
}
