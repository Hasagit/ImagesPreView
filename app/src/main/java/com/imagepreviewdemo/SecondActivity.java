package com.imagepreviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.imagepreviewdemo.view.ImagesPreView;
import com.imagepreviewdemo.view.ScaleImagView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private FrameLayout backGround;
    private ImagesPreView preView;
    private List<Integer>resources;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        preView=(ImagesPreView)findViewById(R.id.preView);
        backGround=(FrameLayout)findViewById(R.id.bg);


        Intent intent=getIntent();
        resources=intent.getIntegerArrayListExtra("resources");
        position=intent.getIntExtra("position",0);

        preView.setResources(resources,getSupportFragmentManager());
        preView.setBackGroundView(backGround);
        preView.setOnScrollListener(new ScaleImagView.OnScrollListener() {
            @Override
            public void onScroll(float scroll) {
                //scroll为图片向下滑动的距离和屏幕总高度百分比x100,值为0~100之间
                if (scroll>40){
                    //preView.setUpFinish(true);
                }
            }
        });


    }
}
