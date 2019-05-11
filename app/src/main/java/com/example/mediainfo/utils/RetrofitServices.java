package com.example.mediainfo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServices {

    private static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/movie/";
    private static final String TV_BASE_URL = "https://api.themoviedb.org/3/tv/";


    private static Gson gson = new GsonBuilder().create();
    static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();



    private static Retrofit retrofit;

    private static Retrofit retrofit2;

    private static boolean isNull(){
        return retrofit == null;
    }

    private static boolean isNull2(){
        return retrofit2 == null;
    }

    public static MovieApiInterface getMovieService(){
        Retrofit retrofit = getMovieInstance();
        return retrofit.create(MovieApiInterface.class);
    }

    public static TVAPIInterface getTVService(){
        Retrofit retrofit = getTVInstance();
        return retrofit.create(TVAPIInterface.class);
    }


    private static Retrofit getMovieInstance() {
        if(isNull()){
            retrofit = new Retrofit.Builder()
                    .baseUrl(MOVIE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .callFactory(httpClientBuilder.build())
                    .build();
        }

        return retrofit;
    }

    private static Retrofit getTVInstance() {
        if(isNull2()){
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(TV_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .callFactory(httpClientBuilder.build())
                    .build();
        }

        return retrofit2;
    }

}
