package com.example.getmeabeer.model.beer;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonResponseDataBeer implements Parcelable {

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
    private List<DatumBeer> data = null;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Parcelable.Creator<JsonResponseDataBeer> CREATOR = new Creator<JsonResponseDataBeer>() {

        @SuppressWarnings({
                "unchecked"
        })
        public JsonResponseDataBeer createFromParcel(Parcel in) {
            return new JsonResponseDataBeer(in);
        }

        public JsonResponseDataBeer[] newArray(int size) {
            return (new JsonResponseDataBeer[size]);
        }
    };

    protected JsonResponseDataBeer(Parcel in) {
        this.currentPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.numberOfPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.data, (DatumBeer.class.getClassLoader()));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public JsonResponseDataBeer() {
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

    public List<DatumBeer> getData() {
        return data;
    }

    public void setData(List<DatumBeer> data) {
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
