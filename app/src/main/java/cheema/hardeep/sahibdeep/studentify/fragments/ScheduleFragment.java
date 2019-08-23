package cheema.hardeep.sahibdeep.studentify.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.adapters.ClassAdapter;
import cheema.hardeep.sahibdeep.studentify.adapters.ScheduleDaysAdapter;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    TextView semester;
    RecyclerView daysRecyclerView, classesRecyclerView;
    ScheduleDaysAdapter daysAdapter;
    ClassAdapter classAdapter;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        semester = view.findViewById(R.id.scheduleSemester);
        daysRecyclerView = view.findViewById(R.id.daysRecyclerView);
        classesRecyclerView = view.findViewById(R.id.classesRecyclerView);
        daysRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        daysAdapter = new ScheduleDaysAdapter();
        daysAdapter.updateDaysList(getDays());
        daysRecyclerView.setAdapter(daysAdapter);
        classesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        classAdapter = new ClassAdapter();
//        classAdapter.updateList(StudentifyDatabaseProvider.getStudentClassDao(getContext()).getAllStudentClasses());
        classAdapter.updateScheduleList(StudentifyDatabaseProvider.getStudentClassDao(getContext()).getStudentClassesWithDay("%"+ScheduleDaysAdapter.selectedDay+"%"));
        classesRecyclerView.setAdapter(classAdapter);
        Toast.makeText(getContext(), daysAdapter.selectedDay, Toast.LENGTH_SHORT).show();
        return view;
    }

    public List<String> getDays(){
        List<String> days = new ArrayList<>();
        days.add("Mon");
        days.add("Tue");
        days.add("Wed");
        days.add("Thu");
        days.add("Fri");
        days.add("Sat");
        days.add("Mon");
        days.add("Tue");
        days.add("Wed");
        days.add("Thu");
        days.add("Fri");
        days.add("Sat");
        return days;
    }
}
