package com.hejunlin.liveplayback.utils;


import com.hejunlin.liveplayback.entity.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/16.
 */

public class JsonPaser {

    public static List<Weather> paserWeather(String result) {
        List<Weather> weathers = new ArrayList<>();
        JSONTokener paser = new JSONTokener(result);
        try {
            JSONObject obj = (JSONObject) paser.nextValue();
            if (obj.optInt("error") == 0) {
                JSONArray results = obj.optJSONArray("results");
                int len = results.length();
                for (int i = 0; i < len; i++) {
                    JSONObject weatherObj = results.optJSONObject(i);
                    JSONArray weather_data = weatherObj.optJSONArray("weather_data");
                    int length = weather_data.length();
                    for (int j = 0; j < length; j++) {
                        JSONObject info = weather_data.optJSONObject(j);
                        String date = info.optString("date");
                        String weather = info.optString("weather");
                        String temperature = info.optString("temperature");
                        String nightPictureUrl=info.optString("nightPictureUrl");
                        String dayPictureUrl=info.optString("dayPictureUrl");

                        if (date.length() > 2)
                            date = date.substring(0, 2);
                        Weather w = new Weather(weather,nightPictureUrl,dayPictureUrl,temperature,date);
                        weathers.add(w);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weathers;
    }
}
