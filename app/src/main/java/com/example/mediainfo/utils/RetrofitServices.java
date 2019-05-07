package com.example.mediainfo.utils;

import com.example.mediainfo.utils.MovieApiInterface;

import retrofit2.Retrofit;

public class RetrofitServices {

    public static final String BASE_URL = "http://api.themoviedb.org/3/movie";
    public static final String SIZE = "w185";
    public static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p";


    Retrofit retrofit;

    private boolean isNull(){
        return retrofit == null;
    }

    public MovieApiInterface getMovieService(){
        Retrofit retrofit = getInstance();
        return retrofit.create(MovieApiInterface.class);
    }


    private Retrofit getInstance() {
        if(isNull()){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();
        }

        return retrofit;
    }

}
