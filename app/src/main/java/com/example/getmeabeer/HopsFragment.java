package com.example.getmeabeer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.getmeabeer.adapter.HopsAdapter;
import com.example.getmeabeer.model.hops.DatumHops;
import com.example.getmeabeer.network.HopsApi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HopsFragment extends Fragment {

    private static final String TAG = HopsFragment.class.getSimpleName();
    Context context;
    private HopsApi hopsApi = new HopsApi();
    HopsAdapter hopsAdapter;
    @BindView(R.id.hops_recyclerview)
    RecyclerView hopsRecyclerView;
    private ArrayList<DatumHops> hopsArrayList = null;


    public HopsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        View rootView = inflater.inflate(R.layout.fragment_hops, container, false);
        ButterKnife.bind(this, rootView);

        MainActivity main = (MainActivity) getActivity();
        hopsArrayList = main.getHopsArrayList();

        HopsAdapter hopsAdapter = new HopsAdapter(context, hopsArrayList);
        hopsRecyclerView.setAdapter(hopsAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        hopsRecyclerView.setLayoutManager(layoutManager);

        if(hopsArrayList != null && !hopsArrayList.isEmpty()) {
            setAdapter(hopsArrayList);
        } else {
            Log.d(TAG, "HopsFrag - HopsList is EMPTY");
        }
        return  rootView;
    }

    private void setAdapter(ArrayList hops) {
        hopsAdapter = new HopsAdapter(context, hops);
        hopsRecyclerView.setAdapter(hopsAdapter);
    }
}
