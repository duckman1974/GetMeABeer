package com.example.getmeabeer.utility;

import android.util.Log;

import com.example.getmeabeer.model.Datum;
import com.example.getmeabeer.model.JsonResponseData;
import com.example.getmeabeer.model.Labels;
import com.example.getmeabeer.model.Style;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JsonUtility {

    private static final String TAG = JsonUtility.class.getSimpleName();
    private List<Datum> beerList;
    private Datum beer;
    private Labels beerLabel;
    private Style beerStyle;


    public List<Datum> makeJsonObject(String beerData) {
        JsonResponseData gsonObj = new Gson().fromJson(beerData, JsonResponseData.class);
        List<Datum> datumData = gsonObj.getData();
        //int numberOfBeers = 0;
        beerList = new ArrayList();
        for(Datum dd : datumData) {
            beer = new Datum();
            beerLabel = new Labels();
            beerStyle = new Style();

            if(dd.getLabels() != null && dd.getStyle() != null) {
                Log.d(TAG, "Parsing beer label: " + dd.getLabels().getLarge());
                beerLabel.setLarge(dd.getLabels().getLarge());
                Log.d(TAG, "Parsing beer style: " + dd.getStyle().getShortName());
                beerStyle.setShortName(dd.getStyle().getShortName());
                Log.d(TAG, "Parsing beer name: " + dd.getName());
                beer.setName(dd.getName());
                Log.d(TAG, "Parsing beer description: " + dd.getDescription());
                beer.setDescription(dd.getDescription());
                beer.setLabels(beerLabel);
                beer.setStyle(beerStyle);

                //numberOfBeers += 1;
                beerList.add(beer);
            }

        }

        Log.d(TAG, "New beer list size: " + beerList.size());


        //Log.d(TAG, "Number of beers: " + numberOfBeers);

        return beerList;
    }
}
