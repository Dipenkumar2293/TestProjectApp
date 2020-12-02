package com.dipen.testprojectapp;

import android.content.ClipData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PriorityListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PriorityListFragment extends Fragment {

    final EventsAdapter adapter = new EventsAdapter();
    private EventsViewModel eventsViewModel;
    private RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PriorityListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PriorityListFragment.
     */
    // TODO: Rename and change types and number of parameters
    @SuppressWarnings("unused")
    public static PriorityListFragment newInstance(String param1, String param2) {
        PriorityListFragment fragment = new PriorityListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);

        recyclerView = view.findViewById(R.id.priority_list);
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
        eventsViewModel.getPriorityEvents().observe(getViewLifecycleOwner(), new Observer<List<Events>>() {
            @Override
            public void onChanged(List<Events> events) {
               // Log.d("potato", String.valueOf(events.get(0).getPriority()));
                adapter.setEventsList(events);
            }
        });
    }
}