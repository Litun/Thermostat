package ru.hse.thermostat;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Litun on 29.05.2015.
 */
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.IntervalViewHolder> {

    private Schedule schedule;

    public static class IntervalViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView text;
        Switch switchView;

        public IntervalViewHolder(View itemView) {
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.cardview_interval);
            text = (TextView) itemView.findViewById(R.id.text_interval);
            switchView = (Switch) itemView.findViewById(R.id.switch_interval);
        }
    }

    public ScheduleAdapter(Schedule schedule){
        this.schedule = schedule;
    }

    @Override
    public IntervalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        return new IntervalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IntervalViewHolder holder, int position) {
        holder.text.setText("[eqhghj");
    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }


}

