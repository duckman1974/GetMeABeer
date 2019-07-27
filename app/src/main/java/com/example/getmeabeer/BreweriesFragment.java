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
import com.example.getmeabeer.adapter.BreweriesAdapter;
import com.example.getmeabeer.model.beer.DatumBeer;
import com.example.getmeabeer.model.breweries.DatumBreweries;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.constraint.Constraints.TAG;

public class BreweriesFragment extends Fragment {

    Context context;
    BreweriesAdapter breweriesAdapter;
    @BindView(R.id.breweries_recyclerview)
    RecyclerView breweriesRecyclerView;
    private ArrayList<DatumBreweries> breweriesArrayList = null;

    public BreweriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getContext();

        View rootView = inflater.inflate(R.layout.fragment_breweries, container, false);
        ButterKnife.bind(this, rootView);

        MainActivity main = (MainActivity) getActivity();
        breweriesArrayList = main.getBreweriesArrayList();

        BreweriesAdapter breweriesAdapterAdapter = new BreweriesAdapter(context, breweriesArrayList);
        breweriesRecyclerView.setAdapter(breweriesAdapterAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        breweriesRecyclerView.setLayoutManager(layoutManager);

        if(breweriesArrayList != null && !breweriesArrayList.isEmpty()) {
            setAdapter(breweriesArrayList);
        } else {
            Log.d(TAG, "MainFrag - BeerArrayList is EMPTY");
        }
        return rootView;
    }

    private void setAdapter(ArrayList breweries) {
        breweriesAdapter = new BreweriesAdapter(context, breweries);
        breweriesRecyclerView.setAdapter(breweriesAdapter);
    }
}
