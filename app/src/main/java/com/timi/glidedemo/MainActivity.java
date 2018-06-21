package com.timi.glidedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.timi.imageloader.GlideApp;
import com.timi.imageloader.ImageLoaderProxy;
import com.timi.imageloader.transform.GlideRoundTransform;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_normal)
    ImageView ivNormal;
    @BindView(R.id.iv_circle)
    ImageView ivCircle;
    @BindView(R.id.iv_recentage)
    ImageView ivRecentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String url = "http://p0.so.qhimgs1.com/bdr/326__/t01ce51d6a2c5d6e214.jpg";
        ImageLoaderProxy.getInstance().displayCircleImage(this, url, ivCircle, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round);
        ImageLoaderProxy.getInstance().displayImage(this, url, ivNormal);
        ImageLoaderProxy.getInstance().displayRoundImage(this, url, ivRecentage, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round);
    }
}
