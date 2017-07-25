package com.hejunlin.liveplayback.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejunlin.liveplayback.R;


/**
 * Created by rbtmk on 2017/3/28.
 */

public class HotelServerAdapter extends RecyclerView.Adapter<HotelServerAdapter.HotelViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private int[] normalIcon = {R.mipmap.normal_eat, R.mipmap.normal_play, R.mipmap.normal_washclothes, R.mipmap.normal_rentcar, R.mipmap.normal_contrl, R.mipmap.normal_hotel};
    private int[] focusIcon = {R.mipmap.focus_eat, R.mipmap.focus_play, R.mipmap.focus_washclothes, R.mipmap.focus_rentcar, R.mipmap.focus_control, R.mipmap.focus_hotel};
    private String[] serverName = {"餐饮预订", "休闲娱乐", "洗衣", "租车", "智能控制", "续订住房"};

    public HotelServerAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public HotelServerAdapter.HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_hotel_server, parent, false);
        HotelViewHolder holder = new HotelViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final HotelServerAdapter.HotelViewHolder holder, final int position) {
        holder.mTvName.setText(serverName[position]);
        holder.mIvImg.setBackgroundResource(normalIcon[position]);
        holder.itemView.setFocusable(true);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    holder.mTvName.setTextColor(Color.WHITE);
                    holder.mIvFocus.setVisibility(View.VISIBLE);
                    holder.mIvImg.setBackgroundResource(focusIcon[position]);
                    holder.mIvBg.setBackground(context.getResources().getDrawable(R.drawable.bg_hotel_focus));
                } else {
                    holder.mTvName.setTextColor(Color.parseColor("#999999"));
                    holder.mIvFocus.setVisibility(View.GONE);
                    holder.mIvImg.setBackgroundResource(normalIcon[position]);
                    holder.mIvBg.setBackground(context.getResources().getDrawable(R.drawable.bg_hotel_normal));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return serverName.length;
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        public HotelViewHolder(View itemView) {
            super(itemView);
            mIvBg = (ImageView) itemView.findViewById(R.id.hotel_server_bg);
            mIvImg = (ImageView) itemView.findViewById(R.id.hotel_server_img);
            mIvFocus = (ImageView) itemView.findViewById(R.id.hotel_server_focus);
            mTvName = (TextView) itemView.findViewById(R.id.hotel_server_name);
        }

        ImageView mIvBg, mIvImg, mIvFocus;
        TextView mTvName;
    }
}
