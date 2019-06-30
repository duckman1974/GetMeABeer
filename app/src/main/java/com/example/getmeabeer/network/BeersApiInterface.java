package com.example.getmeabeer.network;

import com.example.getmeabeer.model.beer.DatumBeer;

import java.util.List;

public interface BeersApiInterface {
    void passBeerJsonResults(List<DatumBeer> data);
}
