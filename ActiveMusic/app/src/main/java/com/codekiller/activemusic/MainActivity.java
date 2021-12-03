package com.codekiller.activemusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.codekiller.activemusic.Adapters.ViewPageAdapter;
import com.codekiller.activemusic.Fragements.AllSongsFragement;
import com.codekiller.activemusic.Fragements.PlaylistFragement;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPageAdapter viewPageAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());

        viewPageAdapter.addFragments(new AllSongsFragement(this),"All Songs");
        viewPageAdapter.addFragments(new PlaylistFragement(this),"Playlists");

        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}