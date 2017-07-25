package com.hejunlin.liveplayback.utils;

import com.hejunlin.liveplayback.entity.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HM on 2017/5/4 14:51
 */

public class GoodsUtils {
    public static List<Goods> createGoods(int[] goodsPricture, String[] goodsNames, String[] goodsPrice) {
        List<Goods> goodsInfos = new ArrayList<>();
        Goods goods;
        for (int i = 0; i < goodsNames.length; i++) {
            goods = new Goods();
            goods.setGoodsPicture(goodsPricture[i]);
            goods.setGoodsName(goodsNames[i]);
            goods.setGoodsPrice(goodsPrice[i]);
            goodsInfos.add(goods);
        }
        return goodsInfos;
    }
}
