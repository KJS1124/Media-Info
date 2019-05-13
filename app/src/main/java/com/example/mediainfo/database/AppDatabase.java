package com.example.mediainfo.database;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mediainfo.dao.CardDetailsDao;
import com.example.mediainfo.dao.MovieDAO;
import com.example.mediainfo.dao.TVDAO;
import com.example.mediainfo.models.CardDetails;
import com.example.mediainfo.models.Movie;
import com.example.mediainfo.models.TVSeries;

@Database(entities = {CardDetails.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String LOG_TAG = AppDatabase.class.getSimpleName();
    public static final String databaseName = "PopularMoviesApp";
    public static final Object LOCK = new Object();
    public static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (null == sInstance) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "creating database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,
                        AppDatabase.databaseName)
                        .addCallback(roomCallback)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting Database Instance");
        return sInstance;
    }

    public  static  RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    public abstract CardDetailsDao cardDetailsDao();
}