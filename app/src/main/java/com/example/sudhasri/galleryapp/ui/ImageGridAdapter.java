package com.example.sudhasri.galleryapp.ui;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sudhasri.galleryapp.R;
import com.example.sudhasri.galleryapp.data.model.ImageData;
import com.example.sudhasri.galleryapp.presenter.MainContract;

import java.util.ArrayList;

/**
 * Created by sudhasri on 19/8/16.
 */
public class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.ImageCardHolder>
{
    private MainContract.Presenter mPresenter;
    private ArrayList<ImageData> mDataList;
    private Context mContext;

    public ImageGridAdapter(Context context, ArrayList<ImageData> dataList, MainContract.Presenter presenter)
    {
        mContext = context;
        mDataList = dataList;
        mPresenter = presenter;
    }

    @Override
    public ImageCardHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image_grid, parent, false);
        ImageCardHolder holder = new ImageCardHolder(mContext, v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageCardHolder holder, int position) {
        ImageData imageData = mDataList.get(position);
        if (imageData.getBitmap() != null) {
            holder.mImageView.setImageBitmap(imageData.getBitmap());
        }
        else
        {
            mPresenter.getImage(imageData, position);
        }

        String likes = mContext.getString(R.string.likes) +
                imageData.getLikes();
        holder.mAttrText1.setText(likes);

        String views = mContext.getString(R.string.views) +
                imageData.getViews();
        holder.mAttrText2.setText(views);

        String comment = mContext.getString(R.string.comment) +
                imageData.getComments();
        holder.mAttrText3.setText(comment);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ImageCardHolder extends RecyclerView.ViewHolder
    {
        private Context mContext;
        private ImageView mImageView;
        private LinearLayout mBackLayout;
        private TextView mAttrText1, mAttrText2, mAttrText3;
        private boolean mFrontVisible = true;

        public ImageCardHolder(Context context, View itemView)
        {
            super(itemView);
            mContext = context;
            mImageView = (ImageView) itemView.findViewById(R.id.imageData);
            mBackLayout = (LinearLayout) itemView.findViewById(R.id.back_layout);
            mAttrText1 = (TextView) itemView.findViewById(R.id.attribute1);
            mAttrText2 = (TextView) itemView.findViewById(R.id.attribute2);
            mAttrText3 = (TextView) itemView.findViewById(R.id.attribute3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(mFrontVisible)
                    {
                        Animator rightIn =  AnimatorInflater.loadAnimator(mContext,
                                R.anim.flip_right_in);
                        rightIn.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);

                                mBackLayout.setVisibility(View.VISIBLE);
                                mBackLayout.setAlpha(1.0f);
                                mImageView.setVisibility(View.GONE);
                            }
                        });
                        Animator leftOut =AnimatorInflater.loadAnimator(mContext, R.anim.flip_left_out);
                        rightIn.setTarget(mBackLayout);
                        leftOut.setTarget(mImageView);
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(rightIn, leftOut);
                        set.start();
                        mFrontVisible = false;
                    }
                    else
                    {
                         Animator rightIn =  AnimatorInflater.loadAnimator(mContext,
                        R.anim.flip_right_in);
                        rightIn.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);

                                mImageView.setVisibility(View.VISIBLE);
                                mImageView.setAlpha(1.0f);
                                mBackLayout.setVisibility(View.GONE);
                            }
                        });
                        Animator leftOut =AnimatorInflater.loadAnimator(mContext, R.anim.flip_left_out);
                        rightIn.setTarget(mImageView);
                        leftOut.setTarget(mBackLayout);
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(rightIn, leftOut);
                        set.start();
                        mFrontVisible = true;
                    }
                }
            });
        }
    }
}
