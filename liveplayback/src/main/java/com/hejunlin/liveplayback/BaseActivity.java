package com.hejunlin.liveplayback;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.KeyEvent;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.hejunlin.liveplayback.contacts.Contacts;
import com.hejunlin.liveplayback.entity.Weather;
import com.hejunlin.liveplayback.utils.CombinationKeyUtil;
import com.hejunlin.liveplayback.utils.JsonPaser;
import com.hejunlin.liveplayback.utils.WeatherUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.hejunlin.liveplayback.contacts.Contacts.className;
import static com.hejunlin.liveplayback.contacts.Contacts.packageName;
import static com.hejunlin.liveplayback.utils.DrawUtils.getInternetPicture;


public class BaseActivity extends Activity {
    private CombinationKeyUtil keyUtil = null;
    private LocationClient mLocationClient = null;
    private BDLocationListener myListener = new MyLocationListener();
    private static SimpleDateFormat sdfTime;
    private static SimpleDateFormat sdfDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initKey();
        initSimpleDateFormat();
    }

    private void initSimpleDateFormat() {
        sdfTime = new SimpleDateFormat("HH:mm");
        sdfDate = new SimpleDateFormat("yyyy年MM月dd日 E");
    }

    private void initKey() {
        keyUtil = new CombinationKeyUtil(Contacts.keyStrings);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MatchCombinationKey(keyCode);
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 按键匹配，正确就跳转到系统设置界面
     *
     * @param keyCode 键值
     */
    private void MatchCombinationKey(int keyCode) {
        if (keyUtil.isMatch(keyCode)) {//如果按键为：左右左右，则组合快捷键匹配为true
            try {
                startActivity(new Intent().setClassName(packageName, className));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 百度地图定位
     */
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
                if (getCity() != null) {
                    requestWeather(getCity());
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
     * 配置百度地图定位参数
     */
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

    /**
     * 获取本地城市名
     *
     * @return city
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
     * 获取天气并保存到数据库
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
            }
        } catch (Exception e) {
        }
    }

    public Weather getWeather() {
        SimpleDateFormat format = new SimpleDateFormat("E");
        String week = format.format(new Date());
        String firstDay = WeatherUtil.getFirstDayWeather();
        if (!firstDay.equals("kupa") && !week.equals(week)) {
            WeatherUtil.deleteOneWeather(firstDay);
        }
        Weather weather = WeatherUtil.getOneWeather(week);
        if (null == weather) {
            mLocationClient = new LocationClient(BaseActivity.this);
            mLocationClient.registerLocationListener(myListener);
            initLocation();



            return null;
        } else {
            return weather;
        }
    }

    /**
     * 设置天气图片
     *
     * @param weather
     */
    public static Bitmap GetWeatherPicture(final Weather weather) {
        final Bitmap WeatherPicture;
        Time time = new Time();
        time.setToNow();
        int hour = time.hour;
        if (6 <= hour && hour <= 18) {
            WeatherPicture = getInternetPicture(weather.getDayPictureUrl());
        } else {
            WeatherPicture = getInternetPicture(weather.getNightPictureUrl());
        }
        return WeatherPicture;

    }

    /**
     * Function: 每分钟 更新时间和日期
     *
     * @author HM DateTime 2017年2月24日 下午3:48:12
     */
    public  static String[] getDateAndTime() {

        String systemTime = sdfTime.format(new Date());
        String systemDate = sdfDate.format(new Date());

        String[] arr = new String[2];
        arr[0] = systemTime;
        arr[1] = systemDate;

        return arr;

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    getWeather();
                    break;
            }
        }
    };
}