package com.example.getmeabeer;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.getmeabeer.model.Datum;
import com.example.getmeabeer.network.BeerApi;
import com.example.getmeabeer.network.BeersApiInterface;
import com.example.getmeabeer.network.NetworkAsyncCheck;
import com.example.getmeabeer.network.NetworkConnectivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements NetworkAsyncCheck, BeersApiInterface {

    private static final String TAG = MainActivity.class.toString();
    NetworkConnectivity netCheck;
    @BindView (R.id.coordinator_layout_beer_list)
    CoordinatorLayout mCoordLayout;
//    @BindView(R.id.toolbar_container)
//    Toolbar toolbar;
    private BeerApi beerApi = new BeerApi();
    ArrayList<Datum> beerArrayList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate Main: ");
        checkNetworkConnection();

        Toolbar toolbar = findViewById(R.id.toolbar_container);

        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.beers:
                        Log.d(TAG, "Selected BEERS");
                        return false;
                    case R.id.hops:
                        Log.d(TAG, "Selected HOPS");
                        return false;
                    case R.id.brewery_events:
                        Log.d(TAG, "Selected BREWERY EVENTS");
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

    // Makes Async network check request
    private void checkNetworkConnection() {
        Log.d(TAG, "Main Activity and checking network connection");
        netCheck = new NetworkConnectivity(getApplicationContext());
        netCheck.delegate = this;
        netCheck.execute();
    }

    private void getBeers() {
        beerApi.delegate = this;
        beerApi.execute("beers");
    }

    protected ArrayList<Datum> getBeerArrayList() {
        return beerArrayList;
    }

    public void passJsonResults(List<Datum> beerObject) {
        int listSize = beerObject.size();
        Log.d(TAG, "MainActivity - Size of beerList: " + listSize);
        ArrayList<Datum> datumArrayList = new ArrayList<>(listSize);
        datumArrayList.addAll(beerObject);

        beerArrayList = datumArrayList;
        callMainFragment();
    }

    private void callMainFragment() {
        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.coordinator_layout_beer_list, mainFragment)
                .commit();
    }



    // Network check results
    public void processFinish(Boolean result){
        String check;
        if(result == true) {
            check = "UP";
            Log.d(TAG, "Network is: " + check);
            getBeers();
        } else {
            check = "DOWN";
            Log.d(TAG, "Network is: " + check);
            Toast.makeText(this, "Please make sure you have Network Connection to find the best Beers.", Toast.LENGTH_LONG).show();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()) {
//            case R.id.beers:
//                Log.d(TAG, "Selected BEERS");
//                return false;
//            case R.id.hops:
//                Log.d(TAG, "Selected HOPS");
//                return false;
//            case R.id.brewery_events:
//                Log.d(TAG, "Selected BREWERY EVENTS");
//                return false;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
