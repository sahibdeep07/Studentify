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
import cheema.hardeep.sahibdeep.studentify.activities.TasksActivity;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.interfaces.ClassesInterface;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.utils.DateUtils;
import cheema.hardeep.sahibdeep.studentify.utils.DialogUtil;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    private static final String NONE = "None";
    private static final String HYPEN = " - ";
    private boolean isSchedule;

    private List<StudentClass> studentClassList = new ArrayList<>();
    private ClassesInterface classesInterface;

    public ClassAdapter(boolean isSchedule) {
        this.isSchedule = isSchedule;
    }

    public void setClassesInterface(ClassesInterface classesInterface) {
        this.classesInterface = classesInterface;
    }

    public void updateList(List<StudentClass> studentClassList) {
        this.studentClassList = studentClassList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classes_item, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        StudentClass studentClass = studentClassList.get(position);
        holder.classTitle.setText(studentClass.getName());
        holder.professorName.setText(studentClass.getProfessorName());
        holder.roomNumber.setText(studentClass.getRoomNumber());
        holder.time.setText(getStartEndDisplayTime(studentClass));
        holder.test.setText(NONE);
        holder.homework.setText(NONE);
        holder.itemView.setOnClickListener(v ->
                v.getContext().startActivity(TasksActivity.createIntent(v.getContext(), studentClass.getId()))
        );

        if (!isSchedule) {
            holder.itemView.setOnLongClickListener(v -> {
                DialogUtil.createDeleteConfirmationDialog(v.getContext(),
                        (dialogInterface, i) -> {
                            StudentifyDatabaseProvider.getStudentClassDao(v.getContext()).deleteStudentClass(studentClass);
                            if (classesInterface != null) classesInterface.refreshClasses();
                        });
                return true;
            });
        }
    }

    private String getStartEndDisplayTime(StudentClass studentClass) {
        return DateUtils.formatDisplayTime(studentClass.getStartTime()) + HYPEN + DateUtils.formatDisplayTime(studentClass.getEndTime());
    }

    @Override
    public int getItemCount() {
        return studentClassList.size();
    }

    class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView classTitle;
        TextView professorName;
        TextView roomNumber;
        TextView time;
        TextView homework;
        TextView test;
        View itemView;

        ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            classTitle = itemView.findViewById(R.id.classTitle);
            professorName = itemView.findViewById(R.id.itemProfessorName);
            roomNumber = itemView.findViewById(R.id.itemRoomNumber);
            time = itemView.findViewById(R.id.itemTimeResult);
            homework = itemView.findViewById(R.id.itemHomeworkResult);
            test = itemView.findViewById(R.id.itemTestsResult);
            this.itemView = itemView;
        }
    }
}
