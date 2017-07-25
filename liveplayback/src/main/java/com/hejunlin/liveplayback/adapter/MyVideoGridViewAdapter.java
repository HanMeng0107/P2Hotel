package com.hejunlin.liveplayback.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejunlin.liveplayback.R;
import com.hejunlin.liveplayback.entity.Movie;

import java.util.List;

public class MyVideoGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Movie> mfileitems = null;
    private LayoutInflater minflater = null;


    public MyVideoGridViewAdapter(Context context, List<Movie> fileItems) {
        this.context = context;
        this.mfileitems = fileItems;
        this.minflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mfileitems.size();
    }

    @Override
    public Object getItem(int position) {
        return mfileitems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder viewholder;
        if (view == null || view.getTag() == null) {
            view = minflater.inflate(R.layout.moviehome_gridview_videoitem, null);
            viewholder = new ViewHolder(view);
            view.setTag(viewholder);

        } else {
            viewholder = (ViewHolder) view.getTag();
        }
        Movie fileItem = (Movie) getItem(position);
        viewholder.video_name.setText(fileItem.getMovieName());
        return view;
    }


    private class ViewHolder {
        ImageView video_icon, video_onfocus;
        TextView video_name;

        ViewHolder(View view) {
            this.video_onfocus = (ImageView) view.findViewById(R.id.movie_item_onfocus);
            this.video_name = (TextView) view.findViewById(R.id.textView_videoName);
            this.video_icon = (ImageView) view.findViewById(R.id.imageView_videoIcon);
        }
    }

}
