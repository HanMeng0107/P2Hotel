package com.hejunlin.liveplayback.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hejunlin.liveplayback.R;
import com.makeramen.roundedimageview.RoundedImageView;


/**
 * Created by rbtmk on 2017/4/14.
 */

public class ViewInfoAdapter extends RecyclerView.Adapter<ViewInfoAdapter.ViewInfoViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private Intent intent;

    private int[] views;
    private int[] introduce = {R.string.view1, R.string.view2, R.string.view3, R.string.view4, R.string.view1, R.string.view2, R.string.view3, R.string.view4};
    private int lastPos = -1;

    public ViewInfoAdapter(Context context, int[] views) {
        this.context = context;
        this.views = views;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_info_view, parent, false);
        ViewInfoViewHolder holder = new ViewInfoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewInfoViewHolder holder, final int position) {
        holder.mTvIntroduce.setText(introduce[position]);
        holder.img.setBackgroundResource(views[position]);
        holder.itemView.setFocusable(true);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    intent = new Intent("com.kupa.view.position");
                    intent.putExtra("nowPos", position);
                    intent.putExtra("lastPos", lastPos);
                    context.sendBroadcast(intent);
                } else {
                    lastPos = position;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return views.length;
    }

    public static class ViewInfoViewHolder extends RecyclerView.ViewHolder {

        public ViewInfoViewHolder(View itemView) {
            super(itemView);
            img = (RoundedImageView) itemView.findViewById(R.id.view_info_img);
            mTvIntroduce = (TextView) itemView.findViewById(R.id.view_info_introduce);
            img.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        }

        RoundedImageView img;
        TextView mTvIntroduce;
    }
}
