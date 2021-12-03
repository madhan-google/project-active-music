package com.codekiller.activemusic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codekiller.activemusic.R;
import com.codekiller.activemusic.Utils.SongsData;

import java.util.ArrayList;

import static com.codekiller.activemusic.Utils.Converts.toObj;

public class PlaylistSongsRecyclerAdapter extends RecyclerView.Adapter<PlaylistSongsRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<SongsData> arrayList;

    public PlaylistSongsRecyclerAdapter(Context context, ArrayList<SongsData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_song_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongsData songsData = arrayList.get(position);
        holder.songName.setText(songsData.getTitle());
        holder.artistName.setText(songsData.getArtist());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView songName, artistName;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.song_name);
            artistName = itemView.findViewById(R.id.artist_name);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
