package com.example.nikhi.project;

public class Appointment {

    private int id;
    private String name, description, time, date, accept;
    public String uname;

    public Appointment(int id, String uname ,String name, String description, String time, String date, String accept) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.time = time;
        this.date = date;
        this.accept = accept;
        this.uname = uname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public String getAccept() {
        return accept;
    }
}
