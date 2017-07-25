package com.hejunlin.liveplayback;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class PlayMovieActivity extends BaseActivity {
    private String path;
    private VideoView Vmovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_movie);
        path = getIntent().getStringExtra("moviePath");
        Vmovie = (VideoView) findViewById(R.id.vv_movieplay);
        Vmovie.setVideoURI(Uri.parse(path));
        Vmovie.start();
        Vmovie.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(false);
            }
        });
    }
}
