package com.codekiller.activemusic.Fragements;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.codekiller.activemusic.Adapters.PlaylistRecyclerAdapter;
import com.codekiller.activemusic.R;
import com.codekiller.activemusic.Utils.PlaylistData;
import com.codekiller.activemusic.Utils.SharedPrefs;

import java.util.ArrayList;

import static com.codekiller.activemusic.Utils.Converts.toStr;
import static com.codekiller.activemusic.Utils.Converts.toast;

public class PlaylistFragement extends Fragment {

    public static final String TAG = "PLAYLIST FRAGEMENT ";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    Button createBtn;
    public static Context context;
    EditText dialogEditText;
    Button dialogCreateBtn, dialogCancelBtn;
    Dialog dialog;
    SharedPrefs sharedPrefs;
    ArrayList<String> lists;
    PlaylistData playlistData;
    PlaylistRecyclerAdapter playlistRecyclerAdapter;

    public PlaylistFragement(Context context) {
        // Required empty public constructor
        this.context = context;
    }


    public static PlaylistFragement newInstance(String param1, String param2) {
        PlaylistFragement fragment = new PlaylistFragement(context);
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        lists = new ArrayList<>();
        playlistData = new PlaylistData();
        sharedPrefs = new SharedPrefs(context);
        lists = sharedPrefs.getPlaylists();
        //Log.d(TAG, "onCreate: "+lists.get(0));
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.playlist_create_dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        dialogEditText = dialog.findViewById(R.id.edit_text);
        dialogCreateBtn = dialog.findViewById(R.id.create_btn);
        dialogCancelBtn = dialog.findViewById(R.id.cancel_btn);
        dialogCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialogCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playlistData.setPlaylistName(dialogEditText.getText().toString());
                playlistData.setListSongs(new ArrayList<>());

                if(lists == null){
                    lists = new ArrayList<>();
                    lists.add(toStr(playlistData));
                }else{
                    lists.add(toStr(playlistData));
                    playlistRecyclerAdapter.notifyDataSetChanged();
                }
                Log.d(TAG, "onClick: list "+lists.get(0));
                if( sharedPrefs.addPlaylist(lists) )
                    toast("playlist created",context);
                dialog.dismiss();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist_fragement, container, false);
        createBtn = view.findViewById(R.id.create_playlist);
        recyclerView = view.findViewById(R.id.recycler_playlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        if( lists != null ){
            playlistRecyclerAdapter = new PlaylistRecyclerAdapter(context,lists);
        }
        recyclerView.setAdapter(playlistRecyclerAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                lists.remove(viewHolder.getAdapterPosition());
                playlistRecyclerAdapter.notifyDataSetChanged();
                sharedPrefs.addPlaylist(lists);
                toast("playlist deleted",context);
            }
        }).attachToRecyclerView(recyclerView);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        return view;
    }
}