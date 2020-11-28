package com.dipen.testprojectapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EventsViewModel extends AndroidViewModel {

    private EventRepository repository;
    private LiveData<List<Events>> allEvents;
    private LiveData<List<Events>> dailyEvents;
    private LiveData<List<Events>> weeklyEvents;

    public EventsViewModel(@NonNull Application application) {
        super(application);
        repository = new EventRepository(application);
        allEvents = repository.getAllEvents();
        dailyEvents = repository.getDailyEvents();
        weeklyEvents = repository.getWeeklyEvents();
    }
    public void insert(Events events){
        repository.insert(events);
    }
    public void update(Events events){
        repository.update(events);
    }
    public void delete(Events events){
        repository.delete(events);
    }

    public LiveData<List<Events>> getAllEvents() {
        return allEvents;
    }

    public LiveData<List<Events>> getDailyEvents() {
        return dailyEvents;
    }

    public LiveData<List<Events>> getWeeklyEvents() {
        return weeklyEvents;
    }
}
