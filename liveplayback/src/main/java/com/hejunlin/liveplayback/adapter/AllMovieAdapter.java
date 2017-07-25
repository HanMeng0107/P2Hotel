package com.hejunlin.liveplayback.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejunlin.liveplayback.R;


/**
 * Created by admin on 2017/3/17.
 */

public class AllMovieAdapter extends RecyclerView.Adapter<AllMovieAdapter.ViewHolder> {

    private Activity activity;
    private int[] movies;
    private LayoutInflater inflater;

    private static ViewHolder.OnItemClickListener mOnItemClickListener;


    public AllMovieAdapter(Activity activity, int[] movies) {
        this.activity = activity;
        this.movies = movies;
        this.inflater = LayoutInflater.from(activity);
    }

    @Override
    public AllMovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_all_movies, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mTvName = (TextView) view.findViewById(R.id.movie_name);
        viewHolder.mIvMovie = (ImageView) view.findViewById(R.id.movie_img);
        viewHolder.mIvFocus = (ImageView) view.findViewById(R.id.movie_focus_img);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AllMovieAdapter.ViewHolder holder, final int position) {
        holder.mIvMovie.setBackgroundResource(movies[position]);
        holder.itemView.setFocusable(true);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    holder.mIvFocus.setVisibility(View.VISIBLE);
                    holder.mTvName.setTextColor(Color.parseColor("#0068B7"));
                    if (position < movies.length - 1){
                        Log.d("yimi","next："+getItemId(position+1));
                        holder.itemView.setNextFocusRightId(position + 1);
                    }
                } else {
                    holder.mIvFocus.setVisibility(View.GONE);
                    holder.mTvName.setTextColor(activity.getResources().getColor(R.color.moviehome_listview_unselectcolor));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return movies.length;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void setOnItemClickListener(ViewHolder.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        TextView mTvName;
        ImageView mIvMovie, mIvFocus;

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

}
