package com.timi.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.timi.imageloader.transform.GlideCircleTransform;
import com.timi.imageloader.transform.GlideRoundTransform;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
/**
  * Glide 实现类
  * author: timi
  * create at: 2018/6/21 9:15
  */
public class GlideImageLoader implements ImageLoader {
    GlideRequests mGlideRequst;
    @Override
    public void init(Context context) {
        if(null==mGlideRequst)
            mGlideRequst=GlideApp.with(context);
    }

    @Override
    public void displayImage(Context context, String url, final ImageView imageView, int defaultImage) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        if(null==mGlideRequst){
            init(context);
        }
        resumeGlide();
        mGlideRequst.load(url)
                .centerCrop()
                .placeholder(defaultImage)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    @Override
    public void displayImage(Context context, String url, final ImageView imageView) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        if(null==mGlideRequst){
            init(context);
        }
        resumeGlide();
        mGlideRequst.load(url)
                .transition(withCrossFade())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    @Override
    public void displayImage(Context context, String url, final ImageView imageView, int defaultImage, int errorImage) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        if(null==mGlideRequst){
            init(context);
        }
        resumeGlide();
        mGlideRequst.load(url)
                .transition(withCrossFade())
                .placeholder(defaultImage)
                .error(errorImage)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    @Override
    public void displayCircleImage(Context context, String url, final ImageView imageView, int defaultImage, int errorImage) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        if(null==mGlideRequst){
            init(context);
        }
        resumeGlide();
        mGlideRequst.load(url)
                .transition(withCrossFade())
                .placeholder(defaultImage)
                .error(errorImage)
                .transform(new GlideCircleTransform(context))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    @Override
    public void displayRoundImage(Context context, String url, final ImageView imageView, int defaultImage, int errorImage) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        if(null==mGlideRequst){
            init(context);
        }
        resumeGlide();
        mGlideRequst.load(url)
                .transition(withCrossFade())
                .placeholder(defaultImage)
                .error(errorImage)
                .transform(new GlideRoundTransform(context))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    @Override
    public void onDestroy(Context context) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        if (mGlideRequst != null) {
            mGlideRequst.pauseRequests();
        }
    }

    private void resumeGlide(){
        if (mGlideRequst != null) {
            mGlideRequst.resumeRequests();
        }
    }
}