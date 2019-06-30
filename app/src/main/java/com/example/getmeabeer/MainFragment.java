package com.example.getmeabeer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.getmeabeer.adapter.BeersAdapter;
import com.example.getmeabeer.model.beer.DatumBeer;
import com.example.getmeabeer.network.BeerApi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment  {

    private static final String TAG = MainFragment.class.getSimpleName();
    private BeerApi beerApi = new BeerApi();
    Context context;
    BeersAdapter beersAdapter;
    @BindView(R.id.beers_recyclerview)
    RecyclerView beersRecyclerView;
    private ArrayList<DatumBeer> beerArrayList = null;


    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        MainActivity main = (MainActivity) getActivity();
        beerArrayList = main.getBeerArrayList();

        BeersAdapter beersAdapter = new BeersAdapter(context, beerArrayList);
        beersRecyclerView.setAdapter(beersAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        beersRecyclerView.setLayoutManager(layoutManager);

        if(beerArrayList != null && !beerArrayList.isEmpty()) {
            setAdapter(beerArrayList);
        } else {
            Log.d(TAG, "MainFrag - BeerArrayList is EMPTY");
        }
        return rootView;
    }

    private void setAdapter(ArrayList beers) {
        beersAdapter = new BeersAdapter(context, beers);
        beersRecyclerView.setAdapter(beersAdapter);
    }
}
