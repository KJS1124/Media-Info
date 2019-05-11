package com.example.mediainfo.utils;

import android.net.Uri;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

public class URLUtils {
    private static final String SIZE = "w185";
    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p";



    public static URL getImageUrl(String image) {
        Uri uri = Uri.parse(BASE_IMAGE_URL)
                .buildUpon()
                .appendPath(SIZE)
                .appendPath(image)
                .build();
        Log.i("uri is ", uri.toString());
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.i("Created Path", url.toString());
        return url;
    }
}
