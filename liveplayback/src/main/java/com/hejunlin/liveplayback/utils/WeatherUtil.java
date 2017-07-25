package com.hejunlin.liveplayback.utils;


import com.hejunlin.liveplayback.entity.Weather;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;
import java.util.List;

/**
 * Created by admin on 2017/3/16.
 */

public class WeatherUtil {

    private static DbManager db;

    private static DbManager getManager() {
        DbManager.DaoConfig daoConfig = DatabaseUtil.getDaoConfig();
        return x.getDb(daoConfig);
    }

    /**
     * 保存天气到数据库
     *
     * @param weathers
     */
    public static void saveWeather(List<Weather> weathers) {
        try {
            db = getManager();
            db.saveOrUpdate(weathers);
        } catch (DbException e) {
        }
    }

    /**
     * 获取某一天的天气
     *
     * @param day
     * @return
     */
    public static Weather getOneWeather(String day) {
        Weather weather = null;
        try {
            db = getManager();
            weather = db.findById(Weather.class, day);
        } catch (DbException e) {
        }
        return weather;
    }

    /**
     * 获取第一条天气
     *
     * @return
     */
    public static String getFirstDayWeather() {
        String week = "kupa";
        try {
            db = getManager();
            Weather weather = db.findFirst(Weather.class);
            if (null != weather)
                week = weather.getDate();
        } catch (DbException e) {
        }
        return week;
    }

    /**
     * 删除某天天气
     *
     * @param day
     */
    public static void deleteOneWeather(String day) {
        try {
            db = getManager();
            db.deleteById(Weather.class, day);
        } catch (DbException e) {
        }
    }


}
