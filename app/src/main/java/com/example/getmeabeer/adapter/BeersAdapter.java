package com.example.getmeabeer.adapter;

import android.Manifest;
import android.app.Activity;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getmeabeer.MainActivity;
import com.example.getmeabeer.MainFragment;
import com.example.getmeabeer.R;
import com.example.getmeabeer.model.beer.BeerDbEntry;
import com.example.getmeabeer.model.beer.DatumBeer;
import com.example.getmeabeer.network.DatabaseHelper;
import com.example.getmeabeer.network.FavBeers;
import com.example.getmeabeer.roomDatabase.BeerRoomDatabase;
import com.example.getmeabeer.utility.AppExecutor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class BeersAdapter extends RecyclerView.Adapter<BeersAdapter.ViewHolder> {

    private static final String TAG = BeersAdapter.class.getSimpleName();


    Context context;
    BeerSelectedListener callback;
    private ArrayList<DatumBeer> beers;
    boolean inDatabase = false;
    private int inDB;
   // MainFragment fragment;
    List<BeerDbEntry> favBeers;
    //BeerDbEntry be;


    public BeersAdapter(Context context, ArrayList<DatumBeer> beers, List<BeerDbEntry> favBeers) {
        this.context = context;
        this.beers = beers;
        this.favBeers = favBeers;
    }

    public BeersAdapter(Context context) {
        this.context = context;
    }

    public interface BeerSelectedListener {
        void beerSelectedForWidget(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view_main, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        if(beers.get(position).getName().isEmpty()) {
            Log.d(TAG, "BeerAdapter - beers are empty");
        } else {
            final DatumBeer beerData = beers.get(position);

            viewHolder.beerNameLabel.setText(R.string.beer_name_label);
            viewHolder.beerName.setText(beerData.getName());
            viewHolder.beerStyleLabel.setText(R.string.beer_style_label);
            viewHolder.beerStyle.setText(beerData.getStyle().getShortName());

            // unavailable image is from: https://www.google.com/search?q=beer
            // +images&rlz=1C1CHBD_enUS842US842&tbm=isch&source=iu&ictx=1&fir=ng_tBxWZqU_O3M%253A%252CELm5i7x7TFxYFM%252C_&vet=1
            // &usg=AI4_-kRhHq3sATGRSM5g9qdnlaNKXlJmlg&sa=X&ved=2ahUKEwiY67u0lI_jAhVST98KHSg2AOcQ9QEwCHoECAcQFA#imgrc=ng_tBxWZqU_O3M:
            Picasso.get().load(beerData.getLabels().getLarge())
                    .placeholder(ContextCompat.getDrawable(context, R.drawable.unavailable_beer))
                    .error(ContextCompat.getDrawable(context, R.drawable.unavailable_beer))
                    .into(viewHolder.beerLabel);


            for(BeerDbEntry b : favBeers) {
                Log.d(TAG, "onBindViewHolder size: " + favBeers.size());
                if(b.getName().equals(beerData.getName())) {
                    Log.d(TAG, "onBindViewHolder: b.getName: " + b.getName());
                    Log.d(TAG, "onBindViewHolder: beerData.getName " + beerData.getName());
                    viewHolder.favButton.setBackgroundColor(Color.YELLOW);
                } else {
                    viewHolder.favButton.setBackgroundColor(Color.WHITE);
                }
            }



            viewHolder.favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    inDatabase = getFavBeer(favBeers, beerData);
                    Log.d(TAG, "IN DB TRUE/FALSE: " + inDatabase);

                    //boolean test = viewHolder.favButton.isActivated();
                    //Log.d(TAG, "onClick: button T/F: " + test);
                    // TODO: work on having to hit the button 2 times to delete.
                    if(inDatabase) {
                    //if(inDatabase) {
                        viewHolder.favButton.setBackgroundColor(Color.WHITE);
                        viewHolder.fav_text.setText("");
                        inDB = 0;
                       final BeerDbEntry be = new BeerDbEntry(beerData.getId(), beerData.getName(), beerData.getAbv(), inDB);
                        AppExecutor.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "Deleting favorite Beer from DB");
                                viewHolder.beerRoomDatabase.beerDao().delete(be);
                            }
                        });
                    } else {
                        viewHolder.favButton.setBackgroundColor(Color.YELLOW);
                        viewHolder.fav_text.setText("FAVORITE");
                        inDB = 1;
                        final BeerDbEntry be = new BeerDbEntry(beerData.getId(), beerData.getName(), beerData.getAbv(), inDB);
                        Log.d(TAG, "Adding id: " + beerData.getId());
                        Log.d(TAG, "Adding name: " + beerData.getName());
                        Log.d(TAG, "Adding abv: " + beerData.getAbv());
                        AppExecutor.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "Inserting favorite beer in db.");
                                viewHolder.beerRoomDatabase.beerDao().insert(be);
                            }
                        });
                    }
                }
            });
        }
    }


    private boolean getFavBeer(List<BeerDbEntry> favBeer, DatumBeer beer) {
        Log.d(TAG, "getFavBeer: " + beer.getId());
        for(BeerDbEntry b : favBeer) {
            Log.d(TAG, "getFavBeer: loop: " + b.getId());
            if(b.getId().equals(beer.getId())) {
                return true;
            }
        }
        return false;
    }



    @Override
    public int getItemCount() {
        return beers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView beerNameLabel;
        final TextView beerName;
        final TextView beerStyleLabel;
        final TextView beerStyle;
        final ImageView beerLabel;
        final ImageButton favButton;
        final TextView fav_text;
        final BeerRoomDatabase beerRoomDatabase;


        ViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            beerNameLabel = view.findViewById(R.id.beer_name_label);
            beerName = view.findViewById(R.id.beer_name);
            beerStyleLabel = view.findViewById(R.id.beer_style_label);
            beerStyle = view.findViewById(R.id.beer_style);
            beerLabel = view.findViewById(R.id.beer_label);
            favButton = view.findViewById(R.id.favorite_button);
            beerRoomDatabase = BeerRoomDatabase.getDatabase(context);
            fav_text = view.findViewById(R.id.fav_Text);
            favButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            callback = (BeerSelectedListener) context;

            Log.d(TAG, "onClick: in ViewHolder " + view.getId());
            int adapterPost = getAdapterPosition();
            Log.d(TAG, "callback: " + callback);
            if(view.getId() == favButton.getId()) {
                Toast.makeText(context, "Beer added to widget on home screen", Toast.LENGTH_SHORT).show();
                callback.beerSelectedForWidget(adapterPost);
            }
        }


    }
}
