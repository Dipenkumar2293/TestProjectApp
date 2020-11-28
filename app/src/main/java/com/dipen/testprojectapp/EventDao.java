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

    @Query("SELECT * FROM event_table WHERE date LIKE :today ORDER BY date ASC")
    LiveData<List<Events>>getDailyEvents(String today);

    @Query("SELECT * FROM event_table WHERE date LIKE :today or date LIKE :plusOne or date LIKE :plusTwo or  date LIKE " +
            ":plusThree or date LIKE :plusFour or date LIKE :plusFive or date LIKE :plusSix ORDER BY date ASC")
    LiveData<List<Events>>getWeeklyEvents(String today, String plusOne, String plusTwo, String plusThree, String
            plusFour, String plusFive, String plusSix);
}
