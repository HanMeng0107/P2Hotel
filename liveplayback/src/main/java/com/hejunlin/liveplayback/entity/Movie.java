package com.hejunlin.liveplayback.entity;

import java.io.Serializable;

public class Movie implements Serializable {
    private String movieName;
    private String movieGrade;
    private String movieDateAndArea;
    private String movieType;
    private String movieDuration;
    private String movieDirector;
    private String movieProtagonist;
    private String movieAbstract;
    private String moviePreviewPath;
    private String moviePlayPath;
    private int movieThumb;


    public Movie(String movieName, String movieGrade,
                 String movieDateAndArea,
                 String movieType, String movieDuration,
                 String movieDirector, String movieProtagonist,
                 String movieAbstract, String moviePreviewPath,
                 String moviePlayPath, int movieThumb) {
        this.movieName = movieName;
        this.movieGrade = movieGrade;
        this.movieDateAndArea = movieDateAndArea;
        this.movieType = movieType;
        this.movieDuration = movieDuration;
        this.movieDirector = movieDirector;
        this.movieProtagonist = movieProtagonist;

        this.movieAbstract = movieAbstract;
        this.moviePreviewPath = moviePreviewPath;
        this.moviePlayPath = moviePlayPath;
        this.movieThumb = movieThumb;
    }

    public Movie() {
        super();
    }

    public int getMovieThumb() {
        return movieThumb;
    }

    public void setMovieThumb(int movieThumb) {
        this.movieThumb = movieThumb;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieGrade() {
        return movieGrade;
    }

    public void setMovieGrade(String movieGrade) {
        this.movieGrade = movieGrade;
    }

    public String getMovieDateAndArea() {
        return movieDateAndArea;
    }

    public void setMovieDateAndArea(String movieDateAndArea) {
        this.movieDateAndArea = movieDateAndArea;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieProtagonist() {
        return movieProtagonist;
    }

    public void setMovieProtagonist(String movieProtagonist) {
        this.movieProtagonist = movieProtagonist;
    }

    public String getMovieAbstract() {
        return movieAbstract;
    }

    public void setMovieAbstract(String movieAbstract) {
        this.movieAbstract = movieAbstract;
    }

    public String getMoviePreviewPath() {
        return moviePreviewPath;
    }

    public void setMoviePreviewPath(String moviePreviewPath) {
        this.moviePreviewPath = moviePreviewPath;
    }

    public String getMoviePlayPath() {
        return moviePlayPath;
    }

    public void setMoviePlayPath(String moviePlayPath) {
        this.moviePlayPath = moviePlayPath;
    }
}
