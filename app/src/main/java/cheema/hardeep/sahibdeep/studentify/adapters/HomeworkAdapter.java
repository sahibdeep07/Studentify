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

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkViewHolder> {
    List<Task> homeworkList = new ArrayList<>();

    public void updateList(List<Task> homeworkList){
        this.homeworkList = homeworkList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homework_test_item, parent, false);
        return new HomeworkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder holder, int position) {
        Task task = homeworkList.get(position);
        holder.homeworkname.setText(task.getName());
        holder.homeworkTime.setText(task.getDateTime().toString());
        holder.homeworkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TasksDetailsActivity.createIntent(v.getContext(),);
            }
        });

    }

    @Override
    public int getItemCount() {
        return homeworkList.size();
    }
}

class HomeworkViewHolder extends RecyclerView.ViewHolder{
    ConstraintLayout homeworkView;
    TextView homeworkname, homeworkDay, homeworkTime;
    public HomeworkViewHolder(@NonNull View itemView) {
        super(itemView);
        homeworkname = itemView.findViewById(R.id.homeworkName);
        homeworkDay = itemView.findViewById(R.id.homeworkDay);
        homeworkTime = itemView.findViewById(R.id.homeworkTime);
        homeworkView = itemView.findViewById(R.id.homeworkView);
    }
}