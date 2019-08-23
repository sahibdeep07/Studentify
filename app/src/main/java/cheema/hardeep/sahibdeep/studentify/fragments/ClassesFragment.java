package cheema.hardeep.sahibdeep.studentify.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.activities.ClassInformationActivity;
import cheema.hardeep.sahibdeep.studentify.activities.HomeActivity;
import cheema.hardeep.sahibdeep.studentify.adapters.ClassAdapter;
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.TermDetails;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.UserInformation;

public class ClassesFragment extends Fragment {

    TextView universityName, semester;
    ImageView addClassButton;
    RecyclerView classDetailRV;
    ClassAdapter classAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classes, container, false);
        addClassButton = view.findViewById(R.id.addClassButton);
        classDetailRV = view.findViewById(R.id.classDetailRecyclerView);
        semester = view.findViewById(R.id.semester);
        universityName = view.findViewById(R.id.universityName);
        universityName.setText(StudentifyDatabaseProvider.getUserInformationDao(getContext())
                .getUserInformation(SharedPreferencesProvider.getStudentId(getContext())).getCollegeName());
        semester.setText(StudentifyDatabaseProvider.getUserInformationDao(getContext())
                .getUserInformation(SharedPreferencesProvider.getStudentId(getContext())).getTermName());
        addClassButton.setOnClickListener(v -> startActivity(ClassInformationActivity.createIntent(view.getContext())));
        classDetailRV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        classAdapter = new ClassAdapter();
        classDetailRV.setAdapter(classAdapter);
//        SharedPreferencesProvider.saveUserId(getContext(), "123");
        Toast.makeText(getContext(), SharedPreferencesProvider.getStudentId(getContext()), Toast.LENGTH_SHORT).show();
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
//        String termName = userInformation.getTermName();
//        TermDetails termDetails = StudentifyDatabaseProvider.getTermDao(getContext()).getTermWithClasses(termName);
        classAdapter.updateList(StudentifyDatabaseProvider.getStudentClassDao(getContext()).getAllStudentClasses());
}
}
