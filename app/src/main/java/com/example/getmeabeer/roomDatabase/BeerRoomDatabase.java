package com.example.getmeabeer.roomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.example.getmeabeer.model.beer.BeerDbEntry;

@Database(entities = {BeerDbEntry.class}, version = 1)
public abstract class BeerRoomDatabase extends RoomDatabase {

    private static final String TAG = BeerRoomDatabase.class.toString();

    public abstract BeerDao beerDao();

    private static volatile BeerRoomDatabase INSTANCE;

    public static BeerRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (BeerRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        BeerRoomDatabase.class, "beer_database.db")
                            .build();
                    Log.d(TAG, "Built Beer DB");
                }
            }
        }
        return INSTANCE;
    }
}
