package com.timi.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.timi.imageloader.transform.GlideCircleTransform;
import com.timi.imageloader.transform.GlideRoundTransform;

public class GlideImageLoader implements ImageLoader {
    private GlideRequests glideRequests;

    @Override
    public void init(Context context) {
        if (null == glideRequests) {
            glideRequests = GlideApp.with(context);
        }
    }

    @Override
    public void displayImage(Context context, String url, ImageView imageView, int defaultImage) {
        if (null == glideRequests) {
            init(context);
        }
        glideRequests.load(url)
                .centerCrop()
                .placeholder(defaultImage)
                .into(imageView);
    }

    @Override
    public void displayImage(Context context, String url, ImageView imageView) {
        if (null == glideRequests) {
            init(context);
        }
        glideRequests.load(url)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void displayImage(Context context, String url, ImageView imageView, int defaultImage, int errorImage) {
        if (null == glideRequests) {
            init(context);
        }
        glideRequests.load(url)
                .centerCrop()
                .placeholder(defaultImage)
                .error(errorImage)
                .into(imageView);
    }

    @Override
    public void displayCircleImage(Context context, String url, ImageView imageView, int defaultImage, int errorImage) {
        if (null == glideRequests) {
            init(context);
        }
        glideRequests.load(url)
                .centerCrop()
                .placeholder(defaultImage)
                .error(errorImage)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    @Override
    public void displayRoundImage(Context context, String url, ImageView imageView, int defaultImage, int errorImage) {
        if (null == glideRequests) {
            init(context);
        }
        glideRequests.load(url)
                .centerCrop()
                .placeholder(defaultImage)
                .error(errorImage)
                .transform(new GlideRoundTransform(context))
                .into(imageView);
    }

    @Override
    public void onDestroy(Context context) {
        if (context == null) {
            throw new RuntimeException("context is not null");
        }
        if (glideRequests != null) {
            glideRequests.resumeRequests();
        }
    }

    private void finishGlide() {
        if (glideRequests != null) {
            glideRequests.resumeRequests();
        }
    }

}