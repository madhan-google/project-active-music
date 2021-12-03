package com.codekiller.activemusic.Adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

import com.codekiller.activemusic.R;
import com.codekiller.activemusic.SongsActivity;
import com.codekiller.activemusic.Utils.Database;

import java.io.Serializable;
import java.util.ArrayList;

import static com.codekiller.activemusic.Adapters.SelectionRecyclerAdapter.temp;

public class SelectionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    Database database;
    public static ImageView doneBtn;
    ImageView backBtn;
    SelectionRecyclerAdapter selectionRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        recyclerView = findViewById(R.id.selection_recycler);
        backBtn = findViewById(R.id.back_btn);
        doneBtn = findViewById(R.id.btn_done);
        database = new Database(this);
        selectionRecyclerAdapter = new SelectionRecyclerAdapter(this,database.getAllSongs());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(selectionRecyclerAdapter);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionActivity.this,SongsActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //temp.clear();
    }
}