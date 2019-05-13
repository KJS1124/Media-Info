package com.example.mediainfo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mediainfo.models.Movie;

import java.util.List;

@Dao
public interface  MovieDAO {
    @Query("Select * from movie")
    List<Movie> loadAllMovies();

    @Insert
    void insertMovie(Movie movie);

    @Update
    void updateMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

    @Query("Select *from movie where id=:id")
    Movie getMovie(int id);
}
