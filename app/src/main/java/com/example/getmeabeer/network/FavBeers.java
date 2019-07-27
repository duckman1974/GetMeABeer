package com.example.getmeabeer.network;

import com.example.getmeabeer.model.beer.BeerDbEntry;

import java.util.List;

public interface FavBeers {
    void getFavBeers(List<BeerDbEntry> favBeers);
}
