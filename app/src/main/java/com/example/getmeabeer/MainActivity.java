package com.example.getmeabeer;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.getmeabeer.model.beer.DatumBeer;
import com.example.getmeabeer.model.hops.DatumHops;
import com.example.getmeabeer.network.BeerApi;
import com.example.getmeabeer.network.BeersApiInterface;
import com.example.getmeabeer.network.HopsApi;
import com.example.getmeabeer.network.HopsApiInterface;
import com.example.getmeabeer.network.NetworkAsyncCheck;
import com.example.getmeabeer.network.NetworkConnectivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements NetworkAsyncCheck, BeersApiInterface, HopsApiInterface {

    private static final String TAG = MainActivity.class.toString();
    NetworkConnectivity netCheck;
    @BindView (R.id.coordinator_layout_beer_list)
    CoordinatorLayout mCoordLayout;
//    @BindView(R.id.toolbar_container)
//    Toolbar toolbar;
    private BeerApi beerApi = new BeerApi();
    private HopsApi hopsApi = new HopsApi();
    ArrayList<DatumBeer> beerArrayList = null;
    ArrayList<DatumHops> hopsArrayList = null;
    public static final String BUNDLE_BEER_ARRAY_KEY = "myBeers";
    private static final String BEERS ="beers";
    private static final String HOPS = "hops";
    private final Integer BEERFRAGMENT = 1;
    private final Integer HOPSFRAGMENT = 2;
    private final Integer BREWERIESFRAGMENT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate Main: ");
        //checkNetworkConnection();

        Toolbar toolbar = findViewById(R.id.toolbar_container);

        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.beers:
                        Log.d(TAG, "Selected BEERS");
                        beerArrayList.clear();
                        getBeers(BEERS);
                        replaceMainFragment(BEERFRAGMENT);
                        return true;
                    case R.id.hops:
                        Log.d(TAG, "Selected HOPS");
                        beerArrayList.clear();
                        if(hopsArrayList != null) hopsArrayList.clear();
                        getHops(HOPS);
                        return true;
                    case R.id.brewery_events:
                        Log.d(TAG, "Selected BREWERY EVENTS");
                        return false;
                    default:
                        return false;
                }
            }
        });

        if(savedInstanceState != null) {
            if(savedInstanceState.containsKey(BUNDLE_BEER_ARRAY_KEY)) {
                Log.d(TAG, "onCreate: getting onsavedStateBundle");
                beerArrayList = savedInstanceState.getParcelableArrayList(BUNDLE_BEER_ARRAY_KEY);
                replaceMainFragment(BEERFRAGMENT); //TODO: will need to refactor this in case something other than beer is displayed
            }
        } else {
            Log.d(TAG, "onCreate: Getting new beer list");
            checkNetworkConnection();
        }
    }

    // Makes Async network check request
    private void checkNetworkConnection() {
        Log.d(TAG, "Main Activity and checking network connection");
        netCheck = new NetworkConnectivity(getApplicationContext());
        netCheck.delegate = this;
        netCheck.execute();
    }

    private void getBeers(String dataType) {
        beerApi = new BeerApi();
        beerApi.delegate = this;
        beerApi.execute(dataType);
    }

    private void getHops(String dataType) {
        hopsApi = new HopsApi();
        hopsApi.delegate = this;
        hopsApi.execute(dataType);
    }

    //TODO: build getBreweries()


    protected ArrayList<DatumBeer> getBeerArrayList() {
        return beerArrayList;
    }

    protected ArrayList<DatumHops> getHopsArrayList() {
        return hopsArrayList;
    }

    public void passBeerJsonResults(List<DatumBeer> beerObject) {
        int listSize = beerObject.size();
        Log.d(TAG, "MainActivity - Size of beerList: " + listSize);
        ArrayList<DatumBeer> datumArrayList = new ArrayList<>(listSize);
        datumArrayList.addAll(beerObject);

        beerArrayList = datumArrayList;
        callMainFragment();
    }

    public void passHopsJsonResults(List<DatumHops> hopsObject) {
        int listSize = hopsObject.size();
        Log.d(TAG, "MainActivity - Size of hopsList: " + listSize);
        ArrayList<DatumHops> datumArrayList = new ArrayList<>(listSize);
        datumArrayList.addAll(hopsObject);
        hopsArrayList = datumArrayList;
        replaceMainFragment(HOPSFRAGMENT);
    }

    private void callMainFragment() {
        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.coordinator_layout_beer_list, mainFragment)
                .commit();
    }

    private void replaceMainFragment(int fragType) {

        if(fragType == 1) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.coordinator_layout_beer_list, new MainFragment(), "MainFragment");
            ft.commit();
        } else if(fragType == 2) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.coordinator_layout_beer_list, new HopsFragment(), "HopsFragment");
            ft.commit();
        }
    }



    // Network check results
    public void processFinish(Boolean result){
        String check;
        if(result == true) {
            check = "UP";
            Log.d(TAG, "Network is: " + check);
            getBeers(BEERS);
        } else {
            check = "DOWN";
            Log.d(TAG, "Network is: " + check);
            Toast.makeText(this, "Please make sure you have Network Connection to find the best Beers.", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<DatumBeer> beerArrayList = getBeerArrayList();
        if(beerArrayList != null && !beerArrayList.isEmpty()) {  //TODO: Need to look at saving currently displayed menu selected.  currently set at BEER
            outState.putParcelableArrayList(BUNDLE_BEER_ARRAY_KEY, beerArrayList);
            Log.d(TAG, "onSaveInstanceState: saving beerArrayList");
        }
    }
}
