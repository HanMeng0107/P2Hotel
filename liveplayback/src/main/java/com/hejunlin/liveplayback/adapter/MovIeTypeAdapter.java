package com.hejunlin.liveplayback.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejunlin.liveplayback.R;


/**
 * Created by HM on 2017/3/16 15:01
 */

public class MovIeTypeAdapter extends RecyclerView.Adapter<MovIeTypeAdapter.ViewHolder> {

    private LayoutInflater inflate;
    private Activity activity;


    private String[] types;
    private static View oldView;
    private static TextView oldTv;

    private static OnRecyclerViewItemSelectListener mSelectListener = null;

    public MovIeTypeAdapter(Activity activity, String[] movieTypes) {
        this.activity = activity;
        this.types = movieTypes;
        this.inflate = LayoutInflater.from(activity);
    }

    public interface OnRecyclerViewItemSelectListener {
        void onItemSelect(View view, int position);
    }

    public void setOnItemSelectListener(OnRecyclerViewItemSelectListener listener) {
        mSelectListener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        TextView mTvType;
        ImageView mIvBg, mIvFocus;
    }

    @Override
    public MovIeTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflate.inflate(R.layout.item_movie_type, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mTvType = (TextView) view.findViewById(R.id.type_name);
        viewHolder.mIvBg = (ImageView) view.findViewById(R.id.type_focus_bg);
        viewHolder.mIvFocus = (ImageView) view.findViewById(R.id.type_focus_img);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MovIeTypeAdapter.ViewHolder holder, final int position) {
        holder.mTvType.setText(types[position]);
        holder.itemView.setFocusable(true);
        holder.itemView.setId(position);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (null != oldView)
                        oldView.setVisibility(View.GONE);
                    if (null != oldTv)
                        oldTv.setTextColor(activity.getResources().getColor(R.color.movie_item_text));

                    holder.mIvBg.setVisibility(View.VISIBLE);
                    holder.mTvType.setTextColor(Color.WHITE);
                    holder.mIvFocus.setVisibility(View.VISIBLE);

                    mSelectListener.onItemSelect(holder.itemView, holder.itemView.getId());
                } else {
                    holder.mIvFocus.setVisibility(View.GONE);
                    oldView = holder.mIvBg;
                    oldTv = holder.mTvType;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return types.length;
    }
}