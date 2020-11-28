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

    public EventRepository(Application application){

        String today = LocalDate.now().toString();

        EventDatabase eventDatabase = EventDatabase.getInstance(application);
        eventDao = eventDatabase.eventDao();
        allEvents = eventDao.getAllEvents();
        dailyEvents = eventDao.getDailyEvents(today);
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
