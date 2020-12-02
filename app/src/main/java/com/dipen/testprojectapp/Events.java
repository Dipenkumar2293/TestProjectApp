package com.dipen.testprojectapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Date;

@Entity(tableName = "event_table")
public class Events {
    @PrimaryKey(autoGenerate = true)
    private int id;
    public String name;
    public String startTime;
    public String  endTime;
    public String  date;
    public String category;
    public String isPriority;

    public Events(){
        this.name = "";
        this.startTime = "";
        this.endTime = "";
        this.date = "";
        this.category = "";
        this.isPriority = "";
    }

    public Events(String name, String startTime, String endTime, String date, String category, String isPriority) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.category = category;
        this.isPriority = isPriority;
    }

    public void setName(String name) {this.name = name;}

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

    public String getIsPriority() { return isPriority;}

    public void setIsPriority(String priority) { this.isPriority = priority; }
}
