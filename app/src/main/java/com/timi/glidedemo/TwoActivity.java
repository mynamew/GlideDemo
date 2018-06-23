package com.timi.glidedemo;

import android.os.Bundle;
import android.widget.ImageView;

import com.timi.imageloader.GlideTransType;
import com.timi.imageloader.ImageLoaderProxy;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TwoActivity extends BaseActivity {

    @BindView(R.id.iv_normal)
    ImageView ivNormal;
    @BindView(R.id.iv_circle)
    ImageView ivCircle;
    @BindView(R.id.iv_recentage)
    ImageView ivRecentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        ButterKnife.bind(this);
        String url = "http://p0.so.qhimgs1.com/bdr/326__/t01bd451ac2006cee54.jpg";
        String url1 = "http://p1.so.qhimgs1.com/bdr/326__/t011f7b7936e478dfdd.jpg";
        String url2= "http://p0.so.qhimgs1.com/bdr/326__/t01aaaed83f79397d5c.jpg";

        ImageLoaderProxy.getInstance().displayImage(this, url, ivCircle, GlideTransType.CIRCLE);
        ImageLoaderProxy.getInstance().displayImage(this, url1, ivNormal, GlideTransType.NORMAL);
        ImageLoaderProxy.getInstance().displayImage(this, url2, ivRecentage, GlideTransType.ROUND);
    }
}
