package com.hejunlin.liveplayback.utils;

import com.hejunlin.liveplayback.R;
import com.hejunlin.liveplayback.entity.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HM on 2017/5/4 14:50
 */

public class MoviesUtils {
    public static List<Movie> createMovies(int[] movieIcons, String[] movieNames) {
        List<Movie> movieInfos = new ArrayList<>();
        Movie movie;
        for (int i = 0; i < movieIcons.length; i++) {
            movie = new Movie();
            movie.setMovieName(movieNames[i]);
            movie.setMovieThumb(movieIcons[i]);
            movie.setMoviePreviewPath("android.resource://com.hejunlin.liveplayback/" + R.raw.zn);
            movieInfos.add(movie);
        }
        return movieInfos;
    }
}
