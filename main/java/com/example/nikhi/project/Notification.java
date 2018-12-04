package com.example.nikhi.project;

public class Notification {

    public String rec, display, header;

    public Notification(String display, String header, String rec) {
        this.rec = rec;
        this.display = display;
        this.header = header;
    }

    public String getRec() {
        return rec;
    }

    public String getHeader() {
        return header;
    }

    public String getDisplay() {
        return display;
    }
}
