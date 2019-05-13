package com.example.mediainfo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mediainfo.R;
import com.example.mediainfo.adapters.ListItemCardAdarpter;
import com.example.mediainfo.decorator.GridSpacingItemDecoration;
import com.example.mediainfo.models.CardDetails;
import com.example.mediainfo.utils.RetrofitServices;
import com.example.mediainfo.wrapper.CommonListFragment;
import com.example.mediainfo.wrapper.ResultWrapper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovieFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieFragment extends Fragment implements ListItemCardAdarpter.ItemCardListner, CommonListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean isScrolling=false;
    private ListItemCardAdarpter adapter;
    private RecyclerView.LayoutManager manager;

    private OnFragmentInteractionListener mListener;

    RecyclerView mRecyclerView;
    ProgressBar mPB;
    TextView mError;
    boolean isPopular= false;



    public MovieFragment() {
        // Required empty public constructo
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieFragment newInstance(String param1, String param2) {
        MovieFragment fragment = new MovieFragment();
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
        if(savedInstanceState!=null)
            Log.i("Test", "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        this.mRecyclerView = view.findViewById(R.id.rv_movie);
        this.mPB = view.findViewById(R.id.pb_loading);
        this.manager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, GridSpacingItemDecoration.dpToPx(10), true));
        mRecyclerView.setLayoutManager(manager);
        this.adapter = new ListItemCardAdarpter(this);
        mRecyclerView.setAdapter(adapter);
        Log.i("Test", "onCreateView: "+savedInstanceState);

        if(savedInstanceState!=null){
            isPopular = savedInstanceState.getBoolean("isPopular");
            adapter.setData(savedInstanceState.getParcelableArrayList("data"));
        } else{
            createList(isPopular);
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(id,"movie");
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void click(CardDetails cardDetails) {
        this.mListener.onFragmentInteraction(cardDetails.getId(),"movie");
    }

    @Override
    public void clickFav(CardDetails cardDetails) {
        mListener.clickOnIcon(cardDetails,"MOVIE");
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
        void onFragmentInteraction(String id,String controller);
        void clickOnIcon(CardDetails cardDetails,String controller);
    }

    Callback<ResultWrapper> firstCallBack = new Callback<ResultWrapper>() {
        @Override
        public void onResponse(Call<ResultWrapper> call, Response<ResultWrapper> response) {
            ResultWrapper data = response.body();
            Log.d("data", "onResponse: "+data.getResults());
            adapter.setData(data.getResults());
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                        isScrolling = true;

                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(isScrolling && (manager.getChildCount()+((GridLayoutManager) manager).findFirstVisibleItemPosition() == manager.getItemCount())){
                        isScrolling = false;
                        mPB.setVisibility(View.VISIBLE);
                        if(isPopular)
                        RetrofitServices.getMovieService().popularMovieAndPage((manager.getItemCount()/20)+1)
                                .enqueue(pagesCallback);
                        else
                            RetrofitServices.getMovieService().topMovieAndPage((manager.getItemCount()/20)+1)
                                    .enqueue(pagesCallback);

                    }
                }
            });
        }

        @Override
        public void onFailure(Call<ResultWrapper> call, Throwable t) {
            Log.i("Failure in api", "onFailure: "+ t);
        }
    };


    Callback<ResultWrapper> pagesCallback = new Callback<ResultWrapper>() {

        @Override
        public void onResponse(Call<ResultWrapper> call, Response<ResultWrapper> response) {
            ResultWrapper data = response.body();
            Log.d("data", "onResponse: "+data.getResults());
            adapter.appendData(data.getResults());
            mPB.setVisibility(View.GONE);
        }

        @Override
        public void onFailure(Call<ResultWrapper> call, Throwable t) {
            mPB.setVisibility(View.GONE);
        }
    };


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("data",  new ArrayList<>(adapter.getData()));
        outState.putBoolean("isPopular", isPopular);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Test", "onDestroy: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Test", "onStop: ");
    }

    @Override
    public void createList(boolean isPopular) {
        this.isPopular = !isPopular;
        if(isPopular) {
            RetrofitServices.getMovieService().popularMovie().enqueue(firstCallBack);
        } else {
            RetrofitServices.getMovieService().topMovie().enqueue(firstCallBack);
        }
    }
}
