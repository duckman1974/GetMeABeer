package com.example.getmeabeer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.getmeabeer.R;
import com.example.getmeabeer.model.Datum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class BeersAdapter extends RecyclerView.Adapter<BeersAdapter.ViewHolder> {

    private static final String TAG = BeersAdapter.class.getSimpleName();

    Context context;
    private ArrayList<Datum> beers;

//    public BeersAdapter() {
////
////    }


    public BeersAdapter(Context context, ArrayList<Datum> beers) {
        this.context = context;
        this.beers = beers;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        if(beers.get(position).getName().isEmpty()) {
            Log.d(TAG, "BeerAdapter - beers are empty");
        } else {

            Datum beerData = beers.get(position);

            viewHolder.beerNameLabel.setText(R.string.beer_name_label);
            viewHolder.beerName.setText(beerData.getName());
            viewHolder.beerStyleLabel.setText(R.string.beer_style_label);
            viewHolder.beerStyle.setText(beerData.getStyle().getShortName());

            // unavailable image is from: https://www.google.com/search?q=beer+images&rlz=1C1CHBD_enUS842US842&tbm=isch&source=iu&ictx=1&fir=ng_tBxWZqU_O3M%253A%252CELm5i7x7TFxYFM%252C_&vet=1&usg=AI4_-kRhHq3sATGRSM5g9qdnlaNKXlJmlg&sa=X&ved=2ahUKEwiY67u0lI_jAhVST98KHSg2AOcQ9QEwCHoECAcQFA#imgrc=ng_tBxWZqU_O3M:
            if(beerData.getLabels() != null) {
                Picasso.get().load(beerData.getLabels().getLarge()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(viewHolder.beerLabel);
            }



        }

    }

    @Override
    public int getItemCount() {

        return beers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView beerNameLabel;
        final TextView beerName;
        final TextView beerStyleLabel;
        final TextView beerStyle;
        final ImageView beerLabel;



        ViewHolder(View view) {
            super(view);
            beerNameLabel = view.findViewById(R.id.beer_name_label);
            beerName = view.findViewById(R.id.beer_name);
            beerStyleLabel = view.findViewById(R.id.beer_style_label);
            beerStyle = view.findViewById(R.id.beer_style);
            beerLabel = view.findViewById(R.id.beer_label);
        }

    }
}
