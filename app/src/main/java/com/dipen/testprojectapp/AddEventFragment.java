package com.dipen.testprojectapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEventFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private EventsViewModel evModel;
    private Button addBtn;
    private EditText edit;
    static TextView from;
    static TextView to;
    static TextView date;
    static boolean onclicked;
    static Spinner spin;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Object TimePickerFragment;

    public AddEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddEventFragment newInstance(String param1, String param2) {
        AddEventFragment fragment = new AddEventFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_event, container, false);
        // Inflate the layout for this fragment
        edit = v.findViewById(R.id.input_event);
        from = v.findViewById(R.id.fromView);
        to = v.findViewById(R.id.toView);
        date = v.findViewById(R.id.dateView);
        spin = v.findViewById(R.id.list_spinner);
        addBtn = v.findViewById(R.id.save_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               insertDatatoDatabase();

            }
        });

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclicked = true;
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getChildFragmentManager(), "timePicker");
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclicked = false;
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getChildFragmentManager(), "timePicker");
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getChildFragmentManager(), "datePicker");
            }
        });

        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource( getContext(),
                R.array.weekly_list, android.R.layout.simple_spinner_dropdown_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        return v;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        evModel = new ViewModelProvider(getActivity()).get(EventsViewModel.class);
        evModel.getAllEvents().observe(getViewLifecycleOwner(), new Observer<List<Events>>() {
            @Override
            public void onChanged(List<Events> events) {

                //Toast.makeText(getContext(),"Hi Guys!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void insertDatatoDatabase(){
        String eventName = edit.getText().toString();
        String fromTime = from.getText().toString();
        String toTime = to.getText().toString();
        String newDate = date.getText().toString();
        String category = spin.getSelectedItem().toString();

        if(checkInput(eventName,fromTime,toTime,newDate,category)){
            Events data = new Events(eventName,fromTime,toTime,newDate,category);
            evModel.insert(data);
            Toast.makeText(requireContext(), "Successfully Added",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(requireContext(), "Please Enter All Data",Toast.LENGTH_SHORT).show();
        }

    }
    private boolean checkInput(String eventName, String sTime, String etime,String dates,String category){
        if ((TextUtils.isEmpty(eventName) || TextUtils.isEmpty(sTime) || TextUtils.isEmpty(etime)
               || TextUtils.isEmpty(dates) || TextUtils.isEmpty(category))){
            return false;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));

        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
           if(onclicked == true) {
               if (minute < 10) {
                   from.setText(hourOfDay + ":0" + minute);
                   // String str = time.getText().toString();
               } else {
                   from.setText(hourOfDay + ":" + minute);
               }
           }else {
               if (minute < 10) {
                   to.setText(hourOfDay + ":0" + minute);
                   // String str = time.getText().toString();
               } else {
                   to.setText(hourOfDay + ":" + minute);
               }
           }
        }



    }
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        public static String dater;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            date.setText(String.valueOf(year) + "/" + String.valueOf(month) + "/" +  String.valueOf(day));

        }
    }


}
