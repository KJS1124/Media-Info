package com.example.mediainfo.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVSeries {
    private int id;
    @SerializedName("name")
    private String title;
    private int voteCount;
    private double voteAvg;
    private double popularity;
    @SerializedName("poster_path")
    private String image;
    private String lang;
    private String overview;
    private String releaseDate;
    private String backDropPath;
    private List<Season> seasons;


    public TVSeries() {
    }

    public TVSeries(int id, String title, int voteCount, double voteAvg, double popularity, String image, String lang, String overview, String releaseDate, String backDropPath, List<Season> seasons) {
        this.id = id;
        this.title = title;
        this.voteCount = voteCount;
        this.voteAvg = voteAvg;
        this.popularity = popularity;
        this.image = image;
        this.lang = lang;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.backDropPath = backDropPath;
        this.seasons = seasons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(double voteAvg) {
        this.voteAvg = voteAvg;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    @Override
    public String toString() {
        return "TVSeries{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", voteCount=" + voteCount +
                ", voteAvg=" + voteAvg +
                ", popularity=" + popularity +
                ", image='" + image + '\'' +
                ", lang='" + lang + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", backDropPath='" + backDropPath + '\'' +
                ", seasons=" + seasons +
                '}';
    }
}

