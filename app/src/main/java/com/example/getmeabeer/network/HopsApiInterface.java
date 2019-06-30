package com.example.getmeabeer.network;

import com.example.getmeabeer.model.hops.DatumHops;

import java.util.List;

public interface HopsApiInterface {
    void passHopsJsonResults(List<DatumHops> data);
}
