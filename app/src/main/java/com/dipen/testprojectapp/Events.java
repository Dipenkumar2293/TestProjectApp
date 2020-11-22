package com.dipen.testprojectapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_table")
public class Events {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String startTime;
    private String  endTime;
    private String  date;
    private String category;

    public Events(String name, String  startTime, String endTime, String date, String category) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }
}
