package com.dipen.testprojectapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.time.LocalDate;
import java.util.List;

public class EventRepository {
    private EventDao eventDao;
    private LiveData<List<Events>> allEvents;
    private LiveData<List<Events>> dailyEvents;
    private LiveData<List<Events>> weeklyEvents;

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
        weeklyEvents = eventDao.getWeeklyEvents(today, plus1Day, plus2Day, plus3Day, plus4Day, plus5Day, plus6Day);
    }
    public void insert(Events events){
        new InsertEventAsyncTask(eventDao).execute(events);
    }
    public void update(Events events){
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

    public LiveData<List<Events>> getWeeklyEvents() {
        return weeklyEvents;
    }

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
