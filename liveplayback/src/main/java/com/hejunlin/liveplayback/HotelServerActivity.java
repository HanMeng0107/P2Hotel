package com.hejunlin.liveplayback;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hejunlin.liveplayback.adapter.HotelServerAdapter;
import com.hejunlin.liveplayback.utils.WifiUtils;


/**
 * Created by HM on 2017/3/28 15:27
 */

public class HotelServerActivity extends BaseActivity implements View.OnFocusChangeListener {

    private RecyclerView mRvServer;
    private HotelServerAdapter adapter;
    private TextView mWifiSSID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        getViews();
        getWifiSSID();
        initRecyclerView();
    }

    /*
    * 获取当前连接wifi的名称
    * */
    private void getWifiSSID() {
        if (WifiUtils.isWifiConnected(HotelServerActivity.this)) {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ssid = wifiInfo.getSSID();
            Log.i("hm", "ssid=" + ssid);
            String newSsid = ssid.substring(ssid.indexOf("\"") + 2, ssid.lastIndexOf("\"") - 1);
            mWifiSSID.setText("当前Wifi：" + ssid);
        } else {
            mWifiSSID.setText(null);
        }
    }

    private void initRecyclerView() {
        adapter = new HotelServerAdapter(this);
        mRvServer.setAdapter(adapter);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        mRvServer.setLayoutManager(glm);
        mRvServer.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (((RecyclerView) v).getChildCount() > 0) {
                ((RecyclerView) v).getChildAt(0).requestFocus();
            }
        }
    }

    private void getViews() {
        mRvServer = (RecyclerView) findViewById(R.id.rv_hotel_server);
        mWifiSSID = (TextView) findViewById(R.id.tv_wifiSSID);
    }
}
