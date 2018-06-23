package com.timi.glidedemo;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.timi.imageloader.GlideTransType;
import com.timi.imageloader.ImageLoaderProxy;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_normal)
    ImageView ivNormal;
    @BindView(R.id.iv_circle)
    ImageView ivCircle;
    @BindView(R.id.iv_recentage)
    ImageView ivRecentage;

    private final int REQUEST_CODE_CHOOSE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /**
         * 权限申请
         */
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(Manifest.permission.CAMERA)
                .subscribe(permission -> {
                    if (permission.granted) {
                        Log.d("Rxpermission", permission.name + " is granted.");
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        Log.d("Rxpermission", permission.name + " is denied. More info should be provided.");
                    } else {
                        Log.d("Rxpermission", permission.name + " is denied.");
                    }
                });


        String url = "http://p0.so.qhimgs1.com/bdr/326__/t01ce51d6a2c5d6e214.jpg";
        String url1 = "http://pic1.win4000.com/wallpaper/4/53d71b942a18a.jpg";
        String url2= "http://image.machine35.com/userpic/58021/20126215173763.jpg";
        ImageLoaderProxy.getInstance().displayImage(this, url, ivCircle, GlideTransType.CIRCLE);
        ImageLoaderProxy.getInstance().displayImage(this, url1, ivNormal, GlideTransType.NORMAL);
        ImageLoaderProxy.getInstance().displayImage(this, url2, ivRecentage, GlideTransType.ROUND);
    }

    public void jumpTwoAct(View v) {
        startActivity(new Intent(this, TwoActivity.class));
    }

    /**
     * 选择相片
     *
     * @param v
     */
    public void selectImg(View v) {
        Matisse.from(MainActivity.this)
                .choose(MimeType.ofAll(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider"))
                .maxSelectable(1)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
//                                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .setOnSelectedListener(new OnSelectedListener() {
                    @Override
                    public void onSelected(
                            @NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                        // DO SOMETHING IMMEDIATELY HERE
                        Log.e("onSelected", "onSelected: pathList=" + pathList);

                    }
                })
                .originalEnable(true)
                .maxOriginalSize(10)
                .setOnCheckedListener(new OnCheckedListener() {
                    @Override
                    public void onCheck(boolean isChecked) {
                        // DO SOMETHING IMMEDIATELY HERE
                        Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                    }
                })
                .forResult(REQUEST_CODE_CHOOSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<String> paths = Matisse.obtainPathResult(data);
            ImageLoaderProxy.getInstance().displayLocalImage(this, paths.get(0), ivCircle, GlideTransType.CIRCLE);
        }
    }
}
