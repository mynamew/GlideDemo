package com.timi.glidedemo;

import android.support.v7.app.AppCompatActivity;

import com.timi.imageloader.ImageLoaderProxy;

/**
 * $dsc
 * author: timi
 * create at: 2018-06-21 10:16
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onDestroy() {
        ImageLoaderProxy.getInstance().onDestroy(this);
        super.onDestroy();
    }
}
