package cheema.hardeep.sahibdeep.studentify.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.activities.TasksDetailsActivity;
import cheema.hardeep.sahibdeep.studentify.interfaces.ViewRefreshInterface;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;
import cheema.hardeep.sahibdeep.studentify.utils.DateUtils;
import cheema.hardeep.sahibdeep.studentify.utils.DialogUtil;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> testList = new ArrayList<>();
    private boolean isHomework;
    private ViewRefreshInterface viewRefreshInterface;

    public TaskAdapter(ViewRefreshInterface viewRefreshInterface, boolean isHomework) {
        this.viewRefreshInterface = viewRefreshInterface;
        this.isHomework = isHomework;
    }

    public void updateList(List<Task> testList) {
        this.testList = testList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homework_test_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = testList.get(position);
        holder.name.setText(task.getName());
        holder.time.setText(DateUtils.formatDisplayDateTime(task.getDateTime()));
        holder.itemView.setOnClickListener(v -> v.getContext().startActivity(TasksDetailsActivity.createIntent(v.getContext(), isHomework, task.getStudentClassId(), task.getId())));

        holder.itemView.setOnLongClickListener(view -> {
            DialogUtil.createDeleteConfirmationDialog(view.getContext(),
                    (dialogInterface, i) -> {
                        StudentifyDatabaseProvider.getTaskDao(view.getContext()).deleteTask(task);
                        if (isHomework) {
                            viewRefreshInterface.refreshHomework(true);
                        } else {
                            viewRefreshInterface.refreshTest(true);
                        }
                    });
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView name;
        TextView time;

        TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.homeworkName);
            time = itemView.findViewById(R.id.homeworkTime);
            this.itemView = itemView;
        }
    }
}
