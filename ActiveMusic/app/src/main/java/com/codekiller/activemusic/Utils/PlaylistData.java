package com.codekiller.activemusic.Utils;

import java.io.Serializable;
import java.util.ArrayList;

public class PlaylistData implements Serializable {
    String playlistName;
    ArrayList<SongsData> listSongs;

    public PlaylistData(String playlistName, ArrayList<SongsData> listSongs) {
        this.playlistName = playlistName;
        this.listSongs = listSongs;
    }

    public PlaylistData() {
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public ArrayList<SongsData> getListSongs() {
        return listSongs;
    }

    public void setListSongs(ArrayList<SongsData> listSongs) {
        this.listSongs = listSongs;
    }
}
