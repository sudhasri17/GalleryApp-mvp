package com.example.sudhasri.galleryapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sudhasri on 20/8/16.
 */
public class ImageList
{
    @SerializedName("total")
    private int total;
    @SerializedName("totalHits")
    private int totalHits;
    @SerializedName("hits")
    private ArrayList<ImageData> images = new ArrayList<>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public ArrayList<ImageData> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageData> images) {
        this.images = images;
    }
}
