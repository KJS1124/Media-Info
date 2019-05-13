package com.example.mediainfo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mediainfo.models.Movie;
import com.example.mediainfo.models.TVSeries;

import java.util.List;

@Dao
public interface  TVDAO {
    @Query("Select * from tvseries")
    List<Movie> loadAllTVSeries();

    @Insert
    void insertTVSeries(TVSeries tvSeries);

    @Update
    void updateTVSeries(TVSeries tvSeries);

    @Delete
    void deleteTVSeries(TVSeries tvSeries);

    @Query("Select *from tvseries where id=:id")
    Movie getTvSeries(int id);
}
