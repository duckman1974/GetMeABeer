package com.example.getmeabeer.network;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.example.getmeabeer.constants.Constants;
import com.example.getmeabeer.model.hops.DatumHops;
import com.example.getmeabeer.utility.JsonUtility;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HopsApi extends AsyncTask<String, Void, List<DatumHops>> {

    private static final String TAG = HopsApi.class.getSimpleName();

    public HopsApiInterface delegate = null;
    Context context;
    private String hopsData;
    private List<DatumHops> jsonData = null;

    public HopsApi() {
    }


    @Override
    protected List<DatumHops> doInBackground(String... params) {
        JsonUtility jsonUtil = new JsonUtility();
        String typeOfDate = "";
        for(int i = 0; i < params.length; i++) {
            typeOfDate = params[i];
        }
        Log.d(TAG, "DoInBackground typeOfDate: " + typeOfDate);
        hopsData = getJsonData(typeOfDate);
        jsonData = jsonUtil.makeHopsJsonObject(hopsData);
        return jsonData;
    }


    private String getJsonData(String dataType) {
        HttpURLConnection urlConnection;
        BufferedReader reader;

        final String KEY = "key";

        try {
            Uri builtUri = Uri.parse(Constants.URL + dataType).buildUpon()
                    .appendQueryParameter(KEY, Constants.API_KEY)
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

            hopsData = buffer.toString();
            return hopsData;

        } catch(Exception e) {
            Log.d(TAG, "Exception retrieving jsonData: ", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<DatumHops> hopsObject) {
        delegate.passHopsJsonResults(hopsObject);
    }
}
