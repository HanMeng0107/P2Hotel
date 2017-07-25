package com.hejunlin.liveplayback.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hejunlin.liveplayback.R;
import com.hejunlin.liveplayback.entity.Goods;


/**
 * Created by rbtmk on 2017/3/28.
 */

public class ShopMenuAdapter extends RecyclerView.Adapter<ShopMenuAdapter.ShopMenuViewHolder> {

    private Context context;
    private String[] menus = {"推荐", "烟酒", "餐饮", "零食", "特产", "礼品", "设备"};
    private LayoutInflater inflater;

    private static OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private static OnRecyclerViewItemSelectListener mSelectListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Goods data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnRecyclerViewItemSelectListener {
        void onItemSelect(View view, int position);
    }

    public void setOnItemSelectListener(OnRecyclerViewItemSelectListener listener) {
        this.mSelectListener = listener;
    }


    public ShopMenuAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ShopMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_shop_menu, parent, false);
        ShopMenuViewHolder holder = new ShopMenuViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ShopMenuViewHolder holder, int position) {
        holder.name.setText(menus[position]);
        holder.itemView.setFocusable(true);
        holder.itemView.setId(position);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    holder.name.setTextColor(Color.WHITE);
                    holder.name.setBackgroundResource(R.drawable.bg_movie_type);
                    holder.vFocus.setVisibility(View.VISIBLE);
                    mSelectListener.onItemSelect(holder.itemView, holder.itemView.getId());
                } else {
                    holder.name.setTextColor(Color.parseColor("#999999"));
                    holder.name.setBackground(null);
                    holder.vFocus.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.length;
    }

    public static class ShopMenuViewHolder extends RecyclerView.ViewHolder {

        public ShopMenuViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.shop_menu_name);
            vFocus = itemView.findViewById(R.id.shop_menu_focus);
        }

        View vFocus;
        TextView name;
    }
}
