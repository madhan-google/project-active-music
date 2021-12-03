package com.codekiller.activemusic.Utils;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

public class Converts {
    public static Gson g = new Gson();
    public static String toStr(Object obj){
        return g.toJson(obj);
    }
    public static Object toObj(String str,Class temp){
        return g.fromJson(str,temp);
    }
    public static void toast(String str, Context context){
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
