package com.codekiller.activemusic.Fragements;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codekiller.activemusic.Adapters.AllSongRecyclerAdapter;
import com.codekiller.activemusic.MainActivity;
import com.codekiller.activemusic.R;
import com.codekiller.activemusic.Utils.Database;
import com.codekiller.activemusic.Utils.SongsData;

import java.util.ArrayList;


public class AllSongsFragement extends Fragment {

    public static final String TAG = "All Song Fragement";

    RecyclerView recyclerView;
    ArrayList<SongsData> list;
    Database db;
    AllSongRecyclerAdapter allSongRecyclerAdapter;
    public static Context context;

    private static final String ARG_PARAM1 = "allsongs";
    private static final String ARG_PARAM2 = "param2";

    private int mParam1;
    private String mParam2;

    public AllSongsFragement(Context context) {
        // Required empty public constructor
        list = new ArrayList<>();
        db = new Database(context);
        this.context = context;
    }

    public static AllSongsFragement newInstance(String param1, String param2) {
        AllSongsFragement fragment = new AllSongsFragement(context);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1,-1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 100);
        } else {
            list = db.getAllSongs();
            Log.d(TAG, "onCreateView: " + list.get(0).getSongPath());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_songs_fragement, container, false);
        recyclerView = view.findViewById(R.id.recycler_all_song);
        allSongRecyclerAdapter = new AllSongRecyclerAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(allSongRecyclerAdapter);

        return view;
    }
}