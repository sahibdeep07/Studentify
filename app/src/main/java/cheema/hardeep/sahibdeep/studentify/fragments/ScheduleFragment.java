package cheema.hardeep.sahibdeep.studentify.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import cheema.hardeep.sahibdeep.studentify.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    TextView semester;
    RecyclerView daysRecyclerView, classesRecyclerView;

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
        return view;

    }

}
