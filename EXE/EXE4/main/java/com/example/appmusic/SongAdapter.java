package com.example.appmusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends BaseAdapter {

    private ArrayList<Song> songs;
    private Context context;

    public SongAdapter(Context c, ArrayList<Song> theSongs){
        songs=theSongs;
        context = c;
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.song, null);
        TextView songView = (TextView)view.findViewById(R.id.song_title);
        TextView artistView = (TextView)view.findViewById(R.id.song_artist);
        Song currSong = songs.get(i);
        songView.setText(currSong.getTitle());
        artistView.setText(currSong.getArtist());
        return view;
    }
}
