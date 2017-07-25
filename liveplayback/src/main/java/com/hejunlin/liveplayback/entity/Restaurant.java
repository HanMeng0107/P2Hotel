package com.hejunlin.liveplayback.entity;

/**
 * Created by rbtmk on 2017/4/16.
 */

public class Restaurant {

    private String name;
    private String score;
    private String phone;
    private String time;
    private String address;

    public Restaurant(String name, String score, String phone, String time, String address) {
        this.name = name;
        this.score = score;
        this.phone = phone;
        this.time = time;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
