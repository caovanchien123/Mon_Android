package com.example.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private ArrayList<Song> songList;
    private ListView songView;
    private ImageButton btnPlay, btnPause;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private ImageView img_ro;
    private Animation animation;
    private SeekBar seekBar;
    private Handler updateHandler = new Handler();
    private TextView thoiGian, tenBaiHat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    private void setEvent() {
         animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        songList = getListSongs(this);

        SongAdapter songAdt = new SongAdapter(this, songList);
        songView.setAdapter(songAdt);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(songList.get(i).getPath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    tenBaiHat.setText(songList.get(i).getTitle());
                    seekBar.setMax((int) mediaPlayer.getDuration());
                    seekBar.setProgress(0);
                    seekBar.setOnSeekBarChangeListener(MainActivity.this);
                    updateHandler.postDelayed(update, 100);
                    img_ro.startAnimation(animation);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                img_ro.startAnimation(animation);
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                img_ro.setAnimation(null);
            }
        });
    }

    private void setControl() {
        songView = findViewById(R.id.song_list);
        btnPause = findViewById(R.id.pause);
        btnPlay = findViewById(R.id.play);
        img_ro = findViewById(R.id.image_ro);
        seekBar = findViewById(R.id.sbThoiGian);
        thoiGian = findViewById(R.id.thoiGian);
        tenBaiHat = findViewById(R.id.tenBaiHat);
    }

    public static ArrayList<Song> getListSongs(Context context) {
        ArrayList<Song> mListSongs = new ArrayList();
        Uri uri;
        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] m_data = {MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA};

        Cursor c = context.getContentResolver().query(uri, m_data, MediaStore.Audio.Media.IS_MUSIC + "=1", null,
                MediaStore.Audio.Media.TITLE + " ASC");

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            String name, title, album, artist, path;
            long id;
            int duration;
            id = c.getLong(c.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
            name = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
            title = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            album = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
            artist = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
            duration = (int) (c.getInt(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
            path = c.getString(c.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
            Song song = new Song(id, title, artist, path);
            mListSongs.add(song);

        }
        return mListSongs;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(b)
            mediaPlayer.seekTo(i);
    }

    private Runnable update = new Runnable()
    {
        public void run()
        {
            long currentTime = mediaPlayer.getCurrentPosition();
            seekBar.setProgress((int)currentTime);
            int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(currentTime);
            int seconds = (int)TimeUnit.MILLISECONDS.toSeconds(currentTime) - minutes * 60;
            thoiGian.setText(String.format("%02d:%02d",minutes, seconds));
            updateHandler.postDelayed(this, 100);
        }
    };

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}