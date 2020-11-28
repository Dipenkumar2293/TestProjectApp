package com.dipen.testprojectapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void insert (Events events);
    @Update
    void update (Events events);
    @Delete
    void delete (Events events);
   /* void insert (Events events);*/
    @Query("SELECT * FROM event_table ORDER BY date ASC")
    LiveData<List<Events>>getAllEvents();

    @Query("SELECT * FROM event_table WHERE date LIKE :today ORDER BY startTime ASC")
    LiveData<List<Events>>getDailyEvents(String today);
}
