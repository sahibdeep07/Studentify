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
import cheema.hardeep.sahibdeep.studentify.interfaces.ScheduleInterface;
import cheema.hardeep.sahibdeep.studentify.models.ScheduleDay;

public class ScheduleDaysAdapter extends RecyclerView.Adapter<ScheduleDaysAdapter.ScheduleDaysViewHolder> {

    private List<ScheduleDay> scheduleDays = new ArrayList<>();
    private ScheduleInterface scheduleInterface;

    public ScheduleDaysAdapter(ScheduleInterface scheduleInterface) {
        this.scheduleInterface = scheduleInterface;
    }

    public void updateDaysList(List<ScheduleDay> scheduleDays) {
        this.scheduleDays = scheduleDays;
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
        ScheduleDay scheduleDay = scheduleDays.get(position);
        holder.day.setText(scheduleDay.getDay());
        holder.date.setText(String.valueOf(scheduleDay.getDate()));
        holder.itemView.setOnClickListener(v -> scheduleInterface.onDaySelected(scheduleDay.getDay()));
    }

    @Override
    public int getItemCount() {
        return scheduleDays.size();
    }

    class ScheduleDaysViewHolder extends RecyclerView.ViewHolder {
        TextView day;
        TextView date;
        View itemView;

        ScheduleDaysViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            day = itemView.findViewById(R.id.day);
            this.itemView = itemView;
        }
    }
}
