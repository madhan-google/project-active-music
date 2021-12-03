package com.codekiller.activemusic.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codekiller.activemusic.R;
import com.codekiller.activemusic.Utils.MyService;
import com.codekiller.activemusic.Utils.SongsData;

import java.io.File;
import java.util.ArrayList;

public class AllSongRecyclerAdapter extends RecyclerView.Adapter<AllSongRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<SongsData> list;
    MyService myService;
    MediaPlayer mediaPlayer;
    int id;

    public AllSongRecyclerAdapter(Context context, ArrayList<SongsData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.all_song_recycler, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.artistname.setText(list.get(position).getArtist());
        holder.songname.setText(list.get(position).getTitle());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                mediaPlayer = MediaPlayer.create(context, Uri.fromFile(new File(list.get(position).getSongPath())));
                //mediaPlayer.setLooping(false);
                mediaPlayer.start();
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView songname, artistname;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songname = itemView.findViewById(R.id.song_name);
            artistname = itemView.findViewById(R.id.artist_name);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
