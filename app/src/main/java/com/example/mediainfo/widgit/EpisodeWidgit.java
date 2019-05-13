package com.example.mediainfo.widgit;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.example.mediainfo.R;
import com.example.mediainfo.activites.DetailActivity;

/**
 * Implementation of App Widget functionality.
 */
public class EpisodeWidgit extends AppWidgetProvider {

    public static void updateAppWidget(Context applicationContext, AppWidgetManager appWidgetManager, int appWidgetId, String s) {
        RemoteViews views = new RemoteViews(applicationContext.getPackageName(), R.layout.episode_widgit);
        views.setTextViewText(R.id.appwidget_text, s);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        SharedPreferences sharedPreferences = context.getSharedPreferences(DetailActivity.SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(DetailActivity.EPISODE_PREFERENCES, null);


        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId,result);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

