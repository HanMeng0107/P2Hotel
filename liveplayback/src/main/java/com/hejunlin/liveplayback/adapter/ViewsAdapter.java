package com.hejunlin.liveplayback.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejunlin.liveplayback.R;


/**
 * Created by rbtmk on 2017/4/12.
 */

public class ViewsAdapter extends RecyclerView.Adapter<ViewsAdapter.ViewsViewHolder> {

    private int[] views = {R.mipmap.piclist1, R.mipmap.piclist2, R.mipmap.piclist3, R.mipmap.piclist4,
            R.mipmap.piclist1, R.mipmap.piclist2, R.mipmap.piclist3, R.mipmap.piclist4,};
    private String[] names = {"广州塔", "沙面", "石室圣心大教堂", "越秀公园", "广州塔", "沙面", "石室圣心大教堂", "越秀公园"};

    private Context context;
    private LayoutInflater inflater;

    private MyItemClickListener clickListener;

    public ViewsAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_views, parent, false);
        ViewsViewHolder holder = new ViewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewsViewHolder holder, int position) {
        holder.mTvName.setText(names[position]);
        holder.mIvView.setBackgroundResource(views[position]);
        holder.itemView.setFocusable(true);
        holder.itemView.setId(position);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    holder.mTvName.setTextColor(Color.parseColor("#333333"));
                    holder.mIvFocus.setVisibility(View.VISIBLE);
                    viewAnim(holder.mCvView, hasFocus);
                } else {
                    holder.mTvName.setTextColor(Color.parseColor("#666666"));
                    holder.mIvFocus.setVisibility(View.GONE);
                    viewAnim(holder.mCvView, hasFocus);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return views.length;
    }

    public class ViewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTvName = (TextView) itemView.findViewById(R.id.view_name);
            mIvView = (ImageView) itemView.findViewById(R.id.view_img);
            mCvView = (CardView) itemView.findViewById(R.id.cv_view);
            mIvFocus = (ImageView) itemView.findViewById(R.id.im_view_focus);
        }

        TextView mTvName;
        ImageView mIvFocus, mIvView;
        CardView mCvView;

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onItemClick(view, view.getId());
        }
    }

    /**
     * 位移动画
     *
     * @param v
     * @param hasFocus
     */
    private static void viewAnim(View v, boolean hasFocus) {
        TranslateAnimation animation;
        if (hasFocus) {
            animation = new TranslateAnimation(0, 0, 0, -60);
        } else {
            animation = new TranslateAnimation(0, 0, -60, 0);
        }
        animation.setDuration(500);//设置动画持续时间
        animation.setFillAfter(true);
        v.setAnimation(animation);
        animation.start();
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.clickListener = listener;
    }
}
