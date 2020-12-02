package com.dipen.testprojectapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 */
public class MonthlyListFragment extends Fragment {

    final EventsAdapter adapter = new EventsAdapter();
    private EventsViewModel eventsViewModel;
    private RecyclerView recyclerView;
    private String date = "";

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MonthlyListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MonthlyListFragment newInstance(int columnCount) {
        MonthlyListFragment fragment = new MonthlyListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = this.getArguments();
        if(b != null) {
            date = b.getString("date");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly_list_list, container, false);

        recyclerView = view.findViewById(R.id.monthly_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                /*eventsViewModel.delete(adapter.getEventAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Event Deleted", Toast.LENGTH_SHORT).show();*/

                if(direction == ItemTouchHelper.RIGHT){
                    eventsViewModel.delete(adapter.getEventAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(getContext(), "Event Deleted", Toast.LENGTH_SHORT).show();
                }
                else if (direction == ItemTouchHelper.LEFT){
                    eventsViewModel.update(adapter.getEventAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(getContext(), "Priority List Updated", Toast.LENGTH_SHORT).show();
                }
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setAdapter(adapter);
        eventsViewModel = new ViewModelProvider(getActivity()).get(EventsViewModel.class);
        eventsViewModel.getAllEvents().observe(getViewLifecycleOwner(), new Observer<List<Events>>() {
            @Override
            public void onChanged(List<Events> events) {
                String d;
                List<Events> mEvents = new ArrayList<Events>();
                for(Events event: events) {
                    d = event.getDate().toString();
                    if(d.equals(date)) {
                        mEvents.add(event);
                    }
                }
                adapter.setEventsList(mEvents);
            }
        });
    }
}