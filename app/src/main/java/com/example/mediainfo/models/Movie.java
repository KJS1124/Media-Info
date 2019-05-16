package com.example.mediainfo.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class Movie {
    int id;
    String title;
    int voteCount;
    double voteAvg;
    double popularity;
    @SerializedName("poster_path")
    String image;
    String lang;
    String overview;
    String releaseDate;
    String backDropPath;

    @Ignore
    public Movie(String title, int voteCount, double voteAvg, double popularity, String image, String lang, String overview, String releaseDate, String backDropPath) {
        this.title = title;
        this.voteCount = voteCount;
        this.voteAvg = voteAvg;
        this.popularity = popularity;
        this.image = image;
        this.lang = lang;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.backDropPath = backDropPath;
    }

    public Movie(int id, String title, int voteCount, double voteAvg, double popularity, String image, String lang, String overview, String releaseDate, String backDropPath) {
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
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", voteCount=" + voteCount +
                ", voteAvg=" + voteAvg +
                ", popularity=" + popularity +
                ", image='" + image + '\'' +
                ", lang='" + lang + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", backDropPath='" + backDropPath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}