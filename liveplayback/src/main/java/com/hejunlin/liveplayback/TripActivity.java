package com.hejunlin.liveplayback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hejunlin.liveplayback.adapter.ViewsAdapter;


public class TripActivity extends BaseActivity implements View.OnFocusChangeListener, ViewsAdapter.MyItemClickListener {

    private RecyclerView mRvViews;
    private ViewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        getViews();
        initViews();
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            if (((RecyclerView) view).getChildCount() > 0) {
                ((RecyclerView) view).getChildAt(0).requestFocus();
            }
        }
    }

    private void initViews() {
        adapter = new ViewsAdapter(this);
        adapter.setOnItemClickListener(this);
        mRvViews.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRvViews.setLayoutManager(llm);
        mRvViews.setOnFocusChangeListener(this);
    }

    private void getViews() {
        mRvViews = (RecyclerView) findViewById(R.id.rv_view);
    }


    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, TripInfoActivity.class);
        intent.putExtra("itemPosition", position);
        startActivity(intent);
        overridePendingTransition(R.anim.foodactivity_zoomin, R.anim.foodactivity_zoomout);
    }
}
