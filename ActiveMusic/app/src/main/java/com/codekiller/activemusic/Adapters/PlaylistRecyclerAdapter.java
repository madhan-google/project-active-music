package com.codekiller.activemusic.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codekiller.activemusic.R;
import com.codekiller.activemusic.SongsActivity;
import com.codekiller.activemusic.Utils.GlobalClass;
import com.codekiller.activemusic.Utils.PlaylistData;

import java.util.ArrayList;

import static com.codekiller.activemusic.Utils.Converts.toObj;
import static com.codekiller.activemusic.Utils.Converts.toStr;

public class PlaylistRecyclerAdapter extends RecyclerView.Adapter<PlaylistRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<String> list;
    GlobalClass globalClass;

    public PlaylistRecyclerAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
        globalClass = new GlobalClass();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlaylistData playlistData = (PlaylistData) toObj(list.get(position),PlaylistData.class);
        holder.playlistName.setText(playlistData.getPlaylistName());
        holder.songsCount.setText(playlistData.getListSongs().size()+" Songs");
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.playlistData = playlistData;
                context.startActivity(new Intent(context, SongsActivity.class)
                                        .putExtra("playlist_no",position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView playlistName, songsCount;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playlistName = itemView.findViewById(R.id.playlist_name);
            songsCount = itemView.findViewById(R.id.song_count);
            relativeLayout = itemView.findViewById(R.id.playilst_relative_layout);
        }
    }
}
