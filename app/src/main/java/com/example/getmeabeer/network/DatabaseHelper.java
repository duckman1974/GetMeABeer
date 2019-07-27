package com.example.getmeabeer.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.getmeabeer.model.beer.BeerDbEntry;
import com.example.getmeabeer.roomDatabase.BeerRoomDatabase;

import java.util.List;

public class DatabaseHelper extends AsyncTask<Void, Void, List<BeerDbEntry>> {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private List<BeerDbEntry> favoriteBeers;
    public FavBeers delegate = null;
    BeerRoomDatabase beerDb;
    Context context;

    public DatabaseHelper(){
    }

    @Override
    protected List<BeerDbEntry> doInBackground(Void... voids) {
        beerDb = BeerRoomDatabase.getDatabase(context);
        favoriteBeers = beerDb.beerDao().getAllBeer();
        Log.d(TAG, "getAllBeers: size " + favoriteBeers.size());
        for(BeerDbEntry b: favoriteBeers) {
            Log.d(TAG, "getAllBeers: id: " + b.getId());
            Log.d(TAG, "doInBackground: name: " + b.getName());
        }
        return favoriteBeers;
    }

    @Override
    protected void onPostExecute(List<BeerDbEntry> favBeers) {
        delegate.getFavBeers(favBeers);
    }
}
