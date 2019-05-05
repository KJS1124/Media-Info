package com.example.mediainfo.models;

public class CastCardDetails {
    String image;
    String name;

    public CastCardDetails() {
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "CastCardDetails{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
