package com.example.mediainfo.utils;

import com.example.mediainfo.models.CardDetails;
import com.example.mediainfo.models.Movie;
import com.example.mediainfo.models.Review;
import com.example.mediainfo.models.Video;
import com.example.mediainfo.wrapper.ResultWrapper;
import com.example.mediainfo.wrapper.VideoWrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiInterface {

    public static final String KEY_PARAM = "api_key";
    public static final String API_KEY = "01fc236f22edef7f8ecfb2fc4207c898";
    public static final String POPULAR_MOVIE_PATH_PARAM= "popular";
    public static final String TOP_MOVIE_PATH_PARAM = "top_rated";
    public static final String VIDEOS_PATH_PARAM = "videos";
    public static final String REVIEW_PATH_PARAM = "reviews";

    @GET(POPULAR_MOVIE_PATH_PARAM+"?"+KEY_PARAM+"="+API_KEY)
    Call<ResultWrapper> popularMovie();

    @GET(POPULAR_MOVIE_PATH_PARAM+"?"+KEY_PARAM+"="+API_KEY)
    Call<ResultWrapper> popularMovieAndPage(@Query("page") int page);

    @GET(TOP_MOVIE_PATH_PARAM+"?"+KEY_PARAM+"="+API_KEY)
    Call<ResultWrapper> topMovie();

    @GET(TOP_MOVIE_PATH_PARAM+"?"+KEY_PARAM+"="+API_KEY)
    Call<ResultWrapper> topMovieAndPage(@Query("page")int page);

    @GET("{id}"+"?"+KEY_PARAM+"="+API_KEY)
    Call<Movie> movie(@Path("id") String id);

    @GET("{id}/"+VIDEOS_PATH_PARAM+"?"+KEY_PARAM+"="+API_KEY)
    Call<VideoWrapper> videos(@Path("id") String id);

    @GET("{id}/"+REVIEW_PATH_PARAM+"?"+KEY_PARAM+"="+API_KEY)
    Call<List<Review>> reviews(@Path("id") String id);
}

