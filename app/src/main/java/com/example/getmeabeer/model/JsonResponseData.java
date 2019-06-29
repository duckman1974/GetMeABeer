package com.example.getmeabeer.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonResponseData implements Parcelable {

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
    private List<Datum> data = null;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Parcelable.Creator<JsonResponseData> CREATOR = new Creator<JsonResponseData>() {

        @SuppressWarnings({
                "unchecked"
        })
        public JsonResponseData createFromParcel(Parcel in) {
            return new JsonResponseData(in);
        }

        public JsonResponseData[] newArray(int size) {
            return (new JsonResponseData[size]);
        }
    };

    protected JsonResponseData(Parcel in) {
        this.currentPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.numberOfPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.data, (Datum.class.getClassLoader()));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public JsonResponseData() {
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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
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
