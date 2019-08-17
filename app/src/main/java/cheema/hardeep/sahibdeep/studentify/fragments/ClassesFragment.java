package cheema.hardeep.sahibdeep.studentify.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.activities.HomeActivity;
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.TermDetails;
import cheema.hardeep.sahibdeep.studentify.models.tables.UserInformation;

public class ClassesFragment extends Fragment {

    TextView universityName, semester;
    ImageView addClassButton;
    RecyclerView classDetailRV;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        String studentID = SharedPreferencesProvider.getStudentId(getContext());
        UserInformation userInformation = StudentifyDatabaseProvider.getUserInformationDao(getContext()).getUserInformation(studentID);
        String termName = userInformation.getTermName();
        TermDetails termDetails = StudentifyDatabaseProvider.getTermDao(getContext()).getTermWithClasses(termName);
}
}
