package com.hejunlin.liveplayback;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejunlin.liveplayback.entity.Weather;
import com.hejunlin.liveplayback.layout.MainUpViewLayout;
import com.hejunlin.liveplayback.layout.MyFrameMainLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.BLACK;
import static com.hejunlin.liveplayback.utils.Toast.toast;


public class MainHomeActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener, ViewTreeObserver.OnGlobalFocusChangeListener {
    private MainUpViewLayout mainUpView1;
    private MyFrameMainLayout main_lay12;
    private AlphaAnimation alphaAnimation;
    private ImageView imgTvlive, imgAllmovie, imgOnlinemall, imgEntertainment, imgMyhotel, imgTrip, mWeatherPicture;
    private List<ImageView> ImageViewslist = new ArrayList<ImageView>();
    private SimpleDateFormat sdfTime, sdfDate;
    private TextView txtTvlive, txtAllmovie, txtOnlinemall, txtEntertainment, txtMyhotel, txtTrip, mTvTime, mTvDate, mTvWeather, mTemperature;
    private List<TextView> textviewlist = new ArrayList<TextView>();
    private Weather mWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kupa_mainhome_activity);
        getViews();
        init();
        updateDateTime();
//        initWeather();

    }

    Handler myHandler = new Handler() {
        @SuppressLint("HandlerLeak")
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    updateDateTime();
                    break;
                case 2:
                    initWeather();
                    break;
                case 3:
                    ImageView mImageView = (ImageView) message.obj;
                    SetBorder(mImageView);
                    break;
            }
        }

        ;
    };

    /**
     * Function: 获取视图
     *
     * @author HM DateTime 2017年2月24日 下午3:48:12
     */
    private void getViews() {

        mainUpView1 = (MainUpViewLayout) findViewById(R.id.mainUpView2);
        main_lay12 = (MyFrameMainLayout) findViewById(R.id.main_lay2);

        this.mTvDate = (TextView) findViewById(R.id.date);
        this.mTvTime = (TextView) findViewById(R.id.time);
        this.mTvWeather = (TextView) findViewById(R.id.weather);
        this.mTemperature = (TextView) findViewById(R.id.temperature);
        this.mWeatherPicture = (ImageView) findViewById(R.id.weatherPicture);

        imgTvlive = (ImageView) findViewById(R.id.img_tvlive);
        imgAllmovie = (ImageView) findViewById(R.id.img_allmovie);
        imgOnlinemall = (ImageView) findViewById(R.id.img_onlinemall);
        imgEntertainment = (ImageView) findViewById(R.id.img_entertainment);
        imgMyhotel = (ImageView) findViewById(R.id.img_myhotel);
        imgTrip = (ImageView) findViewById(R.id.img_trip);

        txtTvlive = (TextView) findViewById(R.id.txt_tvlive);
        txtAllmovie = (TextView) findViewById(R.id.txt_allmovie);
        txtOnlinemall = (TextView) findViewById(R.id.txt_onlinemall);
        txtEntertainment = (TextView) findViewById(R.id.txt_entertainment);
        txtMyhotel = (TextView) findViewById(R.id.txt_myhotel);
        txtTrip = (TextView) findViewById(R.id.txt_trip);

        imgTvlive.setOnClickListener(this);
        imgAllmovie.setOnClickListener(this);
        imgOnlinemall.setOnClickListener(this);
        imgEntertainment.setOnClickListener(this);
        imgMyhotel.setOnClickListener(this);
        imgTrip.setOnClickListener(this);

        imgTvlive.setOnFocusChangeListener(this);
        imgAllmovie.setOnFocusChangeListener(this);
        imgOnlinemall.setOnFocusChangeListener(this);
        imgEntertainment.setOnFocusChangeListener(this);
        imgMyhotel.setOnFocusChangeListener(this);
        imgTrip.setOnFocusChangeListener(this);

    }

    /**
     * Function:初始化
     *
     * @author HM DateTime 2017年3月8日 下午8:52:41
     */
    @SuppressLint("ResourceAsColor")
    private void init() {

        alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(MainHomeActivity.this,
                R.anim.alpha_in);

        ImageViewslist.add(imgTvlive);
        ImageViewslist.add(imgAllmovie);
        ImageViewslist.add(imgOnlinemall);
        ImageViewslist.add(imgEntertainment);
        ImageViewslist.add(imgMyhotel);
        ImageViewslist.add(imgTrip);

        textviewlist.add(txtTvlive);
        textviewlist.add(txtAllmovie);
        textviewlist.add(txtOnlinemall);
        textviewlist.add(txtEntertainment);
        textviewlist.add(txtMyhotel);
        textviewlist.add(txtTrip);

        mainUpView1.setUpRectResource(R.mipmap.mainpage_white_round);
        mainUpView1.setDrawUpRectPadding(new RectF(0, 0, 0, 0));

        main_lay12.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
    }

    /**
     * Function: 每分钟 更新时间和日期
     *
     * @author HM DateTime 2017年2月24日 下午3:48:12
     */
    private void updateDateTime() {
        String[] DateAndTime = getDateAndTime();
        mTvTime.setText(DateAndTime[0]);
        mTvDate.setText(DateAndTime[1]);
        myHandler.sendEmptyMessageDelayed(1, 1000 * 60);
    }

    /**
     * 初始化天气信息
     */
    private void initWeather() {
        mWeather = getWeather();


        /*
        *有问题！！！！！！！！！！！！！！！！
        *
        */

        Log.i("hm", "weather:" + mWeather.getWeather());

//        mTvWeather.setText(mWeather.getWeather());
//        String weathertemp = mWeather.getTemperature();
//        String spStr[] = weathertemp.split(" ~");
//        mTemperature.setText(spStr[1] + "~" + spStr[0] + "℃");
//        mWeatherPicture.setImageBitmap(GetWeatherPicture(mWeather));
    }


    /*
     * （非 Javadoc） <p>Title: onClick</p> <p>Description: </p>
     *
     * @param arg0
     *
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        int id = view.getId();
        switch (id) {
            case R.id.img_tvlive:
                startActivity(new Intent(this, TvLiveMainPageActivity.class));
                overridePendingTransition(R.anim.activity_zoomin, R.anim.activity_zoomout);
                break;
            case R.id.img_allmovie:
                startActivity(new Intent(this, MovieHomeActivity.class));
                overridePendingTransition(R.anim.activity_zoomin, R.anim.activity_zoomout);
                break;
            case R.id.img_entertainment:
                startActivity(new Intent(this, EntertainmentActivity.class));
                overridePendingTransition(R.anim.activity_zoomin, R.anim.activity_zoomout);
                break;
            case R.id.img_trip:
                startActivity(new Intent(this, TripActivity.class));
                overridePendingTransition(R.anim.activity_zoomin, R.anim.activity_zoomout);
                break;
            case R.id.img_onlinemall:
                startActivity(new Intent(this, OnlineShopActivity.class));
                overridePendingTransition(R.anim.activity_zoomin, R.anim.activity_zoomout);
                break;
            case R.id.img_myhotel:
                startActivity(new Intent(this, HotelServerActivity.class));
                overridePendingTransition(R.anim.activity_zoomin, R.anim.activity_zoomout);
                break;
            default:
                toast(MainHomeActivity.this, "请稍后再试。。。");
                break;
        }
    }

    /*
     * （非 Javadoc） <p>Title: onFocusChange</p> <p>Description: </p>
     *
     * @param view
     *
     * @param isfoucus
     *
     * @see
     * android.view.View.OnFocusChangeListener#onFocusChange(android.view.View,
     * boolean)
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public void onFocusChange(View view, boolean isfocus) {
        // TODO Auto-generated method stub
        Message message = new Message();
        switch (view.getId()) {
            case R.id.img_tvlive:
                message.obj = imgTvlive;
                break;
            case R.id.img_allmovie:
                message.obj = imgAllmovie;
                break;
            case R.id.img_onlinemall:
                message.obj = imgOnlinemall;
                break;
            case R.id.img_entertainment:
                message.obj = imgEntertainment;
                break;
            case R.id.img_myhotel:
                message.obj = imgMyhotel;
                break;
            case R.id.img_trip:
                message.obj = imgTrip;
                break;
            default:
                break;
        }
        message.what = 3;
        myHandler.sendMessage(message);
    }

    /**
     * Function:设置item底部背景
     *
     * @author HM DateTime 2017年3月9日 上午11:01:13
     */
    @SuppressLint("ResourceAsColor")
    private void SetBorder(ImageView view) {

        switch (view.getId()) {
            case R.id.img_tvlive:
                imgAllmovie.setImageResource(R.mipmap.normal_icon_allmovie);
                imgTvlive.setImageResource(R.mipmap.select_blue_tvlive);
                break;
            case R.id.img_allmovie:
                imgTvlive.setImageResource(R.mipmap.normal_icon_tvlive);
                imgOnlinemall.setImageResource(R.mipmap.normal_icon_onlinemall);
                imgAllmovie.setImageResource(R.mipmap.select_blue_allmovie);
                break;
            case R.id.img_onlinemall:
                imgEntertainment.setImageResource(R.mipmap.normal_icon_entertainment);
                imgAllmovie.setImageResource(R.mipmap.normal_icon_allmovie);
                imgOnlinemall.setImageResource(R.mipmap.select_blue_onlinemall);
                break;
            case R.id.img_entertainment:
                imgTrip.setImageResource(R.mipmap.normal_icon_trip);
                imgOnlinemall.setImageResource(R.mipmap.normal_icon_onlinemall);
                imgEntertainment.setImageResource(R.mipmap.select_blue_entertainment);
                break;
            case R.id.img_trip:
                imgMyhotel.setImageResource(R.mipmap.normal_icon_myhotel);
                imgEntertainment.setImageResource(R.mipmap.normal_icon_entertainment);
                imgTrip.setImageResource(R.mipmap.select_blue_trip);
                break;
            case R.id.img_myhotel:
                imgTrip.setImageResource(R.mipmap.normal_icon_trip);
                imgMyhotel.setImageResource(R.mipmap.select_blue_myhotel);
                break;
            default:
                break;
        }

        for (int i = 0; i < ImageViewslist.size(); i++) {
            if (ImageViewslist.get(i) == view) {
                textviewlist.get(i).setTextSize(getResources().getDimensionPixelSize(R.dimen.h_38));
                textviewlist.get(i).setTextColor(getResources().getColor(R.color.mainpage_select_item_text));
                view.startAnimation(alphaAnimation);
            } else {
                textviewlist.get(i).setTextSize(getResources().getDimensionPixelSize(R.dimen.h_34));
                textviewlist.get(i).setTextColor(BLACK);
            }
        }
    }

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        if (newFocus != null) {
            newFocus.bringToFront();
        }
        mainUpView1.setFocusView(newFocus, oldFocus, 1.13f);
    }

}
