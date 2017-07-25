package com.hejunlin.liveplayback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.hejunlin.liveplayback.adapter.FoodInfoAdapter;
import com.hejunlin.liveplayback.entity.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HM on 2017/4/15 15:27
 */

public class NearbyStoresActivity extends BaseActivity implements View.OnFocusChangeListener {

    private RecyclerView mRvAround;
    private FoodInfoAdapter adapter;

    private List<Restaurant> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_around_food);
        getViews();
        getDatas();
        initViews();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            if (((RecyclerView) view).getChildCount() > 0) {
                ((RecyclerView) view).getChildAt(0).requestFocus();
            }
        }
    }

    private void getDatas() {
        Restaurant restaurant = new Restaurant("合意来餐厅", "3.1", "(0755)25278588;(0755)25278788", "08:30 - 21:00", "北山道54号（菜市场对面）");
        list.add(restaurant);
        restaurant = new Restaurant("威记肠粉王", "4.0", "18520839233", "08:30 - 21:00", "罗湖区华丽路2046号木头龙翠华花园8栋1-101（近爱国路）");
        list.add(restaurant);
        restaurant = new Restaurant("普宁第一家肠粉", "4.4", "075582520121;4000908621转06056", "10:30 - 20:00", "华发北路振华手机城A1009D号铺");
        list.add(restaurant);
        restaurant = new Restaurant("合意来餐厅", "3.1", "(0755)25278588;(0755)25278788", "08:30 - 21:00", "北山道54号（菜市场对面）");
        list.add(restaurant);
    }

    private void initViews() {
        adapter = new FoodInfoAdapter(this, list);
        mRvAround.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvAround.setLayoutManager(llm);
        mRvAround.setOnFocusChangeListener(this);
    }

    private void getViews() {
        mRvAround = (RecyclerView) findViewById(R.id.rv_food_around);
    }
}
