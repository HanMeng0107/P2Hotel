package com.hejunlin.liveplayback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.hejunlin.liveplayback.adapter.RecyclerAdapter;
import com.hejunlin.liveplayback.adapter.RecyclerServerAdapter;
import com.hejunlin.liveplayback.entity.Weather;
import com.hejunlin.liveplayback.utils.JsonPaser;
import com.hejunlin.liveplayback.utils.WeatherUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by HM on 2017/3/10 16:37
 */

public class SecondHomeActivity extends BaseActivity implements View.OnFocusChangeListener, RecyclerServerAdapter.ViewHolder.OnItemClickListener {

    private RecyclerView mRecyclerView, mRvServer;
    private TextView mTvWeather, mTvTime;
    private RecyclerAdapter mAdapter;
    private RecyclerServerAdapter serverAdapter;
    private List<Integer> mDatas;
    private List<String> servers;

    private static int NEXT_RIGHT = 4;
    private static int NEXT_LEFT = 0;

    private Class[] clazz = {TvLiveMainPageActivity.class, TvLiveMainPageActivity.class, MovieHomeActivity.class, TvLiveMainPageActivity.class, TvLiveMainPageActivity.class,};

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    private SimpleDateFormat format;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        getViews();
        initDatas();
        initRecycle(mRecyclerView);
        initRecycle(mRvServer);
        setAdapter();
        registerReceiver();
        initWeather();
    }

    private void getViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.grid_recycler);
        mRvServer = (RecyclerView) findViewById(R.id.server_recycler);
        mTvTime = (TextView) findViewById(R.id.main_time);
        mTvWeather = (TextView) findViewById(R.id.main_weather);

        format = new SimpleDateFormat("HH:mm");
    }

    private void initDatas() {
        mDatas = new ArrayList<>(Arrays.asList(R.mipmap.move_zore,
                R.mipmap.movie, R.mipmap.movie_two, R.mipmap.movie_three, R.mipmap.main_music,
                R.mipmap.music_zore, R.mipmap.music_two, R.mipmap.music_three, R.mipmap.game_zore));

        servers = new ArrayList<>(Arrays.asList("电视直播", "在线商城", "全部影片", "周围娱乐", "酒店服务"));
    }

    private void initRecycle(RecyclerView mRecyclerView) {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setOnFocusChangeListener(this);
    }

    private void setAdapter() {
        //设置适配器
        mAdapter = new RecyclerAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        serverAdapter = new RecyclerServerAdapter(this, servers);
        serverAdapter.setOnItemClickListener(this);
        mRvServer.setAdapter(serverAdapter);
    }

    private void registerReceiver() {
        ReceivePosition receivePosition = new ReceivePosition();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.kupa.tv.movie");
        registerReceiver(receivePosition, filter);
    }

    ReceivePosition receivePosition;

    private class ReceivePosition extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int index = intent.getIntExtra("index", 0);
            int pos = intent.getIntExtra("position", 0);
            int toPos;

            if (pos == 1)
                mRecyclerView.smoothScrollToPosition(0);
            if (pos > 1 && pos < mDatas.size() - 1) {
                NEXT_RIGHT = pos + 2;
                NEXT_LEFT = pos - 2;

                if (pos < index)
                    toPos = NEXT_LEFT;
                else
                    toPos = NEXT_RIGHT;
                mRecyclerView.smoothScrollToPosition(toPos);
            }
        }
    }

    /**
     * 初始化天气信息
     */
    private void initWeather() {
        SimpleDateFormat format = new SimpleDateFormat("E");
        String week = format.format(new Date());
        String firstDay = WeatherUtil.getFirstDayWeather();
        if (!firstDay.equals("kupa") && !week.equals(week)) {
            WeatherUtil.deleteOneWeather(firstDay);
        }
        Weather weather = WeatherUtil.getOneWeather(week);
        if (null == weather) {
            mLocationClient = new LocationClient(SecondHomeActivity.this);
            //声明LocationClient类
            mLocationClient.registerLocationListener(myListener);
            //注册监听函数
            initLocation();
        } else {
            mTvWeather.setText(weather.getWeather() + " " + weather.getTemperature() + " " + getCity() + "  |  ");
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(false);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            boolean locaSuccess = false;
            if (location.getLocType() == BDLocation.TypeGpsLocation) {
                // GPS定位结果
                locaSuccess = true;
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                // 网络定位结果
                locaSuccess = true;
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
                // 离线定位结果
                locaSuccess = true;
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                if(getCity()!=null){
                    requestWeather(getCity());
                }else{
                    Toast.makeText(SecondHomeActivity.this, "网络不通导致定位失败，请检查网络是否通畅", Toast.LENGTH_SHORT).show();
                }
            }
            if (locaSuccess) {
                String city = location.getCity();
                if (null != city) {
                    mLocationClient.stop();
                    if (city.contains("市"))
                        city = city.substring(0, city.indexOf("市"));
                    saveCity(city);
                    requestWeather(city);
                }
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    /**
     * 获取本地城市名
     *
     * @return
     */
    private String getCity() {
        SharedPreferences sp = getSharedPreferences("kupatv", 0);
        return sp.getString("city", "深圳");
    }

    /**
     * 保存城市名称
     *
     * @param city
     */
    private void saveCity(String city) {
        SharedPreferences sp = getSharedPreferences("kupatv", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("city", city).apply();
    }

    /**
     * 获取天气
     *
     * @param city
     */
    private void requestWeather(String city) {
        try {
            HttpGet httpRequest = new HttpGet("http://api.map.baidu.com/telematics/v3/weather?location=" + city
                    + "&output=json&ak=XshP6eeg4Rc6CRGqmDuGo23c&mcode=E5:5A:F6:32:80:C2:2F:87:07:04:19:9F:51:E8:D1:E5:6B:12:95:8D;com.yimi.merun");
            HttpResponse httpResponse = new DefaultHttpClient()
                    .execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(httpResponse.getEntity());
                List<Weather> weathers = JsonPaser.paserWeather(result);
                WeatherUtil.saveWeather(weathers);
                handler.sendEmptyMessage(1);
            } else {
                Log.e("hm", "天气更新失败=================");
            }
        } catch (Exception e) {
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    setTime();
                    break;

                case 1:
                    initWeather();
                    break;
            }
        }
    };

    /**
     * 设置时间
     */
    private void setTime() {
        String time = format.format(new Date());
        handler.sendEmptyMessageDelayed(0, 2000);
        mTvTime.setText(time);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (((RecyclerView) v).getChildCount() > 0) {
                ((RecyclerView) v).getChildAt(0).requestFocus();
            }
        }
    }

    @Override
    public void onItemClick(View view) {
        int childAdapterPosition = mRvServer.getChildAdapterPosition(view);
        Intent intent = new Intent(this, clazz[childAdapterPosition]);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTime();
    }

    @Override
    protected void onPause() {
        handler.removeMessages(0);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (null != receivePosition)
            unregisterReceiver(receivePosition);
        super.onDestroy();
    }


}
