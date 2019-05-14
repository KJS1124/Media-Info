package com.example.mediainfo.models;

public class Cast {
    String profile_path;
    String name;

    public Cast() {
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "profile_path='" + profile_path + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
