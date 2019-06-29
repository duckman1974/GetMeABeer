package com.example.getmeabeer.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Labels implements Parcelable {

    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("large")
    @Expose
    private String large;
    @SerializedName("contentAwareIcon")
    @Expose
    private String contentAwareIcon;
    @SerializedName("contentAwareMedium")
    @Expose
    private String contentAwareMedium;
    @SerializedName("contentAwareLarge")
    @Expose
    private String contentAwareLarge;
    public final static Parcelable.Creator<Labels> CREATOR = new Creator<Labels>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Labels createFromParcel(Parcel in) {
            return new Labels(in);
        }

        public Labels[] newArray(int size) {
            return (new Labels[size]);
        }
    };

    protected Labels(Parcel in) {
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
        this.medium = ((String) in.readValue((String.class.getClassLoader())));
        this.large = ((String) in.readValue((String.class.getClassLoader())));
        this.contentAwareIcon = ((String) in.readValue((String.class.getClassLoader())));
        this.contentAwareMedium = ((String) in.readValue((String.class.getClassLoader())));
        this.contentAwareLarge = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Labels() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getContentAwareIcon() {
        return contentAwareIcon;
    }

    public void setContentAwareIcon(String contentAwareIcon) {
        this.contentAwareIcon = contentAwareIcon;
    }

    public String getContentAwareMedium() {
        return contentAwareMedium;
    }

    public void setContentAwareMedium(String contentAwareMedium) {
        this.contentAwareMedium = contentAwareMedium;
    }

    public String getContentAwareLarge() {
        return contentAwareLarge;
    }

    public void setContentAwareLarge(String contentAwareLarge) {
        this.contentAwareLarge = contentAwareLarge;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(icon);
        dest.writeValue(medium);
        dest.writeValue(large);
        dest.writeValue(contentAwareIcon);
        dest.writeValue(contentAwareMedium);
        dest.writeValue(contentAwareLarge);
    }

    public int describeContents() {
        return 0;
    }
}
