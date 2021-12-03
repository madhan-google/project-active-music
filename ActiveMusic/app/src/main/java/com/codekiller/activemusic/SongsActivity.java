package com.codekiller.activemusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.codekiller.activemusic.Adapters.PlaylistSongsRecyclerAdapter;
import com.codekiller.activemusic.Adapters.SelectionActivity;
import com.codekiller.activemusic.Fragements.AllSongsFragement;
import com.codekiller.activemusic.Utils.GlobalClass;
import com.codekiller.activemusic.Utils.PlaylistData;
import com.codekiller.activemusic.Utils.SharedPrefs;
import com.codekiller.activemusic.Utils.SongsData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.codekiller.activemusic.Adapters.SelectionRecyclerAdapter.temp;
import static com.codekiller.activemusic.Utils.Converts.toObj;

public class SongsActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    PlaylistSongsRecyclerAdapter playlistSongsRecyclerAdapter;
    TextView playlistName;
    SharedPrefs sharedPrefs;
    ArrayList<SongsData> arrayList;
    GlobalClass globalClass;
    int playno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        playlistName = findViewById(R.id.playlist_name);
        recyclerView = findViewById(R.id.songs_recycler_view);
        globalClass = new GlobalClass();
        sharedPrefs = new SharedPrefs(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        //String obj = getIntent().getStringExtra("playlist");
        playno = getIntent().getIntExtra("playlist_no",-1);
        PlaylistData playlistData = globalClass.playlistData;
        globalClass.playlistData = null;
        playlistName.setText(playlistData.getPlaylistName());
        arrayList = playlistData.getListSongs();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.song_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == R.id.add_songs ){
            startActivity(new Intent(this, SelectionActivity.class));
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        //temp.clear();
    }
}