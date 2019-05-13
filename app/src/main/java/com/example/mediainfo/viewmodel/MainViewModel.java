package com.example.mediainfo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.mediainfo.database.AppDatabase;
import com.example.mediainfo.models.CardDetails;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<CardDetails>> cardDetails;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        cardDetails = database.cardDetailsDao().loadAllDetails();
    }

    public LiveData<List<CardDetails>> getDetails() {
        return cardDetails;
    }
}
