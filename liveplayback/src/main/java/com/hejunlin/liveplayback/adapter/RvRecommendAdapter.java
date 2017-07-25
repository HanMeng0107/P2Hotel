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
import com.hejunlin.liveplayback.entity.Movie;

import java.util.List;


/**
 * Created by 星 on 2017/3/27.
 */

public class RvRecommendAdapter extends RecyclerView.Adapter<RvRecommendAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movies;
    private LayoutInflater inflater;
    private static OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Movie data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public RvRecommendAdapter(Context context, List<Movie> movieInfos) {
        this.context = context;
        this.movies = movieInfos;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recommend, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTvName.setText(movies.get(position).getMovieName());
        holder.mIvBgMovieImg.setImageResource(movies.get(position).getMovieThumb());
        holder.itemView.setFocusable(true);
        holder.itemView.setTag(movies.get(position));
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    holder.mIvBgFocus.setVisibility(View.VISIBLE);
                    holder.mTvName.setTextColor(Color.WHITE);
                    holder.mIvBgName.setBackgroundResource(R.mipmap.bg_movie_name_focus);
                } else {
                    holder.mIvBgFocus.setVisibility(View.GONE);
                    holder.mTvName.setTextColor(Color.parseColor("#e6e6e6"));
                    holder.mIvBgName.setBackgroundResource(R.mipmap.bg_movie_name);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mIvBgMovieImg = (ImageView) itemView.findViewById(R.id.movie_thumb);
            mIvBgFocus = (ImageView) itemView.findViewById(R.id.im_movie_bg);
            mIvBgName = (ImageView) itemView.findViewById(R.id.im_name_bg);
            mTvName = (TextView) itemView.findViewById(R.id.movie_name);
        }

        ImageView mIvBgFocus, mIvBgName, mIvBgMovieImg;
        TextView mTvName;

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(view, (Movie) view.getTag());
            }
        }
    }
}
