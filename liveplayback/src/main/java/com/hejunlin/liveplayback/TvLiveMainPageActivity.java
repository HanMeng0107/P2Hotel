/*
 * Copyright (C) 2016 hejunlin <hejunlin2013@gmail.com>
 * 
 * Github:https://github.com/hejunlin2013/LivePlayback
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hejunlin.liveplayback;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hejunlin.liveplayback.adapter.MyOptionItemAdapter;
import com.hejunlin.liveplayback.widget.MetroViewBorderImpl;
/**
 * Created by HM on 2016/10/28 15:27
 */
public class TvLiveMainPageActivity extends BaseActivity {

    private MetroViewBorderImpl mMetroViewBorderImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tvlive_activitymain_recyclerview);
        mMetroViewBorderImpl = new MetroViewBorderImpl(this);
        mMetroViewBorderImpl.setBackgroundResource(R.drawable.tvlivemainpageactivity_metroviewborderimp1_backgroundresource_border_color);
        loadRecyclerViewMenuItem();
    }

    private void loadRecyclerViewMenuItem() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ry_menu_item);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusable(false);
        mMetroViewBorderImpl.attachTo(recyclerView);
        createOptionItemData(recyclerView, R.layout.tvlive_mainpageactivity_detail_menu_item);
    }

    private void createOptionItemData(RecyclerView recyclerView, int id) {
        MyOptionItemAdapter adapter = new MyOptionItemAdapter(this, id);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }
}
