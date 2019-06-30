package com.example.getmeabeer.model.hops;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import android.os.Parcel;

import com.google.gson.annotations.Expose;

public class JsonResponseDataHops implements Parcelable {

    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("numberOfPages")
    @Expose
    private Integer numberOfPages;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("data")
    @Expose
    private List<DatumHops> data = null;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Parcelable.Creator<JsonResponseDataHops> CREATOR = new Creator<JsonResponseDataHops>() {


        @SuppressWarnings({"unchecked"})
        public JsonResponseDataHops createFromParcel(Parcel in) {
            return new JsonResponseDataHops(in);
        }

        public JsonResponseDataHops[] newArray(int size) {
            return (new JsonResponseDataHops[size]);
        }
    };

    protected JsonResponseDataHops(Parcel in) {
        this.currentPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.numberOfPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.data, (DatumHops.class.getClassLoader()));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public JsonResponseDataHops() {
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<DatumHops> getData() {
        return data;
    }

    public void setData(List<DatumHops> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(currentPage);
        dest.writeValue(numberOfPages);
        dest.writeValue(totalResults);
        dest.writeList(data);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }
}
