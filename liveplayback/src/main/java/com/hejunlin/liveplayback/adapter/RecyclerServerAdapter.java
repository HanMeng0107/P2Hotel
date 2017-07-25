package com.hejunlin.liveplayback.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejunlin.liveplayback.R;

import java.util.List;


/**
 * Created by admin on 2017/3/10.
 */

public class RecyclerServerAdapter extends RecyclerView.Adapter<RecyclerServerAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<String> mDatas;
    private Context context;

    private String[] bgColors = {"#f4bf2f", "#fe7e80", "#008ef1", "#e667ea", "#64c9ca"};
    private int[] icons = {R.mipmap.server_live, R.mipmap.server_shopping, R.mipmap.server_movie, R.mipmap.server_happy, R.mipmap.server_hotel,};

    private static ViewHolder.OnItemClickListener mOnItemClickListener;

    public RecyclerServerAdapter(Context context, List<String> datats) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    public void setOnItemClickListener(ViewHolder.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(View arg0) {
            super(arg0);
            itemView.setOnClickListener(this);
        }

        TextView mTxt;
        CardView layout;
        ImageView mIm, mImFocus;

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view);
            }
        }

        public interface OnItemClickListener {
            void onItemClick(View view);
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_server_layout,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.layout = (CardView) view.findViewById(R.id.server_layout);
        viewHolder.mTxt = (TextView) view.findViewById(R.id.server_name);
        viewHolder.mIm = (ImageView) view.findViewById(R.id.server_icon);
        viewHolder.mImFocus = (ImageView) view.findViewById(R.id.movie_focus_bg);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.layout.setCardBackgroundColor(Color.parseColor(bgColors[i]));
        viewHolder.mTxt.setText(mDatas.get(i));
        viewHolder.mIm.setBackgroundResource(icons[i]);

        viewHolder.itemView.setFocusable(true);
        viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    viewHolder.mImFocus.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.mImFocus.setVisibility(View.GONE);
                }
            }
        });
    }

}
