package cheema.hardeep.sahibdeep.studentify.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import cheema.hardeep.sahibdeep.studentify.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassesFragment extends Fragment {


    public ClassesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_classes, container, false);
    }

}
