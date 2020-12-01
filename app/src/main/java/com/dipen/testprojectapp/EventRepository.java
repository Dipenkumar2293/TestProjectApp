package com.dipen.testprojectapp;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.time.LocalDate;
import java.util.List;

public class EventRepository {
    private EventDao eventDao;
    private LiveData<List<Events>> allEvents;
    private LiveData<List<Events>> dailyEvents;
    private LiveData<List<Events>> ed_weeklyEvents;
    private LiveData<List<Events>> pers_weeklyEvents;
    private LiveData<List<Events>> pro_weeklyEvents;
    private LiveData<List<Events>> fit_weeklyEvents;
    private LiveData<List<Events>> oth_weeklyEvents;
    private LiveData<List<Events>> priorityEvents;

    public int eventID = 1;

    public EventRepository(Application application){

        LocalDate current = LocalDate.now();
        String today = current.toString();
        String plus1Day = current.plusDays(1).toString();
        String plus2Day = current.plusDays(2).toString();
        String plus3Day = current.plusDays(3).toString();
        String plus4Day = current.plusDays(4).toString();
        String plus5Day = current.plusDays(5).toString();
        String plus6Day = current.plusDays(6).toString();

        EventDatabase eventDatabase = EventDatabase.getInstance(application);
        eventDao = eventDatabase.eventDao();
        allEvents = eventDao.getAllEvents();
        dailyEvents = eventDao.getDailyEvents(today);
        ed_weeklyEvents = eventDao.getEdWeeklyEvents(today, plus1Day, plus2Day, plus3Day, plus4Day, plus5Day, plus6Day);
        pers_weeklyEvents = eventDao.getPersWeeklyEvents(today, plus1Day, plus2Day, plus3Day, plus4Day, plus5Day, plus6Day);
        pro_weeklyEvents = eventDao.getProWeeklyEvents(today, plus1Day, plus2Day, plus3Day, plus4Day, plus5Day, plus6Day);
        fit_weeklyEvents = eventDao.getFitWeeklyEvents(today, plus1Day, plus2Day, plus3Day, plus4Day, plus5Day, plus6Day);
        oth_weeklyEvents = eventDao.getOthWeeklyEvents(today, plus1Day, plus2Day, plus3Day, plus4Day, plus5Day, plus6Day);
        priorityEvents = eventDao.getPriorityEvents();
    }
    public void insert(Events events){
        new InsertEventAsyncTask(eventDao).execute(events);
    }
    public void update(Events events){
        events.setPriority("yes");
        Log.d("priority", String.valueOf(events.getPriority()));
        new UpdateEventAsyncTask(eventDao).execute(events);
    }
    public void delete(Events events){
        new DeleteEventAsyncTask(eventDao).execute(events);
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

    public LiveData<List<Events>> getPriorityEvents() {return priorityEvents; }
    /*public LiveData<List<Events>> getPriorityEvents(){
        priorityEvents = eventDao.getPriorityEvent(eventID);
        return priorityEvents;
    }*/

    private static class InsertEventAsyncTask extends AsyncTask<Events,Void,Void> {
        private  EventDao eventDao;

        private InsertEventAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }
        @Override
        protected Void doInBackground(Events... events) {
            eventDao.insert(events[0]);
            return null;
        }
    }

    private static class UpdateEventAsyncTask extends AsyncTask<Events,Void,Void> {
        private  EventDao eventDao;

        private UpdateEventAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }
        @Override
        protected Void doInBackground(Events... events) {
            eventDao.update(events[0]);
            return null;
        }
    }

    private static class DeleteEventAsyncTask extends AsyncTask<Events,Void,Void> {
        private  EventDao eventDao;

        private DeleteEventAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }
        @Override
        protected Void doInBackground(Events... events) {
            eventDao.delete(events[0]);
            return null;
        }
    }
}
