package com.example.mediainfo.models;

public class Video {
    String name;
    String key;

    public Video(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
