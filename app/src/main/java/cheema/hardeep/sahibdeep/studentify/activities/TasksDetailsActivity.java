package cheema.hardeep.sahibdeep.studentify.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cheema.hardeep.sahibdeep.studentify.R;

public class TasksDetailsActivity extends AppCompatActivity {

    public static Intent createIntent(Context context){
        return new Intent(context, TasksDetailsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_details);
        getSupportActionBar().hide();
    }
}
