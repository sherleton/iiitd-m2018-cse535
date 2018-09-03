package com.example.nikhi.mulock;

public class SInfo {
    public String name;
    public String artist;
    public String source;

    SInfo(String name, String artist, String source){
        this.name = name;
        this.artist = artist;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getSource() {
        return source;
    }
}
