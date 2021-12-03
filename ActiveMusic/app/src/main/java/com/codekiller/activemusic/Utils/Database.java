package com.codekiller.activemusic.Utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public ArrayList<SongsData> list;
    public Context context;
    SongsData songsData;

    public Database(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public ArrayList<SongsData> getAllSongs() {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA
        };
        Cursor c = context.getContentResolver().query(uri,projection,null,null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        c.moveToFirst();
        while( c.moveToNext() ){
            songsData = new SongsData(c.getString(c.getColumnIndex(projection[1])),
                    c.getString(c.getColumnIndex(projection[0])),
                    c.getString(c.getColumnIndex(projection[3])),
                    c.getString(c.getColumnIndex(projection[2])),
                    c.getString(c.getColumnIndex(projection[4])),
                    context);
            list.add(songsData);
        }

        return list;
    }
}
