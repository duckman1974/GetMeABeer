package com.example.getmeabeer.model.breweries;

import android.os.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumBreweries implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nameShortDisplay")
    @Expose
    private String nameShortDisplay;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("established")
    @Expose
    private String established;
    @SerializedName("isOrganic")
    @Expose
    private String isOrganic;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusDisplay")
    @Expose
    private String statusDisplay;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("updateDate")
    @Expose
    private String updateDate;
    @SerializedName("isMassOwned")
    @Expose
    private String isMassOwned;
    @SerializedName("isInBusiness")
    @Expose
    private String isInBusiness;
    @SerializedName("isVerified")
    @Expose
    private String isVerified;
    @SerializedName("mailingListUrl")
    @Expose
    private String mailingListUrl;
    public final static Parcelable.Creator<DatumBreweries> CREATOR = new Creator<DatumBreweries>() {


        @SuppressWarnings({"unchecked"})
        public DatumBreweries createFromParcel(Parcel in) {
            return new DatumBreweries(in);
        }

        public DatumBreweries[] newArray(int size) {
            return (new DatumBreweries[size]);
        }
    };

    protected DatumBreweries(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.nameShortDisplay = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.website = ((String) in.readValue((String.class.getClassLoader())));
        this.established = ((String) in.readValue((String.class.getClassLoader())));
        this.isOrganic = ((String) in.readValue((String.class.getClassLoader())));
        this.images = ((Images) in.readValue((Images.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.statusDisplay = ((String) in.readValue((String.class.getClassLoader())));
        this.createDate = ((String) in.readValue((String.class.getClassLoader())));
        this.updateDate = ((String) in.readValue((String.class.getClassLoader())));
        this.isMassOwned = ((String) in.readValue((String.class.getClassLoader())));
        this.isInBusiness = ((String) in.readValue((String.class.getClassLoader())));
        this.isVerified = ((String) in.readValue((String.class.getClassLoader())));
        this.mailingListUrl = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DatumBreweries() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameShortDisplay() {
        return nameShortDisplay;
    }

    public void setNameShortDisplay(String nameShortDisplay) {
        this.nameShortDisplay = nameShortDisplay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEstablished() {
        return established;
    }

    public void setEstablished(String established) {
        this.established = established;
    }

    public String getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getIsMassOwned() {
        return isMassOwned;
    }

    public void setIsMassOwned(String isMassOwned) {
        this.isMassOwned = isMassOwned;
    }

    public String getIsInBusiness() {
        return isInBusiness;
    }

    public void setIsInBusiness(String isInBusiness) {
        this.isInBusiness = isInBusiness;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    public String getMailingListUrl() {
        return mailingListUrl;
    }

    public void setMailingListUrl(String mailingListUrl) {
        this.mailingListUrl = mailingListUrl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(nameShortDisplay);
        dest.writeValue(description);
        dest.writeValue(website);
        dest.writeValue(established);
        dest.writeValue(isOrganic);
        dest.writeValue(images);
        dest.writeValue(status);
        dest.writeValue(statusDisplay);
        dest.writeValue(createDate);
        dest.writeValue(updateDate);
        dest.writeValue(isMassOwned);
        dest.writeValue(isInBusiness);
        dest.writeValue(isVerified);
        dest.writeValue(mailingListUrl);
    }

    public int describeContents() {
        return 0;
    }
}
