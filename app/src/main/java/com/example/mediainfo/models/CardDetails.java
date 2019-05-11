package com.example.mediainfo.models;

import com.google.gson.annotations.SerializedName;

public class CardDetails {
    String id;
    @SerializedName(value = "name",alternate = "title")
    String name;
    @SerializedName("poster_path")
    String image;
    @SerializedName("first_air_date")
    String date;

    public CardDetails() {
    }

    public CardDetails(String id, String name, String image, String date) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CardDetails{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
