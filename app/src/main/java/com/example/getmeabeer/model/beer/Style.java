package com.example.getmeabeer.model.beer;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Style implements Parcelable {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("ibuMin")
    @Expose
    private String ibuMin;
    @SerializedName("ibuMax")
    @Expose
    private String ibuMax;
    @SerializedName("abvMin")
    @Expose
    private String abvMin;
    @SerializedName("abvMax")
    @Expose
    private String abvMax;
    @SerializedName("srmMin")
    @Expose
    private String srmMin;
    @SerializedName("srmMax")
    @Expose
    private String srmMax;
    @SerializedName("ogMin")
    @Expose
    private String ogMin;
    @SerializedName("fgMin")
    @Expose
    private String fgMin;
    @SerializedName("fgMax")
    @Expose
    private String fgMax;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("updateDate")
    @Expose
    private String updateDate;
    public final static Parcelable.Creator<Style> CREATOR = new Creator<Style>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Style createFromParcel(Parcel in) {
            return new Style(in);
        }

        public Style[] newArray(int size) {
            return (new Style[size]);
        }
    };

    protected Style(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.categoryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.category = ((Category) in.readValue((Category.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.shortName = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.ibuMin = ((String) in.readValue((String.class.getClassLoader())));
        this.ibuMax = ((String) in.readValue((String.class.getClassLoader())));
        this.abvMin = ((String) in.readValue((String.class.getClassLoader())));
        this.abvMax = ((String) in.readValue((String.class.getClassLoader())));
        this.srmMin = ((String) in.readValue((String.class.getClassLoader())));
        this.srmMax = ((String) in.readValue((String.class.getClassLoader())));
        this.ogMin = ((String) in.readValue((String.class.getClassLoader())));
        this.fgMin = ((String) in.readValue((String.class.getClassLoader())));
        this.fgMax = ((String) in.readValue((String.class.getClassLoader())));
        this.createDate = ((String) in.readValue((String.class.getClassLoader())));
        this.updateDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Style() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIbuMin() {
        return ibuMin;
    }

    public void setIbuMin(String ibuMin) {
        this.ibuMin = ibuMin;
    }

    public String getIbuMax() {
        return ibuMax;
    }

    public void setIbuMax(String ibuMax) {
        this.ibuMax = ibuMax;
    }

    public String getAbvMin() {
        return abvMin;
    }

    public void setAbvMin(String abvMin) {
        this.abvMin = abvMin;
    }

    public String getAbvMax() {
        return abvMax;
    }

    public void setAbvMax(String abvMax) {
        this.abvMax = abvMax;
    }

    public String getSrmMin() {
        return srmMin;
    }

    public void setSrmMin(String srmMin) {
        this.srmMin = srmMin;
    }

    public String getSrmMax() {
        return srmMax;
    }

    public void setSrmMax(String srmMax) {
        this.srmMax = srmMax;
    }

    public String getOgMin() {
        return ogMin;
    }

    public void setOgMin(String ogMin) {
        this.ogMin = ogMin;
    }

    public String getFgMin() {
        return fgMin;
    }

    public void setFgMin(String fgMin) {
        this.fgMin = fgMin;
    }

    public String getFgMax() {
        return fgMax;
    }

    public void setFgMax(String fgMax) {
        this.fgMax = fgMax;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(categoryId);
        dest.writeValue(category);
        dest.writeValue(name);
        dest.writeValue(shortName);
        dest.writeValue(description);
        dest.writeValue(ibuMin);
        dest.writeValue(ibuMax);
        dest.writeValue(abvMin);
        dest.writeValue(abvMax);
        dest.writeValue(srmMin);
        dest.writeValue(srmMax);
        dest.writeValue(ogMin);
        dest.writeValue(fgMin);
        dest.writeValue(fgMax);
        dest.writeValue(createDate);
        dest.writeValue(updateDate);
    }

    public int describeContents() {
        return 0;
    }
}
