package com.example.getmeabeer.utility;

import android.util.Log;

import com.example.getmeabeer.model.beer.DatumBeer;
import com.example.getmeabeer.model.beer.JsonResponseDataBeer;
import com.example.getmeabeer.model.beer.Labels;
import com.example.getmeabeer.model.beer.Style;
import com.example.getmeabeer.model.breweries.DatumBreweries;
import com.example.getmeabeer.model.breweries.Images;
import com.example.getmeabeer.model.breweries.JsonResponseDataBreweries;
import com.example.getmeabeer.model.hops.Country;
import com.example.getmeabeer.model.hops.DatumHops;
import com.example.getmeabeer.model.hops.JsonResponseDataHops;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JsonUtility {

    private static final String TAG = JsonUtility.class.getSimpleName();
    private List<DatumBeer> beerList;
    private List<DatumHops> hopsList;
    private List<DatumBreweries> breweriesList;
    private DatumBeer beer;
    private DatumHops hops;
    private DatumBreweries breweries;
    private Images breweryImages;
    private Country hopsCountry;
    private Labels beerLabel;
    private Style beerStyle;


    public List<DatumBeer> makeBeerJsonObject(String beerData) {
        JsonResponseDataBeer gsonObj = new Gson().fromJson(beerData, JsonResponseDataBeer.class);
        List<DatumBeer> datumData = gsonObj.getData();
        beerList = new ArrayList();
        for(DatumBeer dd : datumData) {
            beer = new DatumBeer();
            beerLabel = new Labels();
            beerStyle = new Style();

            if(dd.getLabels() != null && dd.getStyle() != null) {
                Log.d(TAG, "Parsing beer ID: " + dd.getId());
                beer.setId(dd.getId());
                Log.d(TAG, "Parsing beer label: " + dd.getLabels().getLarge());
                beerLabel.setLarge(dd.getLabels().getLarge());
                Log.d(TAG, "Parsing beer style: " + dd.getStyle().getShortName());
                beerStyle.setShortName(dd.getStyle().getShortName());
                Log.d(TAG, "Parsing beer name: " + dd.getName());
                beer.setName(dd.getName());
                Log.d(TAG, "Parsing beer description: " + dd.getDescription());
                beer.setDescription(dd.getDescription());
                Log.d(TAG, "Parsing beer abv: " + dd.getAbv());
                beer.setAbv(dd.getAbv());
                beer.setLabels(beerLabel);
                beer.setStyle(beerStyle);
                beerList.add(beer);
            }
        }
        Log.d(TAG, "New beer list size: " + beerList.size());
        return beerList;
    }

    public List<DatumHops> makeHopsJsonObject(String hopsData) {
        JsonResponseDataHops gsonObj = new Gson().fromJson(hopsData, JsonResponseDataHops.class);
        List<DatumHops> datumData = gsonObj.getData();
        hopsList = new ArrayList();
        for(DatumHops h : datumData) {
            hops = new DatumHops();
            hopsCountry = new Country();
            if(h.getCountry() != null) {
                Log.d(TAG, "Parsing Hops names: " + h.getName());
                hops.setName(h.getName());
                Log.d(TAG, "Parsing Hops description: " + h.getDescription());
                hops.setDescription(h.getDescription());
                Log.d(TAG, "Parsing Hops Country of Origin: " + h.getCountry().getName());
                hopsCountry.setName(h.getCountry().getName());
                hops.setCountry(hopsCountry);
                Log.d(TAG, "Parsing Hops Alpha acid Min: " + h.getAlphaAcidMin());
                hops.setAlphaAcidMin(h.getAlphaAcidMin());
                Log.d(TAG, "Parsing Hops creation date: " + h.getCreateDate());
                hops.setCreateDate(h.getCreateDate());
                hopsList.add(hops);
            }
        }
        Log.d(TAG, "Hops list size: " + hopsList.size());
        return hopsList;
    }

    public List<DatumBreweries> makeBreweriesJsonObject(String breweriesData) {
        JsonResponseDataBreweries gsonObj = new Gson().fromJson(breweriesData, JsonResponseDataBreweries.class);
        List<DatumBreweries> datumData = gsonObj.getData();
        breweriesList = new ArrayList();
        for(DatumBreweries b : datumData) {
            breweryImages = new Images();
            breweries = new DatumBreweries();
            if(b.getImages() != null) {
                Log.d(TAG, "Brewery name: " + b.getName());
                breweries.setName(b.getName());
                Log.d(TAG, "Brewery established: " + b.getEstablished());
                breweries.setEstablished(b.getEstablished());
                Log.d(TAG, "Brewery website: " + b.getWebsite());
                breweries.setWebsite(b.getWebsite());
                Log.d(TAG, "Brewery image: " + b.getImages().getSquareMedium());
                breweryImages.setSquareMedium(b.getImages().getSquareMedium());
                breweries.setImages(breweryImages);
                breweriesList.add(breweries);
            }
        }
        Log.d(TAG, "Brewery list size: " + breweriesList.size());
        return breweriesList;

    }
}
