package com.example.appmusic;

public class Song {
    private long id;
    private String title;
    private String artist;
    private String path;

    public Song(long songID, String songTitle, String songArtist, String songPath) {
        id=songID;
        title=songTitle;
        path = songPath;
        artist=songArtist;
    }

    public long getID(){return id;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}

    public String getPath() {
        return path;
    }
}
