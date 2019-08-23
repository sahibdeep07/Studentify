package cheema.hardeep.sahibdeep.studentify.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cheema.hardeep.sahibdeep.studentify.R;
import cheema.hardeep.sahibdeep.studentify.fragments.ScheduleFragment;

public class ScheduleDaysAdapter extends RecyclerView.Adapter<ScheduleDaysViewHolder> {
    List<String> daysList = new ArrayList<>();
    public static String selectedDay = new String();

    public void updateDaysList(List<String> daysList){
        this.daysList = daysList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScheduleDaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_days_item, parent, false);
        return new ScheduleDaysViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleDaysViewHolder holder, int position) {
        String d = daysList.get(position);
        holder.day.setText(d);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDay = holder.day.getText().toString();
                Toast.makeText(v.getContext(), ""+holder.day.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return daysList.size();
    }
}

class ScheduleDaysViewHolder extends RecyclerView.ViewHolder{
    TextView day, date;
    ConstraintLayout view;
    public ScheduleDaysViewHolder(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date);
        day = itemView.findViewById(R.id.day);
        view = itemView.findViewById(R.id.scheduleDaysView);
    }
}
