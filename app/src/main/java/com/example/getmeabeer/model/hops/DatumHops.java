package com.example.getmeabeer.model.hops;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumHops implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("countryOfOrigin")
    @Expose
    private String countryOfOrigin;
    @SerializedName("alphaAcidMin")
    @Expose
    private Float alphaAcidMin;
    @SerializedName("betaAcidMin")
    @Expose
    private Float betaAcidMin;
    @SerializedName("betaAcidMax")
    @Expose
    private Float betaAcidMax;
    @SerializedName("humuleneMin")
    @Expose
    private Float humuleneMin;
    @SerializedName("humuleneMax")
    @Expose
    private Float humuleneMax;
    @SerializedName("caryophylleneMin")
    @Expose
    private Float caryophylleneMin;
    @SerializedName("caryophylleneMax")
    @Expose
    private Float caryophylleneMax;
    @SerializedName("cohumuloneMin")
    @Expose
    private Float cohumuloneMin;
    @SerializedName("cohumuloneMax")
    @Expose
    private Float cohumuloneMax;
    @SerializedName("myrceneMin")
    @Expose
    private Float myrceneMin;
    @SerializedName("myrceneMax")
    @Expose
    private Float myrceneMax;
    @SerializedName("farneseneMin")
    @Expose
    private Float farneseneMin;
    @SerializedName("farneseneMax")
    @Expose
    private Float farneseneMax;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("categoryDisplay")
    @Expose
    private String categoryDisplay;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("updateDate")
    @Expose
    private String updateDate;
    @SerializedName("country")
    @Expose
    private Country country;
    public final static Parcelable.Creator<DatumHops> CREATOR = new Creator<DatumHops>() {


        @SuppressWarnings({"unchecked"})
        public DatumHops createFromParcel(Parcel in) {
            return new DatumHops(in);
        }

        public DatumHops[] newArray(int size) {
            return (new DatumHops[size]);
        }
    } ;

    protected DatumHops(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.countryOfOrigin = ((String) in.readValue((String.class.getClassLoader())));
        this.alphaAcidMin = ((Float) in.readValue((Float.class.getClassLoader())));
        this.betaAcidMin = ((Float) in.readValue((Float.class.getClassLoader())));
        this.betaAcidMax = ((Float) in.readValue((Float.class.getClassLoader())));
        this.humuleneMin = ((Float) in.readValue((Float.class.getClassLoader())));
        this.humuleneMax = ((Float) in.readValue((Float.class.getClassLoader())));
        this.caryophylleneMin = ((Float) in.readValue((Float.class.getClassLoader())));
        this.caryophylleneMax = ((Float) in.readValue((Float.class.getClassLoader())));
        this.cohumuloneMin = ((Float) in.readValue((Float.class.getClassLoader())));
        this.cohumuloneMax = ((Float) in.readValue((Float.class.getClassLoader())));
        this.myrceneMin = ((Float) in.readValue((Float.class.getClassLoader())));
        this.myrceneMax = ((Float) in.readValue((Float.class.getClassLoader())));
        this.farneseneMin = ((Float) in.readValue((Float.class.getClassLoader())));
        this.farneseneMax = ((Float) in.readValue((Float.class.getClassLoader())));
        this.category = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryDisplay = ((String) in.readValue((String.class.getClassLoader())));
        this.createDate = ((String) in.readValue((String.class.getClassLoader())));
        this.updateDate = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((Country) in.readValue((Country.class.getClassLoader())));
    }

    public DatumHops() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Float getAlphaAcidMin() {
        return alphaAcidMin;
    }

    public void setAlphaAcidMin(Float alphaAcidMin) {
        this.alphaAcidMin = alphaAcidMin;
    }

    public Float getBetaAcidMin() {
        return betaAcidMin;
    }

    public void setBetaAcidMin(Float betaAcidMin) {
        this.betaAcidMin = betaAcidMin;
    }

    public Float getBetaAcidMax() {
        return betaAcidMax;
    }

    public void setBetaAcidMax(Float betaAcidMax) {
        this.betaAcidMax = betaAcidMax;
    }

    public Float getHumuleneMin() {
        return humuleneMin;
    }

    public void setHumuleneMin(Float humuleneMin) {
        this.humuleneMin = humuleneMin;
    }

    public Float getHumuleneMax() {
        return humuleneMax;
    }

    public void setHumuleneMax(Float humuleneMax) {
        this.humuleneMax = humuleneMax;
    }

    public Float getCaryophylleneMin() {
        return caryophylleneMin;
    }

    public void setCaryophylleneMin(Float caryophylleneMin) {
        this.caryophylleneMin = caryophylleneMin;
    }

    public Float getCaryophylleneMax() {
        return caryophylleneMax;
    }

    public void setCaryophylleneMax(Float caryophylleneMax) {
        this.caryophylleneMax = caryophylleneMax;
    }

    public Float getCohumuloneMin() {
        return cohumuloneMin;
    }

    public void setCohumuloneMin(Float cohumuloneMin) {
        this.cohumuloneMin = cohumuloneMin;
    }

    public Float getCohumuloneMax() {
        return cohumuloneMax;
    }

    public void setCohumuloneMax(Float cohumuloneMax) {
        this.cohumuloneMax = cohumuloneMax;
    }

    public Float getMyrceneMin() {
        return myrceneMin;
    }

    public void setMyrceneMin(Float myrceneMin) {
        this.myrceneMin = myrceneMin;
    }

    public Float getMyrceneMax() {
        return myrceneMax;
    }

    public void setMyrceneMax(Float myrceneMax) {
        this.myrceneMax = myrceneMax;
    }

    public Float getFarneseneMin() {
        return farneseneMin;
    }

    public void setFarneseneMin(Float farneseneMin) {
        this.farneseneMin = farneseneMin;
    }

    public Float getFarneseneMax() {
        return farneseneMax;
    }

    public void setFarneseneMax(Float farneseneMax) {
        this.farneseneMax = farneseneMax;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryDisplay() {
        return categoryDisplay;
    }

    public void setCategoryDisplay(String categoryDisplay) {
        this.categoryDisplay = categoryDisplay;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(description);
        dest.writeValue(countryOfOrigin);
        dest.writeValue(alphaAcidMin);
        dest.writeValue(betaAcidMin);
        dest.writeValue(betaAcidMax);
        dest.writeValue(humuleneMin);
        dest.writeValue(humuleneMax);
        dest.writeValue(caryophylleneMin);
        dest.writeValue(caryophylleneMax);
        dest.writeValue(cohumuloneMin);
        dest.writeValue(cohumuloneMax);
        dest.writeValue(myrceneMin);
        dest.writeValue(myrceneMax);
        dest.writeValue(farneseneMin);
        dest.writeValue(farneseneMax);
        dest.writeValue(category);
        dest.writeValue(categoryDisplay);
        dest.writeValue(createDate);
        dest.writeValue(updateDate);
        dest.writeValue(country);
    }

    public int describeContents() {
        return 0;
    }
}
