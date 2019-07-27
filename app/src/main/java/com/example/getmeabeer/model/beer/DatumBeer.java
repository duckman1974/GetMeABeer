package com.example.getmeabeer.model.beer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DatumBeer implements Parcelable {

    @NonNull
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @NonNull
    @Expose
    private String name;
    @SerializedName("nameDisplay")
    @Ignore
    @Expose
    private String nameDisplay;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("abv")
    @NonNull
    @Expose
    private String abv;
    @SerializedName("ibu")
    @Ignore
    @Expose
    private String ibu;
    @SerializedName("srmId")
    @Ignore
    @Expose
    private Integer srmId;
    @SerializedName("availableId")
    @Ignore
    @Expose
    private Integer availableId;
    @SerializedName("styleId")
    @Ignore
    @Expose
    private Integer styleId;
    @SerializedName("isOrganic")
    @Ignore
    @Expose
    private String isOrganic;
    @SerializedName("isRetired")
    @Ignore
    @Expose
    private String isRetired;
    @SerializedName("labels")
    @Ignore
    @Expose
    private Labels labels;
    @SerializedName("status")
    @Ignore
    @Expose
    private String status;
    @SerializedName("statusDisplay")
    @Ignore
    @Expose
    private String statusDisplay;
    @SerializedName("originalGravity")
    @Ignore
    @Expose
    private String originalGravity;
    @SerializedName("createDate")
    @Ignore
    @Expose
    private String createDate;
    @SerializedName("updateDate")
    @Ignore
    @Expose
    private String updateDate;
    @SerializedName("srm")
    @Ignore
    @Expose
    private Srm srm;
    @SerializedName("available")
    @Ignore
    @Expose
    private Available available;
    @SerializedName("style")
    @Ignore
    @Expose
    private Style style;
    public final static Parcelable.Creator<DatumBeer> CREATOR = new Creator<DatumBeer>() {

        @SuppressWarnings({"unchecked"})
        public DatumBeer createFromParcel(Parcel in) {

            return new DatumBeer(in);
        }

        public DatumBeer[] newArray(int size) {

            return (new DatumBeer[size]);
        }
    };

    protected DatumBeer(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.nameDisplay = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.abv = ((String) in.readValue((String.class.getClassLoader())));
        this.ibu = ((String) in.readValue((String.class.getClassLoader())));
        this.srmId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.availableId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.styleId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.isOrganic = ((String) in.readValue((String.class.getClassLoader())));
        this.isRetired = ((String) in.readValue((String.class.getClassLoader())));
        this.labels = ((Labels) in.readValue((Labels.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.statusDisplay = ((String) in.readValue((String.class.getClassLoader())));
        this.originalGravity = ((String) in.readValue((String.class.getClassLoader())));
        this.createDate = ((String) in.readValue((String.class.getClassLoader())));
        this.updateDate = ((String) in.readValue((String.class.getClassLoader())));
        this.srm = ((Srm) in.readValue((Srm.class.getClassLoader())));
        this.available = ((Available) in.readValue((Available.class.getClassLoader())));
        this.style = ((Style) in.readValue((Style.class.getClassLoader())));
    }

    @Ignore
    private List<DatumBeer> beerModel;

    public DatumBeer(String id, String name, String abv) {
        this.id = id;
        this.name = name;
        this.abv = abv;
    }

    public DatumBeer() {
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

    public String getNameDisplay() {
        return nameDisplay;
    }

    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public Integer getSrmId() {
        return srmId;
    }

    public void setSrmId(Integer srmId) {
        this.srmId = srmId;
    }

    public Integer getAvailableId() {
        return availableId;
    }

    public void setAvailableId(Integer availableId) {
        this.availableId = availableId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }

    public String getIsRetired() {
        return isRetired;
    }

    public void setIsRetired(String isRetired) {
        this.isRetired = isRetired;
    }

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
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

    public String getOriginalGravity() {
        return originalGravity;
    }

    public void setOriginalGravity(String originalGravity) {
        this.originalGravity = originalGravity;
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

    public Srm getSrm() {
        return srm;
    }

    public void setSrm(Srm srm) {
        this.srm = srm;
    }

    public Available getAvailable() {
        return available;
    }

    public void setAvailable(Available available) {
        this.available = available;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(nameDisplay);
        dest.writeValue(description);
        dest.writeValue(abv);
        dest.writeValue(ibu);
        dest.writeValue(srmId);
        dest.writeValue(availableId);
        dest.writeValue(styleId);
        dest.writeValue(isOrganic);
        dest.writeValue(isRetired);
        dest.writeValue(labels);
        dest.writeValue(status);
        dest.writeValue(statusDisplay);
        dest.writeValue(originalGravity);
        dest.writeValue(createDate);
        dest.writeValue(updateDate);
        dest.writeValue(srm);
        dest.writeValue(available);
        dest.writeValue(style);
    }

    public int describeContents() {
        return 0;
    }
}
