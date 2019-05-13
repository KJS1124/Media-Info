package com.example.mediainfo.utils;

import com.example.mediainfo.models.TVSeries;
import com.example.mediainfo.wrapper.EpisodeResult;
import com.example.mediainfo.wrapper.ResultWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TVAPIInterface {
    public static final String KEY_PARAM = "api_key";
    public static final String API_KEY = "01fc236f22edef7f8ecfb2fc4207c898";
    public static final String POPULAR_TV_PATH_PARAM = "popular";
    public static final String TOP_TV_PATH_PARAM = "top_rated";
    public static final String VIDEOS_PATH_PARAM = "videos";
    public static final String REVIEW_PATH_PARAM = "reviews";

    @GET(POPULAR_TV_PATH_PARAM +"?"+KEY_PARAM+"="+API_KEY)
    Call<ResultWrapper> popularTV();

    @GET(POPULAR_TV_PATH_PARAM +"?"+KEY_PARAM+"="+API_KEY)
    Call<ResultWrapper> popularTvAndPage(@Query("page") int page);

    @GET(TOP_TV_PATH_PARAM +"?"+KEY_PARAM+"="+API_KEY)
    Call<ResultWrapper> topTV();

    @GET(TOP_TV_PATH_PARAM +"?"+KEY_PARAM+"="+API_KEY)
    Call<ResultWrapper> topTVAndPage(@Query("page")int page);

    @GET("{id}"+"?"+KEY_PARAM+"="+API_KEY)
    Call<TVSeries> getTVSeries(@Path("id") String id);

    @GET("{id}/season/{id1}"+"?"+KEY_PARAM+"="+API_KEY)
    Call<EpisodeResult> getEpisodes(@Path("id")int id,@Path("id1") int id1);
}
