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

    @Query("SELECT * FROM event_table WHERE category='Education' and (date LIKE :today or date LIKE :plusOne or date LIKE :plusTwo or  date LIKE :plusThree or date LIKE :plusFour or date LIKE :plusFive or date LIKE :plusSix) ORDER BY date ASC")
    LiveData<List<Events>>getEdWeeklyEvents(String today, String plusOne, String plusTwo, String plusThree, String
            plusFour, String plusFive, String plusSix);

    @Query("SELECT * FROM event_table WHERE category='Personal' and (date LIKE :today or date LIKE :plusOne or date LIKE :plusTwo or  date LIKE :plusThree or date LIKE :plusFour or date LIKE :plusFive or date LIKE :plusSix) ORDER BY date ASC")
    LiveData<List<Events>>getPersWeeklyEvents(String today, String plusOne, String plusTwo, String plusThree, String
            plusFour, String plusFive, String plusSix);

    @Query("SELECT * FROM event_table WHERE category='Profession' and (date LIKE :today or date LIKE :plusOne or date LIKE :plusTwo or  date LIKE :plusThree or date LIKE :plusFour or date LIKE :plusFive or date LIKE :plusSix) ORDER BY date ASC")
    LiveData<List<Events>>getProWeeklyEvents(String today, String plusOne, String plusTwo, String plusThree, String
            plusFour, String plusFive, String plusSix);

    @Query("SELECT * FROM event_table WHERE category='Fitness' and (date LIKE :today or date LIKE :plusOne or date LIKE :plusTwo or  date LIKE :plusThree or date LIKE :plusFour or date LIKE :plusFive or date LIKE :plusSix) ORDER BY date ASC")
    LiveData<List<Events>>getFitWeeklyEvents(String today, String plusOne, String plusTwo, String plusThree, String
            plusFour, String plusFive, String plusSix);

    @Query("SELECT * FROM event_table WHERE category='Others' and (date LIKE :today or date LIKE :plusOne or date LIKE :plusTwo or  date LIKE :plusThree or date LIKE :plusFour or date LIKE :plusFive or date LIKE :plusSix) ORDER BY date ASC")
    LiveData<List<Events>>getOthWeeklyEvents(String today, String plusOne, String plusTwo, String plusThree, String
            plusFour, String plusFive, String plusSix);

    @Query("SELECT * FROM event_table WHERE name LIKE '%exam%' or name LIKE '%final%' or name LIKE '%midterm%' ORDER BY date ASC")
    LiveData<List<Events>>getExamEvents();

    @Query("SELECT * FROM event_table WHERE isPriority = 'yes' ORDER BY date ASC")
    LiveData<List<Events>>getPriorityEvents();
}
