package com.example.mediainfo.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity(tableName = "details")
public class CardDetails implements Parcelable {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    String id;
    @SerializedName(value = "name",alternate = "title")
    String name;
    @SerializedName("poster_path")
    String image;
    @SerializedName("first_air_date")
    String date;

    String type;

    public CardDetails() {
    }

    @Ignore
    public CardDetails(String id, String name, String image, String date, String type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.date = date;
        this.type = type;
    }

    protected CardDetails(Parcel in) {
        id = in.readString();
        name = in.readString();
        image = in.readString();
        date = in.readString();
        type = in.readString();
    }

    public static final Creator<CardDetails> CREATOR = new Creator<CardDetails>() {
        @Override
        public CardDetails createFromParcel(Parcel in) {
            return new CardDetails(in);
        }

        @Override
        public CardDetails[] newArray(int size) {
            return new CardDetails[size];
        }
    };

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CardDetails{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(date);
        dest.writeString(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDetails that = (CardDetails) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
