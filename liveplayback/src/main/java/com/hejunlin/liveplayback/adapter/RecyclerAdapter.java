package com.hejunlin.liveplayback.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejunlin.liveplayback.R;

import java.util.List;


/**
 * Created by admin on 2017/3/10.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Integer> mDatas;
    private Activity activity;
    public boolean isFocus;

    private int[] shadows = {R.id.movie_shadow_one, R.id.movie_shadow_two, R.id.movie_shadow_three, R.id.movie_shadow_four, R.id.movie_shadow_five};
    private int LAST_INDEX;
    private boolean isShowLeft, isShowRight;

    public RecyclerAdapter(Activity activity, List<Integer> datats) {
        this.activity = activity;
        mInflater = LayoutInflater.from(activity);
        mDatas = datats;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
        TextView mTxt;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.activity_index_gallery_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mImg = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
        viewHolder.mTxt = (TextView) view.findViewById(R.id.id_index_gallery_item_text);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.mImg.setImageResource(mDatas.get(i));
        viewHolder.mTxt.setText("电影名称" + i);
        viewHolder.itemView.setFocusable(true);
        viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    scaleItem(v, false, 1.1f, 1);
                    viewHolder.mTxt.setTextColor(Color.WHITE);
                    isFocus = true;
                    showHiseShadow(i, true);
                    scaleItem(activity.findViewById(shadows[getPosition(i)]), false, 1, 0.5f);
                    //发送广播
                    sendBroadcast(i);
                    showHideArrow(i);
                } else {
                    //缩小item
                    scaleItem(v, true, 1.1f, 1);
                    //缩小阴影
                    scaleItem(activity.findViewById(shadows[getPosition(i)]), true, 1, 0.5f);
                    //还原字体颜色
                    viewHolder.mTxt.setTextColor(Color.BLACK);
                    isFocus = false;
                    LAST_INDEX = i;
                }
            }
        });
    }

    /**
     * 隐藏显示箭头逻辑
     *
     * @param pos
     */
    private void showHideArrow(int pos) {
        if (pos == 0) {
            showHideArrow(R.id.arrow_left, false);
            isShowLeft = false;
        } else if (pos == mDatas.size() - 1) {
            showHideArrow(R.id.arrow_right, false);
            isShowRight = false;
        } else {
            if (!isShowLeft) {
                showHideArrow(R.id.arrow_left, true);
                isShowLeft = true;
            }
            if (!isShowRight) {
                showHideArrow(R.id.arrow_right, true);
                isShowRight = true;
            }
        }
    }

    /**
     * 隐藏显示箭头操作
     *
     * @param id
     * @param isShow
     */
    private void showHideArrow(int id, boolean isShow) {
        if (isShow)
            (activity.findViewById(id)).setVisibility(View.VISIBLE);
        else
            (activity.findViewById(id)).setVisibility(View.GONE);
    }

    private void sendBroadcast(int position) {
        Intent intent = new Intent();
        intent.setAction("com.kupa.tv.movie");
        intent.putExtra("position", position);
        intent.putExtra("index", LAST_INDEX);
        activity.sendBroadcast(intent);
    }

    private void showHiseShadow(int position, boolean isShow) {
        position = getPosition(position);
        if (isShow)
            (activity.findViewById(shadows[position])).setVisibility(View.VISIBLE);
        else
            (activity.findViewById(shadows[position])).setVisibility(View.INVISIBLE);
    }

    private int getPosition(int position) {
        if (position == mDatas.size() - 2) {
            position = 3;
        } else if (position == mDatas.size() - 1) {
            position = 4;
        } else if (position > 1 && position < mDatas.size() - 2) {
            position = 2;
        }
        return position;
    }

    private void scaleItem(View v, boolean isSmall, float scale, float from) {
          /*
                AnimationSet相当于一个动画的集合，true表示使用Animation的interpolator
                false则是使用自己的。
                Interpolator 被用来修饰动画效果，定义动画的变化率，可以使存在的动画效果
                accelerated(加速)，decelerated(减速),repeated(重复),bounced(弹跳)等。
             */

        AnimationSet animationSet = new AnimationSet(true);
            /*
                参数解释：
                    第一个参数：X轴水平缩放起始位置的大小（fromX）。1代表正常大小
                    第二个参数：X轴水平缩放完了之后（toX）的大小，0代表完全消失了
                    第三个参数：Y轴垂直缩放起始时的大小（fromY）
                    第四个参数：Y轴垂直缩放结束后的大小（toY）
                    第五个参数：pivotXType为动画在X轴相对于物件位置类型
                    第六个参数：pivotXValue为动画相对于物件的X坐标的开始位置
                    第七个参数：pivotXType为动画在Y轴相对于物件位置类型
                    第八个参数：pivotYValue为动画相对于物件的Y坐标的开始位置

                   （第五个参数，第六个参数），（第七个参数,第八个参数）是用来指定缩放的中心点
                    0.5f代表从中心缩放
             */
        ScaleAnimation scaleAnimation;
        if (isSmall)
            scaleAnimation = new ScaleAnimation(scale, from, scale, from,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.6f);
        else
            scaleAnimation = new ScaleAnimation(from, scale, from, scale,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.6f);
        //3秒完成动画
        scaleAnimation.setDuration(500);
        //动画执行完后是否停留在执行完的状态
//        scaleAnimation.setFillAfter(true);
        animationSet.setFillAfter(true);
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
        animationSet.addAnimation(scaleAnimation);
        //启动动画
        v.startAnimation(animationSet);
    }
}
