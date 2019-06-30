package com.example.getmeabeer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.getmeabeer.R;
import com.example.getmeabeer.model.hops.DatumHops;
import com.example.getmeabeer.utility.DescriptionRegex;
import java.util.ArrayList;

public class HopsAdapter extends RecyclerView.Adapter<HopsAdapter.ViewHolder> {

    private static final String TAG = HopsAdapter.class.getSimpleName();

    Context context;
    private ArrayList<DatumHops> hops;

    public HopsAdapter(Context context, ArrayList<DatumHops> hops) {
        this.context = context;
        this.hops = hops;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view_hops, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if(hops.get(position).getName().isEmpty()) {
            Log.d(TAG, "HopsAdapter - beers are empty");
        } else {
            Log.d(TAG, "HopsAdapter - Is NOT empty");
            DatumHops hopsData =  hops.get(position);
            DescriptionRegex d = new DescriptionRegex();
            String newDescription = d.processDescriptString(hopsData.getDescription());

            viewHolder.tvName.setText(hopsData.getName());
            viewHolder.tvCountry.setText(hopsData.getCountry().getName());
            viewHolder.tvDescription.setText(newDescription + ".");
            viewHolder.tvCreationDate.setText(hopsData.getCreateDate());
            viewHolder.tvAcid.setText(hopsData.getAlphaAcidMin().toString());
        }
    }

    @Override
    public int getItemCount() {
        return hops.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvName;
        final TextView tvCountry;
        final TextView tvAcid;
        final TextView tvDescription;
        final TextView tvCreationDate;


        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.hops_name);
            tvCountry = view.findViewById(R.id.hops_country);
            tvAcid = view.findViewById(R.id.hops_acid);
            tvDescription = view.findViewById(R.id.hops_description);
            tvCreationDate = view.findViewById(R.id.hops_creation_date);
        }
    }
}
