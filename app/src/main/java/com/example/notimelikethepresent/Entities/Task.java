package com.example.notimelikethepresent.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String details;
    private int priority;
    private boolean idDone;

    public Task(String title, String details, int priority, boolean idDone) {
        this.title = title;
        this.details = details;
        this.priority = priority;
        this.idDone = idDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isIdDone() {
        return idDone;
    }

    public void setIdDone(boolean idDone) {
        this.idDone = idDone;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
