package com.imagepreviewdemo;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.imagepreviewdemo.view.ImagesPreView;
import com.imagepreviewdemo.view.ScaleImagView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_1,img_2,img_3;
    private FrameLayout backGround;
    private ImagesPreView preView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_1=(ImageView)findViewById(R.id.img_1);
        img_2=(ImageView)findViewById(R.id.img_2);
        img_3=(ImageView)findViewById(R.id.img_3);

        backGround=(FrameLayout)findViewById(R.id.bg);
        preView=(ImagesPreView)findViewById(R.id.preView);

        //设置图片资源
        img_1.setImageResource(R.drawable.timg_1);
        img_2.setImageResource(R.drawable.timg_2);
        img_3.setImageResource(R.drawable.timg_3);

        img_1.setOnClickListener(this);
        img_2.setOnClickListener(this);
        img_3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_1:
                showPreView(0);
                break;
            case R.id.img_2:
                showPreView(1);
                break;
            case R.id.img_3:
                showPreView(2);
                break;
        }
    }

    public void showPreView(int position){
        final ImagesPreView preView=(ImagesPreView)findViewById(R.id.preView);
        final FrameLayout backGround=(FrameLayout)findViewById(R.id.bg);
        //添加图片资源
        List<Integer>resources=new ArrayList<Integer>();
        resources.add(R.drawable.timg_1);
        resources.add(R.drawable.timg_2);
        resources.add(R.drawable.timg_3);
        //初始化
        preView.setResources(resources,getSupportFragmentManager());
        //设置当前显示的图片
        preView.setCurrentItem(position);
        //设置背景View,会随着图片向下滑动渐隐
        preView.setBackGroundView(backGround);
        //设置图片向下滑动事件监听
        preView.setOnScrollListener(new ScaleImagView.OnScrollListener() {
            @Override
            public void onScroll(float scroll) {
                //scroll值为向下滑动的距离和屏幕总高度百分比x100，值0~100之间
                if (scroll>50){
                    //当图片向下滑动的距离超过一50%的屏幕总高度时，设置打开松开手指时的事件监听
                    preView.setTouchUpEnable(true);
                }
            }
        });
        //松开手指时的事件监听，如果没有设置preView.setTouchUpEnable(true)，以下方法将不会被执行
        preView.setOnTouchUpListener(new ScaleImagView.OnTouchUpListener() {
            @Override
            public void onTouchUp() {
            }
        });
        //设置图片滑动监听
        preView.setOnImageChangeListener(new ImagesPreView.OnImageChangeListener() {
            @Override
            public void OnImageChange(int position) {

            }
        });
    }




}
