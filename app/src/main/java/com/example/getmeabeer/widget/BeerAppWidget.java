package com.example.getmeabeer.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.example.getmeabeer.MainActivity;
import com.example.getmeabeer.R;
import com.example.getmeabeer.model.beer.BeerDbEntry;

public class BeerAppWidget extends AppWidgetProvider {

    private static final String TAG = BeerAppWidget.class.getSimpleName();

    private static BeerDbEntry favoriteBeersForWidget = null;


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
        int width = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        RemoteViews views;
        if(width < 300) {

            Intent intent;
            views = new RemoteViews(context.getPackageName(), R.layout.beer_app_widget);

            if(favoriteBeersForWidget == null) {
                intent = new Intent(context, MainActivity.class);
                views.setTextViewText(R.id.tv_widget, "No Favorite Beers");
            } else {
                intent = new Intent(context, MainActivity.class);
                intent.putExtra("favBeers", favoriteBeersForWidget.getName());
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                views.setTextViewText(R.id.tv_widget, favoriteBeersForWidget.getName());
            }

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);

        } else {
            views = new RemoteViews(context.getPackageName(), R.layout.beer_widget_list_view);

            Intent intent = new Intent(context, WidgetRemoteViewsService.class);
            views.setRemoteAdapter(R.layout.beer_widget_list_view, intent);
            views.setEmptyView(R.layout.beer_widget_list_view, R.id.empty_view);
        }
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateBeerWidget(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, String beerSelected) {
        //favoriteBeersForWidget = beerSelected;
        favoriteBeersForWidget.setName(beerSelected);
        // Could be multiple widgets
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        UpdateBeerWidgetIntentService.startActionUpdateRecipeWidget(context, favoriteBeersForWidget.getName());
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        UpdateBeerWidgetIntentService.startActionUpdateRecipeWidget(context, favoriteBeersForWidget.getName());
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }



    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}
