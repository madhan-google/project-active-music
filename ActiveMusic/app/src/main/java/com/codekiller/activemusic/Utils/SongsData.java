package com.codekiller.activemusic.Utils;

import android.content.Context;

import java.io.Serializable;

public class SongsData implements Serializable {
    Context context;
    String artist;
    String album;
    String duration;
    String title;
    String songPath;

    public SongsData(String artist, String album, String duration, String title, String songPath, Context context) {
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.title = title;
        this.songPath = songPath;
        this.context = context;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
