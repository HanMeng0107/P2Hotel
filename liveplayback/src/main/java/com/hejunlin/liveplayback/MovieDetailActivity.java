package com.hejunlin.liveplayback;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.hejunlin.liveplayback.adapter.RvRecommendAdapter;
import com.hejunlin.liveplayback.contacts.Contacts;
import com.hejunlin.liveplayback.entity.Movie;

import java.util.List;

import static com.hejunlin.liveplayback.utils.MoviesUtils.createMovies;

public class MovieDetailActivity extends BaseActivity implements View.OnFocusChangeListener, View.OnClickListener, RvRecommendAdapter.OnRecyclerViewItemClickListener {

    private TextView mBtnMoviePlay, mBtnMovieKeep, mMovieName, mMovieGrade, mMovieDateAndArea, mMovieType, mMovieDuration, mMovieDirector, mMovieProtagonist, mMovieAbstract;
    private ImageView mIvKeepFocus, mIvPlayFocus;
    private LinearLayout mLPlay;
    private RecyclerView mRvCommend;
    private RvRecommendAdapter mRvRecommendAdapter;
    private Movie mMovieInfo;
    private Boolean isCollect = false;
    private List<Movie> movieInfos;
    private String path;

    private VideoView mVv;

    private String[] movies = {"一条狗的使命", "一条狗的使命", "一条狗的使命", "一条狗的使命", "一条狗的使命", "一条狗的使命",
            "一条狗的使命", "一条狗的使命", "一条狗的使命", "一条狗的使命", "一条狗的使命", "一条狗的使命", "一条狗的使命",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviehome_videoitem_detail);
        getViews();
        initBtnPlay();
        init();
        initMovieInfo(mMovieInfo);
        initRecommend();
        initPreviewVideo(path);
    }

    private void initBtnPlay() {
        mIvPlayFocus.setVisibility(View.GONE);
        mBtnMoviePlay.setTextColor(Color.parseColor("#569bd5"));
        Drawable drawable = getResources().getDrawable(R.mipmap.play_no);
        //setBounds(int left, int top, int right, int bottom)
        drawable.setBounds(20, 0, drawable.getMinimumWidth() + 20, drawable.getMinimumHeight());
        mBtnMoviePlay.setCompoundDrawables(drawable, null, null, null);
    }

    private void getViews() {
        mRvCommend = (RecyclerView) findViewById(R.id.rv_recommend);
//        mBtnMovieKeep = (TextView) findViewById(R.id.tv_keep);
        mBtnMoviePlay = (TextView) findViewById(R.id.tv_play);
        mIvPlayFocus = (ImageView) findViewById(R.id.iv_play_focus);
//        mIvKeepFocus = (ImageView) findViewById(R.id.iv_keep_focus);
        mMovieName = (TextView) findViewById(R.id.movie_name);
        mMovieGrade = (TextView) findViewById(R.id.movie_grade);
        mMovieDateAndArea = (TextView) findViewById(R.id.movie_dateandarea);
        mMovieType = (TextView) findViewById(R.id.movie_type);
        mMovieDuration = (TextView) findViewById(R.id.movie_duration);
        mMovieDirector = (TextView) findViewById(R.id.movie_director);
        mMovieProtagonist = (TextView) findViewById(R.id.movie_protagonist);
        mMovieAbstract = (TextView) findViewById(R.id.movie_abstract);
    }

    private void init() {

        mMovieInfo = (Movie) this.getIntent().getSerializableExtra("fileitem");
        path = mMovieInfo.getMoviePreviewPath();
        if (path == null) {
            path = "android.resource://com.hejunlin.liveplayback/" + R.raw.zn;
        }
//        mBtnMovieKeep.setOnFocusChangeListener(this);
        mBtnMoviePlay.setOnFocusChangeListener(this);
//        mBtnMovieKeep.setOnClickListener(this);
        mBtnMoviePlay.setOnClickListener(this);
    }

    private void initMovieInfo(Movie mMovie) {
        if (mMovie.getMovieName() != null) {
            mMovieName.setText(mMovie.getMovieName());
        }
        if (mMovie.getMovieGrade() != null) {
            mMovieGrade.setText(mMovie.getMovieGrade());
        }
        if (mMovie.getMovieDateAndArea() != null) {
            mMovieDateAndArea.setText(mMovie.getMovieDateAndArea());
        }
        if (mMovie.getMovieType() != null) {
            mMovieType.setText(mMovie.getMovieType());
        }
        if (mMovie.getMovieDuration() != null) {
            mMovieDuration.setText(mMovie.getMovieDuration());
        }
        if (mMovie.getMovieDirector() != null) {
            mMovieDirector.setText(mMovie.getMovieDirector());
        }
        if (mMovie.getMovieProtagonist() != null) {
            mMovieProtagonist.setText(mMovie.getMovieProtagonist());
        }
        if (mMovie.getMovieAbstract() != null) {
            mMovieAbstract.setText(mMovie.getMovieAbstract());
        }
    }

    private void initPreviewVideo(final String path) {
        mVv = (VideoView) findViewById(R.id.video);
        mVv.setVideoURI(Uri.parse(path));
        mVv.start();
        mVv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });
        mVv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVv.setVideoURI(Uri.parse(path));
                mVv.start();
            }
        });
    }

    private void initRecommend() {
        movieInfos = createMovies(Contacts.ROMANTIC_MOVIES_IMG, Contacts.ROMANTIC_MOVIES_NAME);
        mRvRecommendAdapter = new RvRecommendAdapter(this, movieInfos);
        mRvCommend.setAdapter(mRvRecommendAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRvCommend.setLayoutManager(llm);
        mRvCommend.setOnFocusChangeListener(this);
        mRvRecommendAdapter.setOnItemClickListener(this);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id) {
//            case R.id.tv_keep:
//                if (hasFocus) {
//                    mIvKeepFocus.setVisibility(View.VISIBLE);
//                    mBtnMovieKeep.setTextColor(Color.WHITE);
//                    Drawable drawable = getResources().getDrawable(R.mipmap.keep_onfocus);
//                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                    mBtnMovieKeep.setCompoundDrawables(drawable, null, null, null);
//                } else {
//                    mIvKeepFocus.setVisibility(View.GONE);
//                    mBtnMovieKeep.setTextColor(Color.parseColor("#569bd5"));
//                    Drawable drawable = getResources().getDrawable(R.mipmap.keep_no);
//                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                    mBtnMovieKeep.setCompoundDrawables(drawable, null, null, null);
//                }
//                break;

            case R.id.tv_play:
                if (hasFocus) {
                    mIvPlayFocus.setVisibility(View.VISIBLE);
                    mBtnMoviePlay.setTextColor(Color.WHITE);
                    Drawable drawable = getResources().getDrawable(R.mipmap.play_start);
                    drawable.setBounds(20, 0, drawable.getMinimumWidth() + 20, drawable.getMinimumHeight());
                    mBtnMoviePlay.setCompoundDrawables(drawable, null, null, null);
                } else {
                    mIvPlayFocus.setVisibility(View.GONE);
                    mBtnMoviePlay.setTextColor(Color.parseColor("#569bd5"));
                    Drawable drawable = getResources().getDrawable(R.mipmap.play_no);
                    //setBounds(int left, int top, int right, int bottom)
                    drawable.setBounds(20, 0, drawable.getMinimumWidth() + 20, drawable.getMinimumHeight());
                    mBtnMoviePlay.setCompoundDrawables(drawable, null, null, null);
                }
                break;

            case R.id.rv_recommend:
                if (hasFocus) {
                    if (((RecyclerView) v).getChildCount() > 0) {
                        ((RecyclerView) v).getChildAt(0).requestFocus();
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_play:
                String moviePath = mMovieInfo.getMoviePlayPath();
                if (moviePath != null) {
                    Log.i("hm", "moviePath=" + moviePath);
//                    Intent intent = new Intent();
//                    intent.setDataAndType(Uri.parse(moviePath), "video/mp4");
//                    startActivity(intent);

                    Intent intent = new Intent(MovieDetailActivity.this, PlayMovieActivity.class);
                    intent.putExtra("moviePath", mMovieInfo.getMoviePlayPath());
                    startActivity(intent);

//                    String Testpath = "http://flv2.bn.netease.com/videolib3/1611/28/nNTov5571/SD/nNTov5571-mobile.mp4";
//                    TvLiveActivity.activityStart(MovieDetailActivity.this, moviePath);

                } else {
//                    String path = "android.resource://com.hejunlin.liveplayback/" + R.raw.sudu;
//                    TvLiveActivity.activityStart(MovieDetailActivity.this, path);
//                    Toast.toast(MovieDetailActivity.this, "该视频暂时无法播放");
                }

                break;
//            case R.id.tv_keep:
            //没有收藏时
//                if (!isCollect) {
//                    Toast.toast(getApplicationContext(), "您已成功收藏该电影");
//                    mBtnMovieKeep.setText("已收藏");
//                    mBtnMovieKeep.setBackground(getResources().getDrawable(R.drawable.bg_keep_on));
//                    isCollect = true;
//                } else {
//                    Toast.toast(getApplicationContext(), "您已取消收藏该电影");
//                    mBtnMovieKeep.setText("收藏");
//                    mBtnMovieKeep.setBackground(getResources().getDrawable(R.drawable.style_play));
//                    isCollect = false;
//                }
//                break;
        }
    }

    @Override
    public void onItemClick(View view, Movie data) {
        initMovieInfo(data);
//        initPreviewVideo(data.getMoviePreviewPath());
    }
}

