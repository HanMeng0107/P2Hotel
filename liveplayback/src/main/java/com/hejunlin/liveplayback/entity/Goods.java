package com.hejunlin.liveplayback.entity;

import java.io.Serializable;

public class Goods implements Serializable {
    private String goodsName;
    private String goodsPrice;
    private int goodsPicture;

    public Goods(String goodsName, String goodsPrice, int goodsPicture) {
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsPicture = goodsPicture;
    }

    public Goods() {
        super();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsPicture() {
        return goodsPicture;
    }

    public void setGoodsPicture(int goodsPicture) {
        this.goodsPicture = goodsPicture;
    }
}
