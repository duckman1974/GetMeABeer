package com.example.getmeabeer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.getmeabeer.R;
import com.example.getmeabeer.model.breweries.DatumBreweries;
import com.example.getmeabeer.model.hops.DatumHops;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BreweriesAdapter extends RecyclerView.Adapter<BreweriesAdapter.ViewHolder> {

    private static final String TAG = BreweriesAdapter.class.getSimpleName();

    Context context;
    private ArrayList<DatumBreweries> breweries;

    public BreweriesAdapter(Context context, ArrayList<DatumBreweries> breweries) {
        this.context = context;
        this.breweries = breweries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view_brewery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if(breweries.get(position).getName().isEmpty()) {
            Log.d(TAG, "BreweryAdapter is empty");
        } else {
            Log.d(TAG, "BreweryAdapter - Is NOT empty");
            DatumBreweries brewery = breweries.get(position);

            viewHolder.breweryName.setText(brewery.getName());
            viewHolder.breweryWebsite.setText(brewery.getWebsite());

            Picasso.get().load(brewery.getImages().getSquareMedium()).error(ContextCompat.getDrawable(context, R.drawable.unavailable_beer))
                    .into(viewHolder.breweryImage);
        }

    }

    @Override
    public int getItemCount() {
        return breweries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView breweryImage;
        final TextView breweryName;
        final TextView breweryWebsite;

        ViewHolder(View view) {
            super(view);
            breweryImage = view.findViewById(R.id.brewery_label);
            breweryName = view.findViewById(R.id.brewery_name);
            breweryWebsite = view.findViewById(R.id.brewery_website);
        }
    }
}
