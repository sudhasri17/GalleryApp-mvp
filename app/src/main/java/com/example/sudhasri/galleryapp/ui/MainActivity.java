package com.example.sudhasri.galleryapp.ui;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.sudhasri.galleryapp.R;
import com.example.sudhasri.galleryapp.data.Injector;
import com.example.sudhasri.galleryapp.data.model.ImageData;
import com.example.sudhasri.galleryapp.presenter.MainContract;
import com.example.sudhasri.galleryapp.presenter.MainPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.View
{
    private RecyclerView.Adapter<ImageGridAdapter.ImageCardHolder> mImageGridAdapter;
    private MainPresenter mPresenter;
    private ProgressDialog mProgressDialog;
    private RecyclerView mImageGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageGrid = (RecyclerView) findViewById(R.id.image_grid);
        mPresenter = new MainPresenter(this, new Injector());
        if (mImageGridAdapter == null)
        {
            mPresenter.getImageList(getString(R.string.api_key));
        }
    }

    @Override
    public void showProgress() {
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
        {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void loadImages(ArrayList<ImageData> images)
    {
        mImageGridAdapter = new ImageGridAdapter(MainActivity.this, images, mPresenter);
        mImageGrid.setAdapter(mImageGridAdapter);
        mImageGrid.setLayoutManager(new GridLayoutManager(MainActivity.this,
                2, GridLayoutManager.VERTICAL, false));
        mImageGrid.setHasFixedSize(true);
    }

    @Override
    public void showErrorMessage(String error)
    {
        hideProgress();
        Toast.makeText(MainActivity.this, error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyImageFetched(int position) {
        mImageGridAdapter.notifyItemChanged(position);
    }
}
