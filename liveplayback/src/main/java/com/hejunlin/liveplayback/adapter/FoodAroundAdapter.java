package com.hejunlin.liveplayback.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejunlin.liveplayback.R;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by rbtmk on 2017/4/15.
 */

public class FoodAroundAdapter extends RecyclerView.Adapter<FoodAroundAdapter.FoodViewHolder> {

    private Context context;
    private LayoutInflater inflater;

    private String[] names;
    private int[] imgs;

    private Intent intent;

    public FoodAroundAdapter(Context context, int[] imgs, String[] names) {
        this.context = context;
        this.imgs = imgs;
        this.names = names;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_food, parent, false);
        FoodViewHolder holder = new FoodViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final FoodViewHolder holder, final int position) {
        holder.mTvName.setText(names[position]);
        holder.img.setBackgroundResource(imgs[position]);
        holder.itemView.setFocusable(true);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    holder.mTvName.setVisibility(View.GONE);
                    holder.mIvFocus.setVisibility(View.GONE);
                    holder.mIvBorder.setVisibility(View.VISIBLE);
                    holder.mIvBg.setVisibility(View.VISIBLE);

                    intent = new Intent("com.kupa.food.position");
                    intent.putExtra("pos", position);
                    context.sendBroadcast(intent);
                } else {
                    holder.mTvName.setVisibility(View.VISIBLE);
                    holder.mIvFocus.setVisibility(View.VISIBLE);
                    holder.mIvBorder.setVisibility(View.GONE);
                    holder.mIvBg.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgs.length;
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {

        public FoodViewHolder(View itemView) {
            super(itemView);
            mIvFocus = itemView.findViewById(R.id.item_food_focus);
            mIvBorder = itemView.findViewById(R.id.item_food_border);
            img = (RoundedImageView) itemView.findViewById(R.id.item_food_img);
            mTvName = (TextView) itemView.findViewById(R.id.item_food_name);
            mIvBg = (ImageView) itemView.findViewById(R.id.item_food_bg);
        }

        ImageView mIvBg;
        View mIvFocus, mIvBorder;
        RoundedImageView img;
        TextView mTvName;
    }
}
