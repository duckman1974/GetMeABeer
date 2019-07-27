package com.example.getmeabeer.network;

import com.example.getmeabeer.model.breweries.DatumBreweries;

import java.util.List;

public interface BreweriesApiInterface {
    void passBreweriesJsonResults(List<DatumBreweries> data);
}
