package com.hejunlin.liveplayback.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.hejunlin.liveplayback.contacts.Contacts;
import com.hejunlin.liveplayback.entity.Movie;
import com.hejunlin.liveplayback.playfile.FileUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

import static com.hejunlin.liveplayback.utils.InitMovies.initLocalMovies;
import static com.hejunlin.liveplayback.utils.InitMovies.initNetWorkMovies;

public class LoadMovies {

    public static List<Movie> getLocalMovies(Context context) {

        List<Movie> movies = null;
        Movie movie;
        if (context != null) {
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                movies = new ArrayList<>();
                android.widget.Toast.makeText(context, "cursor.count=" + cursor.getCount(), android.widget.Toast.LENGTH_LONG).show();
                while (cursor.moveToNext()) {
                    movie = new Movie();

                    int id = cursor.getInt(cursor
                            .getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                    String title = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                    String album = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM));
                    String artist = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST));
                    String displayName = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                    String mimeType = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE));
                    String path = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                    long duration = cursor
                            .getInt(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                    long size = cursor
                            .getLong(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
                    if (size > 1024 * 1024 * 10) {

                        movie.setMovieName(displayName);
                        movie.setMovieDuration(String.valueOf(duration));
                        movie.setMoviePlayPath(path);
                        movies.add(movie);
                    }
                    Log.i("hm", "id=" + id + "\n" +
                            "title=" + title + "\n" +
                            "album=" + album + "\n" +
                            "artist=" + artist + "\n" +
                            "displayName=" + displayName + "\n" +
                            "mimeType=" + mimeType + "\n" +
                            "path=" + path + "\n" +
                            "size=" + size + "\n" +
                            "duration=" + duration + "\n");
                }
                cursor.close();
            }
        }
        return movies;
    }


    public static List<Movie> getLocalMovies(final List<Movie> localMovieList, File file) {
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName();
                int i = name.indexOf('.');
                if (i != -1) {
                    if (name.endsWith(".mp4")
                            || name.equalsIgnoreCase(".3gp")
                            || name.equalsIgnoreCase(".wmv")
                            || name.equalsIgnoreCase(".ts")
                            || name.equalsIgnoreCase(".rmvb")
                            || name.equalsIgnoreCase(".mov")
                            || name.equalsIgnoreCase(".m4v")
                            || name.equalsIgnoreCase(".avi")
                            || name.equalsIgnoreCase(".m3u8")
                            || name.equalsIgnoreCase(".3gpp")
                            || name.equalsIgnoreCase(".3gpp2")
                            || name.equalsIgnoreCase(".mkv")
                            || name.equalsIgnoreCase(".flv")
                            || name.equalsIgnoreCase(".divx")
                            || name.equalsIgnoreCase(".f4v")
                            || name.equalsIgnoreCase(".rm")
                            || name.equalsIgnoreCase(".asf")
                            || name.equalsIgnoreCase(".ram")
                            || name.equalsIgnoreCase(".mpg")
                            || name.equalsIgnoreCase(".v8")
                            || name.equalsIgnoreCase(".swf")
                            || name.equalsIgnoreCase(".m2v")
                            || name.equalsIgnoreCase(".asx")
                            || name.equalsIgnoreCase(".ra")
                            || name.equalsIgnoreCase(".ndivx")
                            || name.equalsIgnoreCase(".xvid")) {
                        Log.i("hm", "fileName=" + file.getName());
                        Movie movie = new Movie();
                        movie.setMovieName(file.getName());
                        movie.setMoviePlayPath(file.getPath());
                        localMovieList.add(movie);
                        return true;
                    }
                } else if (file.isDirectory()) {
                    getLocalMovies(localMovieList, file);
                }
                return false;
            }
        });
        return localMovieList;
    }


    public static List<Movie> filterLocalMovies(List<Movie> localMovieInfos) {
        List<Movie> newMovies = new ArrayList<>();
        for (Movie movieinfo : localMovieInfos) {
            File file = new File(movieinfo.getMoviePlayPath());
            if (file.exists() && file.isFile() && file.length() > 1024 * 1024 * 5) {
                newMovies.add(movieinfo);
            }
        }
        newMovies = initLocalMovies(newMovies);
        return newMovies;
    }


    public static List<Movie> getNetWorkMovie(final List<Movie> item) {
        Movie movie;
        jcifs.Config.setProperty("jcifs.smb.client.disablePlainTextPasswords", "false");
        try {
            SmbFile smbFile = new SmbFile(Contacts.DEFAULT_SMB_PATH);
            ArrayList<SmbFile> dirList = new ArrayList<>();
            ArrayList<SmbFile> fileList = new ArrayList<>();
            SmbFile[] fs = smbFile.listFiles();
            for (SmbFile f : fs) {
                if (f.isDirectory()) {
                    dirList.add(f);
                } else if (f.isFile()) {
                    fileList.add(f);
                }
            }
            dirList.addAll(fileList);
            for (SmbFile f : dirList) {
                movie = new Movie();
                movie.setMovieName(f.getName());
                movie.setMoviePlayPath(f.getPath());
                item.add(movie);
            }
        } catch (MalformedURLException | SmbException e) {
            e.printStackTrace();
        }
        return item;
    }

    public static List<Movie> filterNetWorkMovies(final List<Movie> networkMovieInfos) {
        List<Movie> fileItems = new ArrayList<>();
        if (!networkMovieInfos.isEmpty()) {
            for (Movie fileItem : networkMovieInfos) {
                String ipVal = FileUtil.ip;
                int portVal = FileUtil.port;
                String path = fileItem.getMoviePlayPath();
                String httpReq = "http://" + ipVal + ":" + portVal + "/smb=";
                if (path.endsWith(".avi") || path.endsWith(".rmvb") || path.endsWith(".rm") || path.endsWith(".asf")
                        || path.endsWith(".divx") || path.endsWith(".mpg") || path.endsWith(".mkv") || path.endsWith(".mpeg")
                        || path.endsWith(".mpe") || path.endsWith(".wmv") || path.endsWith(".mp4")) {
                    path = path.substring(6);
                    try {
                        path = URLEncoder.encode(path, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String url = httpReq + path;
                    Log.i("hm", "url=" + url);
                    fileItem.setMoviePlayPath(url);
                    fileItems.add(fileItem);
                }
            }
            fileItems = initNetWorkMovies(fileItems);
        }
        return fileItems;
    }
}
