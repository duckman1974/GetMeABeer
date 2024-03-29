package com.example.getmeabeer.roomDatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.getmeabeer.model.beer.BeerDbEntry;
import com.example.getmeabeer.model.beer.DatumBeer;

import java.util.List;

@Dao
public interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BeerDbEntry beerModel);

    @Delete
    void delete(BeerDbEntry beerModel);

    @Query("SELECT * FROM BEER_TABLE ORDER BY id ASC")
    LiveData<List<BeerDbEntry>> getAllBeers();

    @Query("SELECT * FROM BEER_TABLE ORDER BY id ASC")
    List<BeerDbEntry> getAllBeer();

}
