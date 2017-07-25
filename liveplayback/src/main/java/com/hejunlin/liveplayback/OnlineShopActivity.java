package com.hejunlin.liveplayback;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejunlin.liveplayback.adapter.ShopGoodsAdapter;
import com.hejunlin.liveplayback.adapter.ShopMenuAdapter;
import com.hejunlin.liveplayback.contacts.Contacts;
import com.hejunlin.liveplayback.entity.Goods;

import java.util.List;

import static com.hejunlin.liveplayback.utils.GoodsUtils.createGoods;


/**
 * Created by HM on 2017/3/28 15:27
 */

public class OnlineShopActivity extends BaseActivity {

    private ImageView mIvTitleImg;
    private TextView mTvTitleName;
    private RecyclerView mRvMenu, mRvGoods;
    private ShopGoodsAdapter goodsAdapter;
    private ShopMenuAdapter menuAdapter;
    private int typeCurrentPosition, goodsCurrentPosition;
    private List<Goods> mRecommendGoods, mTobaccoGoods, mRepastGoods, mSnacksGoods, mSpecialtyGoods, mPresentGoods, mEquipmentGoods;

    private boolean isShowQr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shpping);
        getViews();
        init();
        loadGoodsByType();
        initGoodsTypeRecyclerView();
    }

    private void loadGoodsByType() {
        //int[] goodsPricture, String[] goodsNames, String[] goodsPrice
        mRecommendGoods = createGoods(Contacts.GOODS_RECOMMEND_PICTURE, Contacts.GOODS_RECOMMEND_NAME, Contacts.GOODS_RECOMMEND_PRICE);
        mTobaccoGoods = createGoods(Contacts.GOODS_TOBACCO_PICTURE, Contacts.GOODS_TOBACCO_NAME, Contacts.GOODS_TOBACCO_PRICE);
        mRepastGoods = createGoods(Contacts.GOODS_REPAST_PICTURE, Contacts.GOODS_REPAST_NAME, Contacts.GOODS_REPAST_PRICE);
        mSnacksGoods = createGoods(Contacts.GOODS_SNACKS_PICTURE, Contacts.GOODS_SNACKS_NAME, Contacts.GOODS_SNACKS_PRICE);
        mSpecialtyGoods = createGoods(Contacts.GOODS_SPECIALTY_PICTURE, Contacts.GOODS_SPECIALTY_NAME, Contacts.GOODS_SPECIALTY_PRICE);
        mPresentGoods = createGoods(Contacts.GOODS_PRESENT_PICTURE, Contacts.GOODS_PRESENT_NAME, Contacts.GOODS_PRESENT_PRICE);
        mEquipmentGoods = createGoods(Contacts.GOODS_EQUIPMENT_PICTURE, Contacts.GOODS_EQUIPMENT_NAME, Contacts.GOODS_EQUIPMENT_PRICE);
    }

    @Override
    public void onBackPressed() {
        if (isShowQr) {
            findViewById(R.id.layout_qr).setVisibility(View.GONE);
            isShowQr = false;
        } else
            super.onBackPressed();
    }

    private void initGoodsTypeRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRvMenu.setLayoutManager(llm);
        menuAdapter = new ShopMenuAdapter(this);
        menuAdapter.setOnItemSelectListener(goodsTypeItemSelectListener);
        mRvMenu.setAdapter(menuAdapter);
    }

    ShopMenuAdapter.OnRecyclerViewItemSelectListener goodsTypeItemSelectListener = new ShopMenuAdapter.OnRecyclerViewItemSelectListener() {
        @Override
        public void onItemSelect(View view, int position) {
            typeCurrentPosition = position;
            Message message = new Message();
            message.what = 1;
            message.arg1 = position;
            myHandler.sendMessage(message);
        }
    };

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    mRvGoods.setAdapter(null);

                    typeCurrentPosition = message.arg1;
                    Log.i("hm", "position=" + typeCurrentPosition);
                    if (typeCurrentPosition == 0) {
                        //推荐
                        intiGoodsRecyclerView(mRecommendGoods);
                    } else if (typeCurrentPosition == 1) {
                        //烟酒
                        intiGoodsRecyclerView(mTobaccoGoods);
                    } else if (typeCurrentPosition == 2) {
                        //餐饮
                        intiGoodsRecyclerView(mRepastGoods);
                    } else if (typeCurrentPosition == 3) {
                        //零食
                        intiGoodsRecyclerView(mSnacksGoods);
                    } else if (typeCurrentPosition == 4) {
                        //特产
                        intiGoodsRecyclerView(mSpecialtyGoods);
                    } else if (typeCurrentPosition == 5) {
                        //礼品
                        intiGoodsRecyclerView(mPresentGoods);
                    } else if (typeCurrentPosition == 6) {
                        //设备
                        intiGoodsRecyclerView(mEquipmentGoods);
                    }
            }
        }
    };

    void intiGoodsRecyclerView(final List<Goods> goodInfos) {
        goodsAdapter = new ShopGoodsAdapter(this, goodInfos);

        mRvGoods.setAdapter(goodsAdapter);

        goodsAdapter.setOnItemClickListener(new ShopGoodsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                findViewById(R.id.layout_qr).setVisibility(View.VISIBLE);
                isShowQr = true;
            }
        });

        mRvGoods.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (((RecyclerView) v).getChildCount() > 0) {
                        ((RecyclerView) v).getChildAt(0).requestFocus();
                    }
                }
            }
        });
        goodsAdapter.setOnItemSelectListener(new ShopGoodsAdapter.OnRecyclerViewItemSelectListener() {
            @Override
            public void onItemSelect(View view, int position) {
                goodsCurrentPosition = position;
            }
        });

    }


    private void init() {
        mTvTitleName.setText("在线商城");
        mIvTitleImg.setBackgroundResource(R.mipmap.shopping);

        GridLayoutManager glm = new GridLayoutManager(this, 5);
        mRvGoods.setLayoutManager(glm);

    }

    private void getViews() {
        mTvTitleName = (TextView) findViewById(R.id.title_name);
        mIvTitleImg = (ImageView) findViewById(R.id.title_img);
        mRvGoods = (RecyclerView) findViewById(R.id.rv_shop_goods);
        mRvMenu = (RecyclerView) findViewById(R.id.rv_shop_menu);
        findViewById(R.id.tv_wifiSSID).setVisibility(View.GONE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (mRvMenu.hasFocus() && mRvMenu.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mRvMenu.getChildAt(typeCurrentPosition).setBackgroundResource(R.drawable.bg_movie_type);
                    mRvGoods.getChildAt(0).requestFocus();
                }
                if (mRvGoods.hasFocus()) {
                    TextView tv = (TextView) mRvMenu.getChildAt(typeCurrentPosition).findViewById(R.id.shop_menu_name);
                    tv.setTextColor(Color.WHITE);
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (goodsCurrentPosition % 5 == 0 && mRvGoods.hasFocus() && mRvGoods.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mRvMenu.getChildAt(typeCurrentPosition).requestFocus();
                }
                if (mRvMenu.hasFocus()) {
                    TextView tv = (TextView) mRvMenu.getChildAt(typeCurrentPosition).findViewById(R.id.shop_menu_name);
                    tv.setTextColor(Color.WHITE);
                }
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                if (mRvMenu.hasFocus() && mRvMenu.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mRvMenu.getChildAt(typeCurrentPosition).setBackgroundColor(0);
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (mRvMenu.hasFocus() && mRvMenu.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mRvMenu.getChildAt(typeCurrentPosition).setBackgroundColor(0);
                }

                if (mRvGoods.hasFocus() && isEndLine(mRvGoods, goodsCurrentPosition) && mRvGoods.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
//                    Toast.makeText(OnlineShopActivity.this, "is endLine", Toast.LENGTH_LONG).show();
//                    Log.i("hm", "old typeCurrentPosition=" + typeCurrentPosition);
//                    Message message = new Message();
//                    message.what = 1;
//                    message.arg1 = (typeCurrentPosition + 1);
//                    Log.i("hm", "new typeCurrentPosition=" + (typeCurrentPosition + 1));
//                    myHandler.sendMessage(message);
//                    mRvMenu.clearFocus();
//                    mRvMenu.getChildAt(typeCurrentPosition + 1).requestFocus();
//                    mRvGoods.getChildAt(0).requestFocus();
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static Boolean isEndLine(RecyclerView recyclerView, int currentItemPosition) {
        int countLine, moviesCount = recyclerView.getChildCount();
        if (moviesCount % 5 != 0) {
            countLine = (moviesCount / 5) + 1;
        } else {
            countLine = (moviesCount / 5);
        }
        if (currentItemPosition > (5 * (countLine - 1)) && recyclerView.getScrollState() == recyclerView.SCROLL_STATE_IDLE) {
            return true;
        } else {
            return false;
        }
    }
}
