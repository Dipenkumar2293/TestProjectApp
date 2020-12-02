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
    private LiveData<List<Events>> ed_weeklyEvents;
    private LiveData<List<Events>> pers_weeklyEvents;
    private LiveData<List<Events>> pro_weeklyEvents;
    private LiveData<List<Events>> fit_weeklyEvents;
    private LiveData<List<Events>> oth_weeklyEvents;
    private LiveData<List<Events>> examEvents;
    private LiveData<List<Events>> priorityEvents;

    public EventsViewModel(@NonNull Application application) {
        super(application);
        repository = new EventRepository(application);
        allEvents = repository.getAllEvents();
        dailyEvents = repository.getDailyEvents();
        ed_weeklyEvents = repository.getEdWeeklyEvents();
        pers_weeklyEvents = repository.getPersWeeklyEvents();
        pro_weeklyEvents = repository.getProWeeklyEvents();
        fit_weeklyEvents = repository.getFitWeeklyEvents();
        oth_weeklyEvents = repository.getOthWeeklyEvents();
        examEvents = repository.getExamEvents();
        priorityEvents = repository.getPriorityEvents();

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

    public LiveData<List<Events>> getEdWeeklyEvents() {
        return ed_weeklyEvents;
    }

    public LiveData<List<Events>> getPersWeeklyEvents() {
        return pers_weeklyEvents;
    }

    public LiveData<List<Events>> getProWeeklyEvents() {
        return pro_weeklyEvents;
    }

    public LiveData<List<Events>> getFitWeeklyEvents() {
        return fit_weeklyEvents;
    }

    public LiveData<List<Events>> getOthWeeklyEvents() {
        return oth_weeklyEvents;
    }

    public LiveData<List<Events>> getExamEvents() {
        return examEvents;
    }
    public LiveData<List<Events>> getPriorityEvents() {
        return priorityEvents;
    }
}
