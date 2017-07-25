package com.hejunlin.liveplayback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.hejunlin.liveplayback.adapter.ViewInfoAdapter;
import com.hejunlin.liveplayback.utils.Utils;

/**
 * Created by HM on 2017/4/14 15:27
 */

public class TripInfoActivity extends BaseActivity implements View.OnFocusChangeListener {

    private RecyclerView mRvView;
    private ViewInfoAdapter adapter;
    private ImageView mIvLeft, mImRight, mIvNow, mIvLast;

    private int[] views = {R.mipmap.view1, R.mipmap.view2, R.mipmap.view3, R.mipmap.view4, R.mipmap.view1, R.mipmap.view2, R.mipmap.view3, R.mipmap.view4};
    private int nowPos, lastPos, position;
    private boolean isShowLeft, isShowRight;

    private Animation alpha;
    private Bitmap bm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getIntent().getIntExtra("itemPosition", 3);
        Log.i("hm", position + "=");
        setContentView(R.layout.activity_view_info);
        getViews();
        initViews();
        registerReceiver();
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            if (((RecyclerView) view).getChildCount() > 0) {
                ((RecyclerView) view).getChildAt(position).requestFocus();
            }
        }
    }

    private void initViews() {
        adapter = new ViewInfoAdapter(this, views);
        mRvView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        llm.scrollToPosition(position);
        mRvView.setLayoutManager(llm);
        mRvView.setOnFocusChangeListener(this);
    }

    private void getViews() {
        mRvView = (RecyclerView) findViewById(R.id.rv_viewInfo);
        mIvLeft = (ImageView) findViewById(R.id.view_info_left);
        mImRight = (ImageView) findViewById(R.id.view_info_right);
        mIvNow = (ImageView) findViewById(R.id.view_info_bg_now);
        mIvLast = (ImageView) findViewById(R.id.view_info_bg_last);
    }

    @Override
    protected void onDestroy() {
        if (positionReceiver != null) {
            unregisterReceiver(positionReceiver);
        }
        if (!bm.isRecycled()) {
            bm.recycle();   //回收图片所占的内存
            System.gc();  //提醒系统及时回收
        }
        super.onDestroy();
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.kupa.view.position");
        registerReceiver(positionReceiver, filter);
    }

    private BroadcastReceiver positionReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            nowPos = intent.getIntExtra("nowPos", 0);
            lastPos = intent.getIntExtra("lastPos", 0);
            changeBg();
        }
    };

    private void changeBg() {
        showHideArrow();
        bm = Utils.readBitMap(this, views[nowPos]);
        mIvNow.setImageBitmap(bm);
        changeAnim(mIvNow, true);
        if (lastPos >= 0) {
            bm = Utils.readBitMap(this, views[lastPos]);
            mIvLast.setImageBitmap(bm);
            changeAnim(mIvLast, false);
        }

    }

    private void changeAnim(ImageView iv, boolean isNow) {
        if (isNow) {
            alpha = new AlphaAnimation(0.3f, 1.0f);
        } else {
            alpha = new AlphaAnimation(1f, 0f);
        }
        alpha.setDuration(10);
        alpha.setFillAfter(true);
        iv.setAnimation(alpha);
    }

    private void showHideArrow() {
        if (nowPos == 0) {
            mIvLeft.setVisibility(View.GONE);
            isShowLeft = true;
        } else if (nowPos == views.length - 1) {
            mImRight.setVisibility(View.GONE);
            isShowRight = true;
        } else {
            if (isShowLeft) {
                mIvLeft.setVisibility(View.VISIBLE);
                isShowLeft = false;
            }
            if (isShowRight) {
                mImRight.setVisibility(View.VISIBLE);
                isShowRight = false;
            }
        }
    }
}
