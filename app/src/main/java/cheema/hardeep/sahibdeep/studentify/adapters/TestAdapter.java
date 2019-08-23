package cheema.hardeep.sahibdeep.studentify.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.activities.TasksDetailsActivity;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;

public class TestAdapter extends RecyclerView.Adapter<TestViewHolder> {
    List<Task> testList = new ArrayList<>();

    public void updateList(List<Task> testList){
        this.testList = testList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homework_test_item, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        Task task = testList.get(position);
        holder.homeworkname.setText(task.getName());
        holder.homeworkTime.setText(task.getDateTime().toString());
        holder.testView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TasksDetailsActivity.createIntent(v.getContext(),);
            }
        });
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }
}
class TestViewHolder extends RecyclerView.ViewHolder{
    ConstraintLayout testView;
    TextView homeworkname, homeworkDay, homeworkTime;
    public TestViewHolder(@NonNull View itemView) {
        super(itemView);
        homeworkname = itemView.findViewById(R.id.homeworkName);
        homeworkDay = itemView.findViewById(R.id.homeworkDay);
        homeworkTime = itemView.findViewById(R.id.homeworkTime);
        testView = itemView.findViewById(R.id.homeworkView);
    }
}
