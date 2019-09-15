package cheema.hardeep.sahibdeep.studentify.adapters;

import android.text.TextUtils;
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
import cheema.hardeep.sahibdeep.studentify.models.DayTime;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.utils.DateUtils;
import cheema.hardeep.sahibdeep.studentify.utils.DialogUtil;


public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    private static final String HYPEN = " - ";
    private static final String COMPLETED = "Completed: ";
    private static final String FINISHED = "Finished: ";
    private static final String SLASH = "/";
    private static final String COLON = ": ";
    private static final String NEW_LINE = "\n";
    private boolean isSchedule;

    private List<StudentClass> studentClassList = new ArrayList<>();
    private String selectedDay;
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

    public void updateListWithDays(List<StudentClass> studentClassList, String selectedDay) {
        this.studentClassList = studentClassList;
        this.selectedDay = selectedDay;
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
        holder.time.setText(isSchedule ? getTimeForSchedule(studentClass) : getStartEndDisplayTime(studentClass));
        holder.test.setText(getCompletedTestSting(studentClass));
        holder.homework.setText(getFinishedHomeworkString(studentClass));
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

    private String getTimeForSchedule(StudentClass studentClass) {
        for (DayTime dayTime : studentClass.getDayTimes()) {
            if (dayTime.getDay().equals(selectedDay)) {
                return dayTime.getStartTime() + HYPEN + dayTime.getEndTime();
            }
        }
        return null;
    }

    private String getCompletedTestSting(StudentClass studentClass) {
        return COMPLETED + studentClass.getCompletedTest() + SLASH + studentClass.getTotalTest();
    }

    private String getFinishedHomeworkString(StudentClass studentClass) {
        return FINISHED + studentClass.getFinishedHomework() + SLASH + studentClass.getTotalHomework();
    }

    private String getStartEndDisplayTime(StudentClass studentClass) {
        StringBuilder stringBuilder = new StringBuilder();
        for (DayTime dayTime : studentClass.getDayTimes()) {
            stringBuilder
                    .append(dayTime.getDay().charAt(0))
                    .append(COLON)
                    .append(dayTime.getStartTime())
                    .append(HYPEN)
                    .append(dayTime.getEndTime())
                    .append(NEW_LINE);
        }
        return stringBuilder.toString();
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
