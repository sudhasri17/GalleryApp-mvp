package com.example.sudhasri.galleryapp;

import android.app.Application;
import android.content.Context;

import com.example.sudhasri.galleryapp.data.Injector;

/**
 * Created by sudhasri on 20/8/16.
 */
public class GalleryApplication extends Application
{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {

        return mContext;
    }

}
