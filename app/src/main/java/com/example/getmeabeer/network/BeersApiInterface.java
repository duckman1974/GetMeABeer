package com.example.getmeabeer.network;

import com.example.getmeabeer.model.Datum;

import org.json.JSONObject;

import java.util.List;

public interface BeersApiInterface {
    void passJsonResults(List<Datum> data);
}
