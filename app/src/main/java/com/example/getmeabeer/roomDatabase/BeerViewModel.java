package com.example.getmeabeer.roomDatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.getmeabeer.model.beer.BeerDbEntry;

import java.util.List;

public class BeerViewModel extends AndroidViewModel {

    private static final String TAG = BeerViewModel.class.getSimpleName();

    private BeerDao beerDao;
    private LiveData<List<BeerDbEntry>> allBeer;

    public BeerViewModel (Application application) {
        super(application);
        BeerRoomDatabase db = BeerRoomDatabase.getDatabase(application);
        Log.d(TAG, "Retrieving BEERS from the Database.");
        beerDao = db.beerDao();
        allBeer = beerDao.getAllBeers();
    }

    public LiveData<List<BeerDbEntry>> getAllBeer() {
        return allBeer;
    }
}
