package com.example.getmeabeer;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.getmeabeer.adapter.BeersAdapter;
import com.example.getmeabeer.model.beer.BeerDbEntry;
import com.example.getmeabeer.model.beer.DatumBeer;
import com.example.getmeabeer.network.BeerApi;
import com.example.getmeabeer.network.DatabaseHelper;
import com.example.getmeabeer.network.FavBeers;
import com.example.getmeabeer.roomDatabase.BeerRoomDatabase;
import com.example.getmeabeer.roomDatabase.BeerViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.getmeabeer.R2.id.container;

public class MainFragment extends Fragment  {

    private static final String TAG = MainFragment.class.getSimpleName();
    private BeerApi beerApi = new BeerApi();
    Context context;
    BeersAdapter beersAdapter;
    @BindView(R.id.beers_recyclerview)
    RecyclerView beersRecyclerView;
    private boolean inDatabase = false;
    private String beerId;
    private String beerName;
    private String beerAbv;
    private ArrayList<DatumBeer> beerArrayList = null;
    BeerRoomDatabase beerDb;
    private int inDb = 0;
    TextView favText;
    MainActivity main;
    List<BeerDbEntry> favBrs = null;
    BeerDbEntry beerData = null;

    private static final String FAVORITE_BEERS = "favBeers";


    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);
        beerDb = BeerRoomDatabase.getDatabase(context);
        retrieveFavBeers();

        //getAllBeers();

        favText = rootView.findViewById(R.id.fav_Text);

        main = (MainActivity) getActivity();
        beerArrayList = main.getBeerArrayList();
        favBrs = main.favBeers;


       // BeersAdapter beersAdapter = new BeersAdapter(context, beerArrayList, favBrs);
        BeersAdapter beersAdapter = new BeersAdapter(context);
        beersRecyclerView.setAdapter(beersAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        beersRecyclerView.setLayoutManager(layoutManager);


        if(beerArrayList != null && !beerArrayList.isEmpty()) {
            setAdapter(beerArrayList, favBrs);
            //retrieveFavBeers();

        } else {
            Log.d(TAG, "MainFrag - BeerArrayList is EMPTY");
        }

        //getPreferences();
//        final Observer<List<BeerDbEntry>> beerFavs = new Observer<List<BeerDbEntry>>() {
//            @Override
//            public void onChanged(@Nullable List<BeerDbEntry> beerDbEntries) {
//                favBrs = beerDb.beerDao().getAllBeer();
//            }
//        };

        Log.d(TAG, "onCreateView: favBeers: " + favBrs);



        return rootView;
    }

    private void setAdapter(ArrayList beers, List<BeerDbEntry> favBrs) {
        beersAdapter = new BeersAdapter(context, beers, favBrs);
        beersRecyclerView.setAdapter(beersAdapter);
    }

//    private void getAllBeers() {
//        List<BeerDbEntry> beer = beerDb.beerDao().getAllBeer();
//        Log.d(TAG, "getAllBeers: size " + beer.size());
//        for(BeerDbEntry b: beer) {
//            Log.d(TAG, "getAllBeers: id: " + b.getId());
//        }
//    }


    //TODO: Need to work on getting the favorite button to be yellow when beer is in db
    public void retrieveFavBeers() {
        BeerViewModel viewModel = ViewModelProviders.of(this).get(BeerViewModel.class);
        // removeAllstuff from adapter
        viewModel.getAllBeer().observe(this, new Observer<List<BeerDbEntry>>() {
            @Override
            public void onChanged(@Nullable List<BeerDbEntry> beers) {
                Log.d(TAG, "Retrieving favorite Beers from ViewModel");
                if(beers != null) {
                    favBrs = new ArrayList<>();
                    for(BeerDbEntry b : beers) {
                        beerData = new BeerDbEntry();
                        Log.d(TAG, "onChanged in VM - id: " + b.getId());
                        //beerId = b.getId();
                        beerData.setId(b.getId());
                        Log.d(TAG, "onChanged in VM - name: " + b.getName());
                        //beerName = b.getName();
                        beerData.setName(b.getName());
                        Log.d(TAG, "onChanged in VM - abv: " + b.getAbv());
                        //beerAbv = b.getAbv();
                        beerData.setAbv(b.getAbv());
                        Log.d(TAG, "onChanged: in VM - INDB: " + b.getInDb());
                        //inDb = b.getInDb();
                        beerData.setInDb(b.getInDb());
                        favBrs.add(beerData);
                    }
                    Log.d(TAG, "onChanged: favBeers size: " + favBrs.size());
                    beersAdapter.notifyDataSetChanged();
                }
            }
        });
    }




}
