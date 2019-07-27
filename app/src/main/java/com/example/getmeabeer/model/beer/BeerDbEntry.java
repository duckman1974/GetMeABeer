package com.example.getmeabeer.model.beer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "beer_table")
public class BeerDbEntry {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @NonNull
    @Expose
    private String name;
    @SerializedName("abv")
    @NonNull
    @Expose
    private String abv;
    @SerializedName("inDb")
    @NonNull
    @Expose
    private int inDb;


    public BeerDbEntry(String id, String name, String abv, int inDb) {
        this.id = id;
        this.name = name;
        this.abv = abv;
        this.inDb = inDb;
    }

    public BeerDbEntry() {
    }

    @NonNull
    public String getName() {

        return name;
    }

    public void setName(@NonNull String name) {

        this.name = name;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {

        this.id = id;
    }

    @NonNull
    public String getAbv() {

        return abv;
    }

    public void setAbv(@NonNull String abv) {

        this.abv = abv;
    }

    public int getInDb() {
        return inDb;
    }

    public void setInDb(int inDb) {
        this.inDb = inDb;
    }
}
