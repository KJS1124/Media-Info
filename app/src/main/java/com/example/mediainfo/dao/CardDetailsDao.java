package com.example.mediainfo.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mediainfo.models.CardDetails;
import com.example.mediainfo.models.Movie;

import java.util.List;

@Dao
public interface CardDetailsDao {

    @Query("Select * from details")
    LiveData<List<CardDetails>> loadAllDetails();

    @Insert
    void insertDetails(CardDetails details);

    @Update
    void updateDetails(CardDetails details);

    @Delete
    void deleteDetails(CardDetails details);

    @Query("Select *from details where id=:id")
    CardDetails getDetails(int id);
}
