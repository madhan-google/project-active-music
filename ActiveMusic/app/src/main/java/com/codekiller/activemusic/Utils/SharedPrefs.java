package com.codekiller.activemusic.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static com.codekiller.activemusic.Utils.Converts.toObj;
import static com.codekiller.activemusic.Utils.Converts.toStr;

public class SharedPrefs {
    Context context;
    ArrayList<String> arrayList;
    SharedPreferences sharedPreferences;
    PlaylistData playlistData;

    public SharedPrefs(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("com.codekiller.activemusic.Utils",Context.MODE_PRIVATE);
        arrayList = new ArrayList<>();
    }

    public boolean addPlaylist(ArrayList<String> list){
        sharedPreferences.edit().putStringSet("playlists",new HashSet<String>(list)).apply();
        return true;
    }

    public ArrayList<String> getPlaylists(){
        ArrayList<String> out = new ArrayList<>();
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("playlists",null);
        if( set != null ){
            return new ArrayList<>(set);
        }
        return null;
    }

}
