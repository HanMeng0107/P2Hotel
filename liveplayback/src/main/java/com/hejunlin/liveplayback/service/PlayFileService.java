package com.hejunlin.liveplayback.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.hejunlin.liveplayback.playfile.FileServer;
import com.hejunlin.liveplayback.playfile.HTTPServerList;


/**
 * Created by HM on 2017/3/14 21:15
 */

public class PlayFileService extends Service {

    private FileServer fileServer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fileServer = new FileServer();
        fileServer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        HTTPServerList httpServerList = fileServer.getHttpServerList();
        if (httpServerList != null) {
            httpServerList.stop();
            httpServerList.close();
            httpServerList.clear();
            fileServer.interrupt();
        }

    }

}


