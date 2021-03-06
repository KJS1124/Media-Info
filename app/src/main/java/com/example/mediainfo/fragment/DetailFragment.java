package com.example.mediainfo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mediainfo.R;
import com.example.mediainfo.adapters.CastCardItemAdapter;
import com.example.mediainfo.adapters.ListItemCardAdarpter;
import com.example.mediainfo.adapters.VideoAdapter;
import com.example.mediainfo.models.Cast;
import com.example.mediainfo.models.Movie;
import com.example.mediainfo.models.TVSeries;
import com.example.mediainfo.models.Video;
import com.example.mediainfo.utils.RetrofitServices;
import com.example.mediainfo.wrapper.CastWrapper;
import com.example.mediainfo.wrapper.VideoWrapper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment implements VideoAdapter.CustomClickListner {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = DetailFragment.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.overview_content)
    TextView overview;
    @BindView(R.id.no_of_seasons)
    TextView noOfSeasons;
    @BindView(R.id.no_of_episode)
    TextView noOFEpisodes;
    @BindView(R.id.rv_trailers)
    RecyclerView mRVtrailers;
    @BindView(R.id.rv_cast)
    RecyclerView mRVCast;
    @BindView(R.id.cardView_season_data)
    LinearLayout layout;
    @BindView(R.id.no_season_title)
    TextView noSeasonTitle;
    @BindView(R.id.trailer_titles)
    TextView trailerTitle;
    @BindView(R.id.cast_title)
    TextView castTitle;


    @BindView(R.id.no_episode_title)
    TextView noEpisodeTitle;


    private OnFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        VideoAdapter.CustomClickListner listner = this;
        String id = getArguments().getString(ARG_PARAM1);
        String controller = getArguments().getString(ARG_PARAM2);

        if (controller.toUpperCase().equals("TV")) {
            RetrofitServices.getTVService().getTVSeries(id).enqueue(new Callback<TVSeries>() {
                @Override
                public void onResponse(Call<TVSeries> call, Response<TVSeries> response) {

                    TVSeries series = response.body();
                    Log.i(TAG, "onResponse: " + series);
                    overview.setText(series.getOverview());

                    noOFEpisodes.setVisibility(View.VISIBLE);
                    noOfSeasons.setVisibility(View.VISIBLE);
                    trailerTitle.setVisibility(View.GONE);
                    mRVtrailers.setVisibility(View.GONE);
                    noOfSeasons.setText(String.valueOf(series.getSeasons().size() - 1));
                    LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    mRVCast.setLayoutManager(manager);
                    CastCardItemAdapter adapter = new CastCardItemAdapter();
                    RetrofitServices.getTVService().getCast(series.getId()).enqueue(new Callback<CastWrapper>() {
                        @Override
                        public void onResponse(Call<CastWrapper> call, Response<CastWrapper> response) {
                            CastWrapper wrapper = response.body();
                            List<Cast> cast = wrapper.getCast();
                            Log.i(TAG, "onResponse: " + cast);
                            adapter.setData(cast);
                        }

                        @Override
                        public void onFailure(Call<CastWrapper> call, Throwable t) {

                        }
                    });
                    mRVCast.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<TVSeries> call, Throwable t) {

                }
            });
        } else {
            RetrofitServices.getMovieService().movie(id).enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Movie movie = response.body();
                    overview.setText(movie.getOverview());
                    layout.setVisibility(View.INVISIBLE);
                    castTitle.setVisibility(View.GONE);
                    RetrofitServices.getMovieService().videos(id).enqueue(new Callback<VideoWrapper>() {
                        @Override
                        public void onResponse(Call<VideoWrapper> call, Response<VideoWrapper> response) {
                            Log.i("TEsting", "onResponse: "+response.body());
                            LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                            mRVtrailers.setLayoutManager(manager);
                            VideoWrapper wrapper = response.body();;
                            List<Video> videos = wrapper.getResults();
                            mRVtrailers.setAdapter(new VideoAdapter(videos,listner));
                        }

                        @Override
                        public void onFailure(Call<VideoWrapper> call, Throwable throwable) {
                            Log.i("TEsting", "onFailure: "+throwable);
                        }
                    });

                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Video video) {
        if (mListener != null) {
            mListener.onFragmentInteraction(video);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    public void setEpisodeCount(int count){
        if(noOFEpisodes!=null)
            noOFEpisodes.setText(String.valueOf(count==0?"Please Try Later":count-1));
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(Video video) {
        mListener.onFragmentInteraction(video);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Video video);
    }
}
