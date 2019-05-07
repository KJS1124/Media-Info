package com.example.mediainfo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediainfo.adapters.ListItemCardAdarpter;
import com.example.mediainfo.models.CardDetails;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TVSeriesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TVSeriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TVSeriesFragment extends Fragment implements ListItemCardAdarpter.ItemCardListner{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TVSeriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TVSeriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TVSeriesFragment newInstance(String param1, String param2) {
        TVSeriesFragment fragment = new TVSeriesFragment();
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
        View view = inflater.inflate(R.layout.fragment_tvseries, container, false);
        final RecyclerView mRecyclerView = view.findViewById(R.id.rv_tvseries);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        final ListItemCardAdarpter adapter = new ListItemCardAdarpter(this);
        mRecyclerView.setAdapter(adapter);
        List<CardDetails> details = new ArrayList<>();
        details.add(new CardDetails("hello2","Second","https://image.shutterstock.com/image-vector/two-cinema-vector-tickets-isolated-450w-739876768.jpg","12/12/2019"));

        details.add(new CardDetails("hello2","Second","https://image.shutterstock.com/image-vector/two-cinema-vector-tickets-isolated-450w-739876768.jpg","12/12/2019"));

        details.add(new CardDetails("hello2","Second","https://image.shutterstock.com/image-vector/two-cinema-vector-tickets-isolated-450w-739876768.jpg","12/12/2019"));

        details.add(new CardDetails("hello2","Second","https://image.shutterstock.com/image-vector/two-cinema-vector-tickets-isolated-450w-739876768.jpg","12/12/2019"));

        adapter.setData(details);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(id);
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
        this.mListener.onFragmentInteraction(cardDetails.getId());
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
        void onFragmentInteraction(String id);
    }
}
