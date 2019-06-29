package com.example.getmeabeer.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Srm implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("hex")
    @Expose
    private String hex;
    public final static Parcelable.Creator<Srm> CREATOR = new Creator<Srm>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Srm createFromParcel(Parcel in) {
            return new Srm(in);
        }

        public Srm[] newArray(int size) {
            return (new Srm[size]);
        }
    };

    protected Srm(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.hex = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Srm() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(hex);
    }

    public int describeContents() {
        return 0;
    }
}
