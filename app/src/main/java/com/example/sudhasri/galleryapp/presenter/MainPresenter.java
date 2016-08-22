package com.example.sudhasri.galleryapp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.sudhasri.galleryapp.R;
import com.example.sudhasri.galleryapp.data.Injector;
import com.example.sudhasri.galleryapp.data.model.ImageData;
import com.example.sudhasri.galleryapp.data.model.ImageList;
import com.example.sudhasri.galleryapp.data.remote.ApiInterface;
import com.example.sudhasri.galleryapp.presenter.MainContract.Presenter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sudhasri on 20/8/16.
 */
public class MainPresenter implements Presenter
{
    private MainContract.View mActionView;
    private Injector mInjector;
    private ArrayList<ImageData> mImages;
    private boolean isImageSet = false;

    public MainPresenter(MainContract.View view, Injector injector)
    {
        mActionView = view;
        mInjector = injector;
    }
    @Override
    public void getImageList(String apiKey)
    {
        mActionView.showProgress();
        ApiInterface apiInterface = mInjector.getApiService();
        Call<ImageList> call = apiInterface.getImages(apiKey);
        call.enqueue(new Callback<ImageList>()
        {
            @Override
            public void onResponse(Call<ImageList> call, Response<ImageList> response) {
                if (response != null)
                {
                    if (response.body() != null)
                    {
                        mImages = response.body().getImages();

                        mActionView.loadImages(mImages);
                        Log.d("Img", "no of images: " + mImages.size());
                    }
                    else
                    {
                        mActionView.showErrorMessage(response.message());
                    }
                }
                else
                {
                    mActionView.showErrorMessage(mInjector.getContext().getString(R.string.server_error));
                }
            }

            @Override
            public void onFailure(Call<ImageList> call, Throwable t) {
                mActionView.showErrorMessage(mInjector.getContext().getString(R.string.server_error));
            }
        });
    }

    public void getImage(ImageData imageData, int position)
    {
        new GetImageTask(imageData, position).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    //Async task to get image bitmaps, since use of libraries be avoided for handling images
    private class GetImageTask extends AsyncTask<Integer, Void, Bitmap>
    {
        private ImageData mImageData;
        private int mPosition;

        public GetImageTask(ImageData imageData, int i) {
            mImageData = imageData;
            mPosition = i;
        }

        @Override
        protected Bitmap doInBackground(Integer... params)
        {
            URL url;
            Bitmap bitmap = null;

            try
            {
                String urlforSmallerImg = mImageData.getWebformatURL().replace("_640", "_180");
                url = new URL(urlforSmallerImg);
                bitmap = BitmapFactory.decodeStream(url.openStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            super.onPostExecute(bitmap);
            mImageData.setBitmap(bitmap);
            setImage(mPosition);
            Log.d("Img", "got bitmap " + mPosition);

        }
    }

    private void setImage(int position) {
       /* if (!isImageSet)
        {
            mActionView.hideProgress();
            mActionView.loadImages(mImages);
            isImageSet = true;
        }
        else*/
        {
            mActionView.hideProgress();
            mActionView.notifyImageFetched(position);
        }
    }


}
