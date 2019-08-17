package cheema.hardeep.sahibdeep.studentify.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import cheema.hardeep.sahibdeep.studentify.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassesFragment extends Fragment {

    TextView universityName, semester;
    Button addClassButton;
    RecyclerView classDetailRV;

    public ClassesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classes, container, false);
        addClassButton = view.findViewById(R.id.addClassButton);
        classDetailRV = view.findViewById(R.id.classDetailRecyclerView);
        semester = view.findViewById(R.id.semester);
        universityName = view.findViewById(R.id.universityName);
        return view;
    }

}
