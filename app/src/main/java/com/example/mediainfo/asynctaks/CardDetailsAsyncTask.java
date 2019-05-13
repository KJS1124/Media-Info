package com.example.mediainfo.asynctaks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.mediainfo.database.AppDatabase;
import com.example.mediainfo.models.CardDetails;

public class CardDetailsAsyncTask {
    private AppDatabase mDB;
    private Context context;

    public CardDetailsAsyncTask(Context context) {
        this.context = context;
        this.mDB = AppDatabase.getInstance(this.context);
    }

    private class SaveDetails extends AsyncTask<CardDetails, Void, CardDetails> {

        @Override
        protected CardDetails doInBackground(CardDetails... cardDetails) {
            CardDetails detail = cardDetails[0];
            mDB.cardDetailsDao().insertDetails(detail);
            return detail;
        }

        @Override
        protected void onPostExecute(CardDetails cardDetails) {
            super.onPostExecute(cardDetails);
            Toast
                    .makeText(context, cardDetails.getName() + "Saved to Database", Toast.LENGTH_LONG)
                    .show();
        }
    }

    private class DeleteDetails extends AsyncTask<CardDetails, Void, CardDetails> {

        @Override
        protected CardDetails doInBackground(CardDetails... cardDetails) {
            CardDetails detail = cardDetails[0];
            mDB.cardDetailsDao().deleteDetails(detail);
            return detail;
        }

        @Override
        protected void onPostExecute(CardDetails cardDetails) {
            super.onPostExecute(cardDetails);
            Toast
                    .makeText(context, cardDetails.getName() + "Deleted from Database", Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void saveDetails(CardDetails details){
        new CardDetailsAsyncTask.SaveDetails().execute(details);
    }

    public void deleteDetails(CardDetails details){
        new CardDetailsAsyncTask.DeleteDetails().execute(details);
    }
}
