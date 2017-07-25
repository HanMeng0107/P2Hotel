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
 * Created by HM on 2017/3/18 15:14
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Movie> mMovies;

    private int currentPosition;

    private static OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private static OnRecyclerViewItemSelectListener mSelectListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Movie goods);
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

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.inflater = LayoutInflater.from(context);
        this.mMovies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.moviehome_gridview_videoitem, parent, false);
        ViewHolder holder = new ViewHolder(view);

//        holder.movieThumb = (ImageView) view.findViewById(R.id.imageView_videoIcon);
//        holder.mIvFocus = (ImageView) view.findViewById(R.id.movie_item_onfocus);
//        holder.movieName = (TextView) view.findViewById(R.id.textView_videoName);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Movie movie = mMovies.get(position);

        holder.movieThumb.setImageResource(movie.getMovieThumb());

        String[] strs = movie.getMovieName().split("[.]");
        holder.movieName.setText(strs[0]);

        holder.itemView.setId(position);

        holder.itemView.setTag(movie);
        holder.itemView.setFocusable(true);

        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    holder.mIvFocus.setVisibility(View.VISIBLE);
                    holder.movieName.setTextColor(Color.parseColor("#046ab8"));
                    currentPosition = (int) holder.itemView.getId();
                    mSelectListener.onItemSelect(holder.itemView, currentPosition);

                } else {
                    holder.mIvFocus.setVisibility(View.GONE);
                    holder.movieName.setTextColor(Color.parseColor("#666666"));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView movieThumb, mIvFocus;
        TextView movieName, movieGrade, movieDateAndArea, movieType, movieDuration, movieDirector, movieProtagonist, movieAbstract, moviePreviewPath, moviePlayPath;

        public ViewHolder(View itemView) {
            super(itemView);
            movieThumb = (ImageView) itemView.findViewById(R.id.imageView_videoIcon);
            mIvFocus = (ImageView) itemView.findViewById(R.id.movie_item_onfocus);
            movieName = (TextView) itemView.findViewById(R.id.textView_videoName);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(view, (Movie) view.getTag());
            }
        }
    }

}
