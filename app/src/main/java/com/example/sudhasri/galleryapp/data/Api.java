package com.example.sudhasri.galleryapp.data;

import com.example.sudhasri.galleryapp.R;
import com.example.sudhasri.galleryapp.data.model.ImageList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sudhasri on 20/8/16.
 */
public class Api
{
    public static final String BASE_URL = "https://pixabay.com/";
    private static RetrofitService mService;
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static RetrofitService getRetrofitService()
    {
        mService = retrofit.create(RetrofitService.class);
        return  mService;
    }
    public interface RetrofitService
    {
        @GET("api/?q=yellow+flower&image_type=photo&per_page=15")
        Call<ImageList> getImages(@Query("key") String apikey);
    }
}
