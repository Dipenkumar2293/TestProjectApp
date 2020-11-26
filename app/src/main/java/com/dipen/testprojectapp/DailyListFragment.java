package com.dipen.testprojectapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderKt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class DailyListFragment extends Fragment {

     private EventsViewModel eventsViewModel;
     private RecyclerView recyclerView;

    // TODO: Customize parameter argument names


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DailyListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DailyListFragment newInstance(int columnCount) {
        DailyListFragment fragment = new DailyListFragment();
        Bundle args = new Bundle();
       // args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_list_list, container, false);
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EventsAdapter adapter = new EventsAdapter();
        recyclerView.setAdapter(adapter);
        eventsViewModel = new ViewModelProvider(getActivity()).get(EventsViewModel.class);
        eventsViewModel.getAllEvents().observe(getViewLifecycleOwner(), new Observer<List<Events>>() {
            @Override
            public void onChanged(List<Events> events) {
                adapter.setEventsList(events);
                //Toast.makeText(getContext(),"Hi Guys!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}