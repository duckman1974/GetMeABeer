package com.example.getmeabeer.network;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.getmeabeer.constants.Constants;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.example.getmeabeer.model.Datum;
import com.example.getmeabeer.utility.JsonUtility;

public class BeerApi extends AsyncTask<String, Void, List<Datum>> {

    private static final String TAG = BeerApi.class.getSimpleName();

    public BeersApiInterface delegate = null;
    Context context;
    private String beerData;
    private List<Datum> jsonData;

    public BeerApi() {
    }


    @Override
    protected List<Datum> doInBackground(String... params) {
        JsonUtility jsonUtil = new JsonUtility();
        String typeOfDate = "";
        for(int i = 0; i < params.length; i++) {
            typeOfDate = params[i];
        }
        Log.d(TAG, "DoInBackground typeOfDate: " + typeOfDate);
        beerData = getJsonData(typeOfDate);
        jsonData = jsonUtil.makeJsonObject(beerData);


        return jsonData;
    }


    private String getJsonData(String dataType) {
        HttpURLConnection urlConnection;
        BufferedReader reader;

        final String KEY = "key";

        try {
            Uri builtUri = Uri.parse(Constants.URL + dataType).buildUpon()
                    .appendQueryParameter(KEY, Constants.API_KEY)
                    //.appendQueryParameter(Constants.ORDER, Constants.RANDOM)
                   // .appendQueryParameter(Constants.HASLABEL, Constants.LABELS_YES)
                    .build();

            URL url = new URL(builtUri.toString());
            Log.d(TAG, "URL passed in: " + url);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) return null;

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String data;
            while ((data = reader.readLine()) != null) {
                buffer.append(data).append("\n");
            }
            if (buffer.length() == 0) return null;

            beerData = buffer.toString();
            return beerData;

        } catch(Exception e) {
            Log.d(TAG, "Exception retrieving jsonData: ", e);
        }
        return null;
    }


    @Override
    protected void onPostExecute(List<Datum> beerObject) {

        delegate.passJsonResults(beerObject);
    }
}
