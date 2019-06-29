package com.example.getmeabeer.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

public class NetworkConnectivity extends AsyncTask<Void, Void, Boolean> {

    private static final String TAG = NetworkConnectivity.class.toString();
    public NetworkAsyncCheck delegate = null;
    Context context;

    public NetworkConnectivity(Context context) {

        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean connected;
        try {
            ConnectivityManager connMgr =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            connected = networkInfo.isConnected();
            if(connected == true){
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean isConnected) {
        delegate.processFinish(isConnected);
    }
}
