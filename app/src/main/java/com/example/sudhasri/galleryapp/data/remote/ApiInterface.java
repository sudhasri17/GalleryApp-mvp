package com.example.sudhasri.galleryapp.data.remote;

import com.example.sudhasri.galleryapp.data.model.ImageList;

import retrofit2.Call;

/**
 * Created by sudhasri on 20/8/16.
 */
public interface ApiInterface
{
    public Call<ImageList> getImages (String apikey);
}
