package com.example.mediainfo.services;

import android.app.PendingIntent;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;

import com.example.mediainfo.R;
import com.example.mediainfo.database.AppDatabase;
import com.example.mediainfo.models.CardDetails;
import com.example.mediainfo.viewmodel.MainViewModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

public class MessagingService extends FirebaseMessagingService {
    List<CardDetails> cardDetails;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        AppDatabase mDB = AppDatabase.getInstance(getApplicationContext());
        cardDetails = mDB.cardDetailsDao().loadAllDetails().getValue();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        String key = getResources().getString(R.string.notification_key);
        boolean notify = (boolean) sharedPreferences.getBoolean(key,true);
        if(cardDetails.equals(remoteMessage.getNotification().getTitle())
                && notify)
            showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    private void showNotification(String title, String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"Main Notification")
                .setContentTitle(title)
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .setAutoCancel(true)
                .setContentText(message);

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("Cloud Messaging Token", "onNewToken: "+ s);
    }
}
