package com.codekiller.activemusic.Utils;

import java.util.ArrayList;

public class GlobalClass {
    public ArrayList<SongsData> songsDataArrayList;
    public ArrayList<PlaylistData> playlistDataArrayList;
    public SongsData songsData;
    public PlaylistData playlistData;

    public GlobalClass() {
        songsData = null;
        playlistDataArrayList = new ArrayList<>();
        playlistData = null;
        songsDataArrayList = new ArrayList<>();
    }
}
