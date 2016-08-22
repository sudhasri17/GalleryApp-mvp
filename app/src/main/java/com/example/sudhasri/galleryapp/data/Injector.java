package com.example.sudhasri.galleryapp.data;

import android.content.Context;

import com.example.sudhasri.galleryapp.data.remote.ApiInterface;
import com.example.sudhasri.galleryapp.data.remote.PicApi;

/**
 * Created by sudhasri on 20/8/16.
 */
public class Injector
{
    Context mContext;

    public Injector()
    {
        mContext = getContext();
    }
    public Context getContext()
    {
        return mContext;
    }

    public ApiInterface getApiService()
    {
        return new PicApi(Api.getRetrofitService());
    }
}
