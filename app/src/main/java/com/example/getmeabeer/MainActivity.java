package com.example.getmeabeer;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.getmeabeer.network.FavBeers;

import com.example.getmeabeer.adapter.BeersAdapter;
import com.example.getmeabeer.model.beer.BeerDbEntry;
import com.example.getmeabeer.model.beer.DatumBeer;
import com.example.getmeabeer.model.breweries.DatumBreweries;
import com.example.getmeabeer.model.hops.DatumHops;
import com.example.getmeabeer.network.BeerApi;
import com.example.getmeabeer.network.BeersApiInterface;
import com.example.getmeabeer.network.BreweriesApi;
import com.example.getmeabeer.network.BreweriesApiInterface;
import com.example.getmeabeer.network.DatabaseHelper;
import com.example.getmeabeer.network.HopsApi;
import com.example.getmeabeer.network.HopsApiInterface;
import com.example.getmeabeer.network.NetworkAsyncCheck;
import com.example.getmeabeer.network.NetworkConnectivity;
import com.example.getmeabeer.roomDatabase.BeerRoomDatabase;
import com.example.getmeabeer.roomDatabase.BeerViewModel;
import com.example.getmeabeer.widget.UpdateBeerWidgetIntentService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements NetworkAsyncCheck, BeersApiInterface, HopsApiInterface, BreweriesApiInterface, BeersAdapter.BeerSelectedListener, FavBeers{

    private static final String TAG = MainActivity.class.toString();
    NetworkConnectivity netCheck;
    @BindView (R.id.coordinator_layout_beer_list)
    CoordinatorLayout mCoordLayout;
    @BindView(R.id.favorite_button)
    ImageButton favbutton;
    private BeerApi beerApi = new BeerApi();
    private HopsApi hopsApi = new HopsApi();
    private BreweriesApi breweriesApi = new BreweriesApi();
    ArrayList<DatumBeer> beerArrayList = null;
    ArrayList<DatumHops> hopsArrayList = null;
    ArrayList<DatumBreweries> breweriesArrayList = null;
    public static final String BUNDLE_BEER_ARRAY_KEY = "myBeers";
    public static final String BUNDLE_HOPS_ARRAY_KEY = "myHops";
    public static final String BUNDLE_BREWERIES_ARRAY_KEY = "myBreweries";

    private static final String BEERS ="beers";
    private static final String HOPS = "hops";
    private static final String BREWERIES = "breweries";
    private final Integer BEERFRAGMENT = 1;
    private final Integer HOPSFRAGMENT = 2;
    private final Integer BREWERIESFRAGMENT = 3;

    private BeerRoomDatabase beerDb;
    private boolean inDatabase = false;
    private String beerId;
    private String beerName;
    private String beerAbv;
    List<BeerDbEntry> favBeers;
    DatabaseHelper dbH = new DatabaseHelper();

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
                        clearArrayList();
                        getBeers(BEERS);
                        replaceMainFragment(BEERFRAGMENT);
//                        retrieveFavBeers();
                        return true;
                    case R.id.hops:
                        Log.d(TAG, "Selected HOPS");
                        clearArrayList();
                        getHops(HOPS);
                        return true;
                    case R.id.breweries:
                        Log.d(TAG, "Selected BREWERIES");
                        clearArrayList();
                        getBreweries(BREWERIES);
                        return true;
                    default:
                        return false;
                }
            }
        });

        if(savedInstanceState != null) {
            if(savedInstanceState.containsKey(BUNDLE_BEER_ARRAY_KEY)) {
                Log.d(TAG, "onCreate: getting onsavedStateBundle for beer");
                beerArrayList = savedInstanceState.getParcelableArrayList(BUNDLE_BEER_ARRAY_KEY);
                replaceMainFragment(BEERFRAGMENT);
            } else if(savedInstanceState.containsKey(BUNDLE_HOPS_ARRAY_KEY)) {
                Log.d(TAG, "onCreate: getting onsavedStateBundle for hops");
                hopsArrayList = savedInstanceState.getParcelableArrayList(BUNDLE_HOPS_ARRAY_KEY);
                replaceMainFragment(HOPSFRAGMENT);
            } else if(savedInstanceState.containsKey(BUNDLE_BREWERIES_ARRAY_KEY)) {
                breweriesArrayList = savedInstanceState.getParcelableArrayList(BUNDLE_BREWERIES_ARRAY_KEY);
                replaceMainFragment(BREWERIESFRAGMENT);
            } else {
                checkNetworkConnection();
            }
        } else {
            Log.d(TAG, "onCreate: Getting new beer list");
            checkNetworkConnection();
        }

        dbH.delegate = this;
        dbH.execute();

        beerDb = BeerRoomDatabase.getDatabase(getApplicationContext());

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

    private void getBreweries(String dataType) {
        breweriesApi = new BreweriesApi();
        breweriesApi.delegate = this;
        breweriesApi.execute(dataType);
    }

    protected ArrayList<DatumBeer> getBeerArrayList() {

        return beerArrayList;
    }

    protected ArrayList<DatumHops> getHopsArrayList() {
        return hopsArrayList;
    }

    protected ArrayList<DatumBreweries> getBreweriesArrayList() {
        return breweriesArrayList;
    }

    private void clearArrayList() {
        if(hopsArrayList != null) hopsArrayList.clear();
        if(breweriesArrayList != null) breweriesArrayList.clear();
        if(beerArrayList != null) beerArrayList.clear();
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

    public void passBreweriesJsonResults(List<DatumBreweries> breweriesObject) {
        int listSize = breweriesObject.size();
        Log.d(TAG, "MainActivity - Size of breweriesList: " + listSize);
        ArrayList<DatumBreweries> datumArrayList = new ArrayList<>(listSize);
        datumArrayList.addAll(breweriesObject);
        breweriesArrayList = datumArrayList;
        replaceMainFragment(BREWERIESFRAGMENT);
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
        } else if(fragType == 3) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.coordinator_layout_beer_list, new BreweriesFragment(), "BreweriesFragment");
            ft.commit();
        } else {
            Log.e(TAG, "Error_replaceMainFragment: Could not find a valid Fragment");
        }
    }

    // Network check results
    public void processFinish(Boolean result){
        String check;
        if(result == true) {
            check = "UP";
            Log.d(TAG, "Network is: " + check);
            getBeers(BEERS);

            // This calls an Async Task to retrieve all favorite beers from db
            dbH = dbH = new DatabaseHelper();
            dbH.delegate = this;
            dbH.execute();
        } else {
            check = "DOWN";
            Log.d(TAG, "Network is: " + check);
            Toast.makeText(this, "Please make sure you have Network Connection to find the best Beers.", Toast.LENGTH_LONG).show();
        }
    }

    private void getPreferences() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Bundle bundle = new Bundle();
        String ingredientListString = pref.getString("ingredient_list_string", null);


    }


    @Override
    public void beerSelectedForWidget(int position) {
//        recipeForWidget = RecipeMainFragment.jd.get(position);
//        Log.d(TAG, "recipeSelectedForWidget id : " + recipeForWidget.getId());
//        Log.d(TAG, "recipeSelectedForWidget name : " + recipeForWidget.getName());
//        ingredList = recipeForWidget.getIngredients();
//        String ingListString = getIngredientList(recipeForWidget.getIngredients());

        //save the ingredientListString in SharePreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("beer_name", beerName);
        editor.putString("beer_abv", beerAbv);
        Log.d(TAG, "beer selected for widget");
        editor.apply();

        UpdateBeerWidgetIntentService.startActionUpdateRecipeWidget(this, beerName);
    }







    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<DatumBeer> beerArrayList = getBeerArrayList();
        ArrayList<DatumHops> hopsArrayList = getHopsArrayList();
        ArrayList<DatumBreweries> breweriesArrayList = getBreweriesArrayList();
        if(beerArrayList != null && !beerArrayList.isEmpty()) {
            outState.putParcelableArrayList(BUNDLE_BEER_ARRAY_KEY, beerArrayList);
            Log.d(TAG, "onSaveInstanceState: saving beerArrayList");
        } else if (hopsArrayList != null && !hopsArrayList.isEmpty()) {
            outState.putParcelableArrayList(BUNDLE_HOPS_ARRAY_KEY, hopsArrayList);
            Log.d(TAG, "onSaveInstanceState: saving hopsArrayList");
        } else if (breweriesArrayList != null && !breweriesArrayList.isEmpty()) {
            outState.putParcelableArrayList(BUNDLE_BREWERIES_ARRAY_KEY, breweriesArrayList);
            Log.d(TAG, "onSaveInstanceState: saving breweriesArrayList");
        }
    }

    @Override
    public void getFavBeers(List<BeerDbEntry> favoriteBeers) {
        if(favoriteBeers == null) {
            Log.d(TAG,"Favorite Beers DB is empty");
        } else {
            favBeers = favoriteBeers;
        }
    }


//    private void retrieveFavBeers() {
//        BeerViewModel viewModel = ViewModelProviders.of(this).get(BeerViewModel.class);
//        // removeAllstuff from adapter
//        viewModel.getAllBeer().observe(this, new Observer<List<BeerDbEntry>>() {
//            @Override
//            public void onChanged(@Nullable List<BeerDbEntry> beers) {
//                Log.d(TAG, "Retrieving favorite Beers from ViewModel");
//                if(beers != null) {
//                   // favBeers = null;
//                    for(BeerDbEntry b : beers) {
//                        BeerDbEntry be = new BeerDbEntry();
//                        Log.d(TAG, "onChanged in VM - id: " + b.getId());
//                        be.setId(b.getId());
//                        Log.d(TAG, "onChanged in VM - name: " + b.getName());
//                        be.setName(b.getName());
//                        Log.d(TAG, "onChanged in VM - abv: " + b.getAbv());
//                        be.setAbv(b.getAbv());
//                        Log.d(TAG, "onChanged: in VM - INDB: " + b.getInDb());
//                        be.setInDb(b.getInDb());
//                        favBeers.add(be);
//                    }
//
//                }
//            }
//        });
//        //Log.d(TAG, "RetrieveFavBeer size: " + favBeers.size());
//    }
}
