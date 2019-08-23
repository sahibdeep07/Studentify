package cheema.hardeep.sahibdeep.studentify.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.activities.TasksActivity;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    public List<StudentClass> studentClassList = new ArrayList<>();

    public void updateList(List<StudentClass> studentClassList){
        this.studentClassList = studentClassList;
        notifyDataSetChanged();
    }

    public void updateScheduleList(List<StudentClass> studentClassList){
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
        holder.time.setText("None");
        holder.test.setText("None");
        holder.homework.setText("None");
        holder.classView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(TasksActivity.createIntent(v.getContext()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentClassList.size();
    }

    class ClassViewHolder extends RecyclerView.ViewHolder{
        TextView classTitle, professorName, roomNumber, time, homework, test;
        ConstraintLayout classView;
        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            classTitle = itemView.findViewById(R.id.classTitle);
            professorName = itemView.findViewById(R.id.itemProfessorName);
            roomNumber = itemView.findViewById(R.id.itemRoomNumber);
            time = itemView.findViewById(R.id.itemTimeResult);
            homework = itemView.findViewById(R.id.itemHomeworkResult);
            test = itemView.findViewById(R.id.itemTestsResult);
            classView = itemView.findViewById(R.id.classesItemView);
        }
    }
}
