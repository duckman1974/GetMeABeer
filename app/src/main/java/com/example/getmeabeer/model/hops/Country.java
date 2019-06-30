package com.example.getmeabeer.model.hops;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country implements Parcelable
{

    @SerializedName("isoCode")
    @Expose
    private String isoCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("isoThree")
    @Expose
    private String isoThree;
    @SerializedName("numberCode")
    @Expose
    private Integer numberCode;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    public final static Parcelable.Creator<Country> CREATOR = new Creator<Country>() {


        @SuppressWarnings({"unchecked"})
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        public Country[] newArray(int size) {
            return (new Country[size]);
        }

    };

    protected Country(Parcel in) {
        this.isoCode = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.displayName = ((String) in.readValue((String.class.getClassLoader())));
        this.isoThree = ((String) in.readValue((String.class.getClassLoader())));
        this.numberCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Country() {
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIsoThree() {
        return isoThree;
    }

    public void setIsoThree(String isoThree) {
        this.isoThree = isoThree;
    }

    public Integer getNumberCode() {
        return numberCode;
    }

    public void setNumberCode(Integer numberCode) {
        this.numberCode = numberCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(isoCode);
        dest.writeValue(name);
        dest.writeValue(displayName);
        dest.writeValue(isoThree);
        dest.writeValue(numberCode);
        dest.writeValue(createDate);
    }

    public int describeContents() {
        return 0;
    }
}
