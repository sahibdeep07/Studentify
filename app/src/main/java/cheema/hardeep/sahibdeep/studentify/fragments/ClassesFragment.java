package cheema.hardeep.sahibdeep.studentify.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.activities.ClassInformationActivity;
import cheema.hardeep.sahibdeep.studentify.adapters.ClassAdapter;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.interfaces.ClassesInterface;
import cheema.hardeep.sahibdeep.studentify.utils.DatabaseUtil;

public class ClassesFragment extends Fragment implements ClassesInterface {

    @BindView(R.id.universityName)
    TextView universityName;

    @BindView(R.id.semester)
    TextView semester;

    @BindView(R.id.addClassButton)
    ImageView addClassButton;

    @BindView(R.id.classDetailRecyclerView)
    RecyclerView classDetailRV;

    @BindView(R.id.noClass)
    TextView noClass;

    private ClassAdapter classAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classes, container, false);
        ButterKnife.bind(this, view);

        addClassButton.setOnClickListener(v -> startActivity(ClassInformationActivity.createIntent(view.getContext())));
        classDetailRV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        classAdapter = new ClassAdapter(false);
        classAdapter.setClassesInterface(this);
        classDetailRV.setAdapter(classAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        universityName.setText(DatabaseUtil.getUserInformation(getContext()).getCollegeName());
        refreshClasses();
    }

    @Override
    public void refreshClasses() {
        String termName = DatabaseUtil.getUserInformation(getContext()).getTermName();
        semester.setText(termName);
        classAdapter.updateList(StudentifyDatabaseProvider
                .getTermDao(getContext())
                .getTermWithClasses(termName)
                .getStudentClasses());

        if(!StudentifyDatabaseProvider
                .getTermDao(getContext())
                .getTermWithClasses(termName)
                .getStudentClasses().isEmpty())
            noClass.setVisibility(View.GONE);
    }
}
