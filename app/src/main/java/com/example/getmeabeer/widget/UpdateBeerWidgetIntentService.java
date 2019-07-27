package com.example.getmeabeer.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.getmeabeer.R;

public class UpdateBeerWidgetIntentService extends IntentService {

    private static final String TAG = UpdateBeerWidgetIntentService.class.getSimpleName();
    public static final String ACTION_UPDATE_BEER_WIDGET = "com.example.getmeabeer";



    public UpdateBeerWidgetIntentService() {
        super("UpdateBeerWidgetIntentService");
    }

    public static void startActionUpdateRecipeWidget(Context context, String beerName) {
        // Log.d(TAG, "IN startActionUpdateRecipeWidget: " + recipeSelected.getId());
        // Log.d(TAG, "IN startActionUpdateRecipeWidget: " + recipeSelected.getName());
        Intent intent = new Intent(context, UpdateBeerWidgetIntentService.class);
        intent.setAction(ACTION_UPDATE_BEER_WIDGET);
        intent.putExtra("selectedBeer", beerName);
        Log.d(TAG, "STARTING Service for intent startActionUpdateRecipeWidget: " + intent);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "IN onHandleIntent: ");

        if(intent != null) {
            final String action = intent.getAction();
            if(ACTION_UPDATE_BEER_WIDGET.equals(action)) {
                if(intent.getExtras().containsKey("selectedBeer")) {
                    String beerSelected = intent.getParcelableExtra("selectedBeer");
                    //Log.d(TAG, "onHandleIntent: selected recipe: " + recipeSelected.getName());
                    handleActionUpdateRecipeWidget(beerSelected);

                } else {
                    Log.d(TAG, "onHandleIntent: No beer data.");
                }
            }
        }

    }

    private void handleActionUpdateRecipeWidget(String beerSelected) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BeerAppWidget.class));
        Log.d(TAG, "handleActionUpdateRecipeWidget: in this");

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.layout.beer_widget_list_view);
        BeerAppWidget.updateBeerWidget(this, appWidgetManager, appWidgetIds, beerSelected);

    }
}
