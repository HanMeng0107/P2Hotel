package com.hejunlin.liveplayback.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hejunlin.liveplayback.R;
import com.hejunlin.liveplayback.entity.Restaurant;

import java.util.List;

/**
 * Created by rbtmk on 2017/4/16.
 */

public class FoodInfoAdapter extends RecyclerView.Adapter<FoodInfoAdapter.FoodInfoViewHolder> {

    private Context context;
    private LayoutInflater inflater;

    private List<Restaurant> list;

    public FoodInfoAdapter(Context context, List<Restaurant> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public FoodInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_info_food, parent, false);
        FoodInfoViewHolder holder = new FoodInfoViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(FoodInfoViewHolder holder, int position) {
        Restaurant restaurant = list.get(position);
        holder.name.setText(restaurant.getName());
        holder.score.setText(restaurant.getScore()+"分");
        holder.time.setText(restaurant.getTime());
        holder.phone.setText(restaurant.getPhone());
        holder.address.setText(restaurant.getAddress());
        holder.itemView.setFocusable(true);
    }

    public static class FoodInfoViewHolder extends RecyclerView.ViewHolder {
        public FoodInfoViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.restaurant_name);
            score = (TextView) itemView.findViewById(R.id.restaurant_score);
            time = (TextView) itemView.findViewById(R.id.restaurant_time);
            address = (TextView) itemView.findViewById(R.id.restaurant_address);
            phone = (TextView) itemView.findViewById(R.id.restaurant_phone);
        }

        TextView name, score, time, address, phone;
    }
}
