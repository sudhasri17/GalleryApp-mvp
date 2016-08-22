package com.example.sudhasri.galleryapp.presenter;

import com.example.sudhasri.galleryapp.data.model.ImageData;

import java.util.ArrayList;

/**
 * Created by sudhasri on 20/8/16.
 */
public class MainContract
{
    public interface View
    {
        public void showProgress();
        public void hideProgress();
        public void loadImages(ArrayList<ImageData> images);
        public void showErrorMessage(String error);
        public void notifyImageFetched(int position);
    }

    public interface Presenter extends BasePresenter
    {
        public void getImageList(String apiKey);
        public void getImage(ImageData imageData, int position);
    }
}

