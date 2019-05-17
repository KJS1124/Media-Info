package com.example.mediainfo.activites;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mediainfo.adapters.VideoAdapter;
import com.example.mediainfo.models.Movie;
import com.example.mediainfo.models.Video;
import com.example.mediainfo.widgit.EpisodeWidgit;
import com.example.mediainfo.R;
import com.example.mediainfo.fragment.DetailFragment;
import com.example.mediainfo.models.Episode;
import com.example.mediainfo.models.Season;
import com.example.mediainfo.models.TVSeries;
import com.example.mediainfo.utils.RetrofitServices;
import com.example.mediainfo.utils.URLUtils;
import com.example.mediainfo.wrapper.EpisodeResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements DetailFragment.OnFragmentInteractionListener {

    private static final String TAG = DetailActivity.class.getSimpleName();
    public static final String INTENT_DATA = "detail_activity_intent_data";
    public static final String INTENT_CONTROLLER_DATA = "detail_activity_intent_controller_data";
    FloatingActionButton fabButton;
    public static final String SHARED_PREFERENCES = "MOVIE_PREF";
    public static final String EPISODE_PREFERENCES = "episodes";
    private List<Episode> episodes = new ArrayList<>();
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String id = getIntent().getStringExtra(INTENT_DATA);
        String controller = getIntent().getStringExtra(INTENT_CONTROLLER_DATA);
        mImageView = findViewById(R.id.photo);
        setSupportActionBar(findViewById(R.id.toolbarDetail));

        FragmentManager manager = getSupportFragmentManager();
        DetailFragment fragment = DetailFragment.newInstance(id,controller);
        manager.beginTransaction()
                .add(R.id.detailed_fragment_container,fragment)
                .commit();
        fabButton = findViewById(R.id.fabbutton_widgit);
        if(controller.toUpperCase().equals("TV")){
            RetrofitServices.getTVService().getTVSeries(id).enqueue(new Callback<TVSeries>() {
                @Override
                public void onResponse(Call<TVSeries> call, Response<TVSeries> response) {
                    TVSeries tvSeries = response.body();
                    Log.i(TAG, "onResponse: " + tvSeries);
                    ((CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout)).setTitle(tvSeries.getTitle());
                    Picasso.get()
                            .load(String.valueOf(URLUtils.getImageUrl(tvSeries
                                    .getImage().replace("/",""))))
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).into(mImageView);

                    for(Season season : tvSeries.getSeasons()){
                        RetrofitServices.getTVService().getEpisodes(tvSeries.getId(),season.getId()).enqueue(episodeCallback);
                    }

                    fragment.setEpisodeCount(episodes.size());
                    fabButton.show();
                    fabButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences sharedPreferences = getApplicationContext()
                                    .getSharedPreferences(SHARED_PREFERENCES, getApplicationContext().MODE_PRIVATE);
                            sharedPreferences.edit().putString(EPISODE_PREFERENCES,tvSeries.getTitle()+"\n"+
                                    episodes).apply();
                            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                            Bundle bundle = new Bundle();
                            int appWidgetId = bundle.getInt(
                                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                                    AppWidgetManager.INVALID_APPWIDGET_ID);
                            EpisodeWidgit.updateAppWidget(getApplicationContext(), appWidgetManager, appWidgetId, tvSeries.getTitle()+"\n"+
                                    episodes.toString());
                            Toast.makeText(getApplicationContext(), "Added " + tvSeries.getTitle() + " to Widget.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onFailure(Call<TVSeries> call, Throwable t) {

                }
            });
        }
        else{
            RetrofitServices.getMovieService().movie(id).enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Movie movie = response.body();
                    Log.i(TAG, "onResponse: "+movie);
                    ((CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout)).setTitle(movie.getTitle());
                    Picasso.get()
                            .load(String.valueOf(URLUtils.getImageUrl(movie
                                    .getImage().replace("/",""))))
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).into(mImageView);
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });
        }


    }

    Callback<EpisodeResult> episodeCallback = new Callback<EpisodeResult>() {
        @Override
        public void onResponse(Call<EpisodeResult> call, Response<EpisodeResult> response) {
            EpisodeResult episodesResult = response.body();
            if(episodesResult!=null && episodesResult.getEpisodes() !=null)
                episodes.addAll(episodesResult.getEpisodes());
        }

        @Override
        public void onFailure(Call<EpisodeResult> call, Throwable t) {
            Log.i(TAG, "onFailure: " + t);
        }
    };
  @Override
    public void onFragmentInteraction(Video video) {
      Intent intent = new Intent(Intent.ACTION_VIEW, VideoAdapter.getYoutubeVideoUrl(video.getKey()));
      if(intent.resolveActivity(getPackageManager()) != null)
          startActivity(intent);
    }
}
