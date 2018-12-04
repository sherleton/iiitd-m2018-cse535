package com.example.nikhi.project;

public class History {

    private int id;
    private String description, time, date;

    public History(int id, String description, String time, String date) {
        this.id = id;
        this.description = description;
        this.time = time;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
