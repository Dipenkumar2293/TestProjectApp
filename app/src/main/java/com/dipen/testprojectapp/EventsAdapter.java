package com.dipen.testprojectapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventHolder> {

    private List<Events> eventsList =  new ArrayList<>();

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_daily_list,
                parent,false);
        return new EventHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Events currentEvent = eventsList.get(position);
        holder.sTime.setText(currentEvent.getStartTime());
        holder.eTime.setText(currentEvent.getEndTime());
        holder.eventText.setText(currentEvent.getName());
        holder.dateText.setText(currentEvent.getDate());
        holder.categoryList.setText(currentEvent.getCategory());
        holder.isPriority = currentEvent.getIsPriority();
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public void setEventsList(List<Events> eventsList) {
        this.eventsList = eventsList;
        notifyDataSetChanged();
    }
    public Events getEventAt(int position){
        return eventsList.get(position);
    }



    class EventHolder extends RecyclerView.ViewHolder{
        private TextView sTime;
        private TextView eTime;
        private TextView dateText;
        private TextView eventText;
        private TextView categoryList;
        private String isPriority;


        public EventHolder(@NonNull View itemView) {
            super(itemView);
            sTime = itemView.findViewById(R.id.startTimer);
            eTime = itemView.findViewById(R.id.endTimer);
            eventText = itemView.findViewById(R.id.event_Content);
            dateText = itemView.findViewById(R.id.dateShow);
            categoryList = itemView.findViewById(R.id.categ_show);
        }
    }
}
