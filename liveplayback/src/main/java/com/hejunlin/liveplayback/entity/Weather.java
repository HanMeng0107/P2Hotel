package com.hejunlin.liveplayback.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * @author HM DateTime 2017��3��2�� ����4:04:56
 * @version 1.0
 */
@Table(name = "Weather")
public class Weather {
    @Column(name = "weather")
    private String weather;
    @Column(name = "nightPictureUrl")
    private String nightPictureUrl;
    @Column(name = "dayPictureUrl")
    private String dayPictureUrl;
    @Column(name = "temperature")
    private String temperature;
    @Column(name = "date", isId = true)
    private String date;

    public Weather() {
    }

    public Weather(String weather, String nightPictureUrl, String dayPictureUrl, String temperature,
                   String date) {
        this.weather = weather;
        this.date = date;
        this.nightPictureUrl = nightPictureUrl;
        this.dayPictureUrl = dayPictureUrl;
        this.temperature = temperature;

    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getNightPictureUrl() {
        return nightPictureUrl;
    }

    public void setNightPictureUrl(String nightPictureUrl) {
        this.nightPictureUrl = nightPictureUrl;
    }

    public String getDayPictureUrl() {
        return dayPictureUrl;
    }

    public void setDayPictureUrl(String dayPictureUrl) {
        this.dayPictureUrl = dayPictureUrl;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
