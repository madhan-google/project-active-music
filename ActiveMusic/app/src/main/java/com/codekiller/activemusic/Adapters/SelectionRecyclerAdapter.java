package com.codekiller.activemusic.Adapters;

import android.annotation.SuppressLint;
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

import static com.codekiller.activemusic.Adapters.SelectionActivity.doneBtn;
import static com.codekiller.activemusic.Utils.Converts.toObj;
import static com.codekiller.activemusic.Utils.Converts.toStr;

public class SelectionRecyclerAdapter extends RecyclerView.Adapter<SelectionRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<SongsData> list;
    public static ArrayList<SongsData> temp;

    public SelectionRecyclerAdapter(Context context, ArrayList<SongsData> list) {
        this.context = context;
        this.list = list;
        temp = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.selection_recycler,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongsData songsData = list.get(position);
        holder.artistName.setText(songsData.getArtist());
        holder.songName.setText(songsData.getTitle());
        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onLongClick(View v) {
                if( temp.size() == 0 ){
                    temp.add(songsData);
                    holder.relativeLayout.setBackgroundColor(R.color.black);
                    holder.songName.setTextColor(R.color.white);
                    holder.artistName.setTextColor(R.color.white);
                    doneBtn.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if( temp.size() != 0 ){
                    temp.add(songsData);
                    holder.relativeLayout.setBackgroundColor(R.color.black);
                    holder.songName.setTextColor(R.color.white);
                    holder.artistName.setTextColor(R.color.white);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView songName, artistName;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.song_name);
            artistName = itemView.findViewById(R.id.artist_name);
            relativeLayout = itemView.findViewById(R.id.selection_relative_layout);
        }
    }
}
