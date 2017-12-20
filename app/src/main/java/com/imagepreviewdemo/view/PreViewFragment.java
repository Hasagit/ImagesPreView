package com.imagepreviewdemo.view;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.imagepreviewdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreViewFragment extends Fragment {
    private ScaleImagView img;
    private int imgResources=0;
    private String url_file;
    private View backGroundView;
    private ScaleImagView.OnScrollListener listener;
    private boolean touchUpEnable=false;
    private ScaleImagView.OnTouchUpListener onTouchUpListener;


    public void setImgResources(int imgResources) {
        this.imgResources=imgResources;
        url_file=null;
        if (img!=null){
            Glide.with(getContext()).load(imgResources).into(img);
        }
    }

    public void setUrl_file(String url_file) {
        this.url_file=url_file;
        imgResources=0;
        if (img!=null){
            Glide.with(getContext()).load(url_file).into(img);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pre_view, container, false);
        img=(ScaleImagView)view.findViewById(R.id.scale_img);
        if (imgResources!=0){
            Glide.with(getContext()).load(imgResources).into(img);
        }
        if (url_file!=null){
            Glide.with(getContext()).load(url_file).into(img);
        }
        if (listener!=null){
            img.setOnScrollListener(listener);
        }
        if (backGroundView!=null){
            img.setBackGround(backGroundView);
        }
        if (onTouchUpListener!=null){
            img.setOnTouchUpListener(onTouchUpListener);
        }
        ScaleImagView.touchUpEnable=touchUpEnable;
        return view;
    }



    public void setOnScrollListener(ScaleImagView.OnScrollListener listener){
        this.listener=listener;
        if (img!=null){
            img.setOnScrollListener(listener);
        }
    }

    public void setOnTouchUpListener(ScaleImagView.OnTouchUpListener onTouchUpListener) {
        this.onTouchUpListener = onTouchUpListener;
        if (img!=null){
            img.setOnTouchUpListener(onTouchUpListener);
        }
    }

    public void setBackGroundView(View backGroundView){
        this.backGroundView=backGroundView;
        if (img!=null){
            img.setBackGround(backGroundView);
        }
    }

    public void setTouchUpEnable(boolean touchUpEnable) {
        this.touchUpEnable = touchUpEnable;
        ScaleImagView.touchUpEnable=touchUpEnable;
    }
}
