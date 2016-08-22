package com.example.sudhasri.galleryapp.data.remote;

import com.example.sudhasri.galleryapp.R;
import com.example.sudhasri.galleryapp.data.Api;
import com.example.sudhasri.galleryapp.data.model.ImageList;

import retrofit2.Call;

/**
 * Created by sudhasri on 20/8/16.
 */
public class PicApi implements ApiInterface
{

    private final Api.RetrofitService mService;

    public PicApi(Api.RetrofitService service)
    {
        mService = service;
    }

    @Override
    public Call<ImageList> getImages(String apikey)
    {
        Call<ImageList> imageListCall = mService.getImages(apikey);
        return imageListCall;
    }
}
