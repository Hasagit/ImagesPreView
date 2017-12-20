package com.imagepreviewdemo.view;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by DengJf on 2017/12/19.
 */

public class ImagesPreView extends ViewPager{
    private ViewPagerAdapter adapter;
    public ImagesPreView(Context context) {
        super(context);
    }

    public ImagesPreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setResources(List<Integer> resources, FragmentManager manager){
        adapter=null;
        adapter=new ViewPagerAdapter(manager);
        for (int i=0;i<resources.size();i++){
            PreViewFragment fragment=new PreViewFragment();
            adapter.addFragment(fragment);
        }
        setAdapter(adapter);
        setOffscreenPageLimit(0);
        for (int i=0;i<adapter.getCount();i++){
            adapter.getItem(i).setImgResources(resources.get(i));
        }
    }

    public void setFilesOrUrl(List<String> fileOrUrl, FragmentManager manager){
        adapter=new ViewPagerAdapter(manager);
        for (int i=0;i<fileOrUrl.size();i++){
            PreViewFragment fragment=new PreViewFragment();
            fragment.setUrl_file(fileOrUrl.get(i));
            adapter.addFragment(fragment);
        }
        setAdapter(adapter);
    }

    public void setOnScrollListener(ScaleImagView.OnScrollListener listener){
        for (int i=0;i<adapter.getCount();i++){
            adapter.getItem(i).setOnScrollListener(listener);
        }
    }

    public void setBackGroundView(View view){
        for (int i=0;i<adapter.getCount();i++){
            adapter.getItem(i).setBackGroundView(view);
        }
    }

    public void setTouchUpEnable(boolean upFinish){
        for (int i=0;i<adapter.getCount();i++){
            adapter.getItem(i).setTouchUpEnable(upFinish);
        }
    }

    public void setOnTouchUpListener(ScaleImagView.OnTouchUpListener listener){
        for (int i=0;i<adapter.getCount();i++){
            adapter.getItem(i).setOnTouchUpListener(listener);
        }
    }

    public void setOnImageChangeListener(final OnImageChangeListener listener){
        setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                listener.OnImageChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public interface OnImageChangeListener{
        void OnImageChange(int position);
    }
}
