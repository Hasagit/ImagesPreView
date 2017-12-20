package com.imagepreviewdemo.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by DengJf on 2017/12/14.
 */

public class ScaleImagView extends android.support.v7.widget.AppCompatImageView {
    private int beginX,beginY;

    private int moveType;

    private float beforeScale;

    private int windowW=0,windowH=0,beginH=0,beginTop=0;

    private int i=0;

    private View backGround;

    private OnScrollListener scrollListener;

    public static boolean touchUpEnable;

    private Context context;

    private OnTouchUpListener onTouchUpListener;

    public ScaleImagView(Context context) {
        super(context);
        this.context=context;
    }

    public ScaleImagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;


    }

    public ScaleImagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        DisplayMetrics dm =getResources().getDisplayMetrics();
        if (i==0){
            windowW=dm.widthPixels;
            windowH=dm.heightPixels;
            beginH=getHeight();
            beginTop=getTop();
            //Log.e("top",beginH+"");
            i=1;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x= (int) event.getX();//获取触摸位置
        int y= (int) event.getY();
        //Log.e("w&h",windowW+"   "+beginH);
        //Log.e("get_w&h",getHeight()+"   "+getWidth());
        switch (event.getAction()&event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                //单点触摸
                //Log.e("place","down_1");
                beginX=x;
                beginY=y;
                moveType=1;
                touchUpEnable=false;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                //Log.e("place","down_2");
                //多点触摸
                moveType=2;
                beforeScale=getScaleLength(event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (moveType==1){
                    //计算拖动距离
                    int moveX=x-beginX;
                    int moveY=y-beginY;
                    if (scrollListener!=null){
                        scrollListener.onScroll((getTop()-beginTop)*100/windowH);
                        //Log.e("onScroll",(getTop()-beginTop)*100/windowH+"");
                    }

                    if (backGround!=null&&getWidth()==windowW){
                        float a=(getTop()-beginTop)*100/windowH;
                        float b=a/100;
                        if (b>0){
                            int c= (int) (b*100);
                            int alpha=(100-c)*0xff/100;
                            //Log.e("alpha",alpha+"   "+b);
                            backGround.getBackground().setAlpha(alpha);
                        }
                        //Log.e("onScroll",b+"");
                    }
                    layout(getLeft()+moveX,getTop()+moveY,getRight()+moveX,getBottom()+moveY);
                }else {
                    float afterScale=getScaleLength(event);
                    int scale= (int) (afterScale-beforeScale)/4;
                    if ((getRight()+scale-getLeft()+scale)<windowW){
                        layout(0,beginTop,windowW,beginTop+beginH);
                    }else {
                        layout(getLeft()-scale,getTop()-scale,getRight()+scale,getBottom()+scale);
                    }
                    if (scale>0){
                        layout(getLeft()-scale,getTop()-scale,getRight()+scale,getBottom()+scale);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (backGround!=null){
                    backGround.getBackground().setAlpha(0xff);
                }
                if (getWidth()==windowW){
                    layout(0,beginTop,windowW,beginTop+beginH);
                    if (touchUpEnable&&moveType==1){
                        if (onTouchUpListener!=null){
                            onTouchUpListener.onTouchUp();
                        }
                    }
                }
                moveType=1;
                break;
        }
        return true;
    }

    private float getScaleLength(MotionEvent event){
        try {
            //两个触摸点的坐标
            float x1 = event.getX();
            float y1 = event.getY();
            float x2 = event.getX(1);
            float y2 = event.getY(1);
            //利用勾股定理求得斜角边
            return (float) Math.sqrt(Math.pow((x1-x2), 2)+Math.pow((y1-y2), 2));
        }catch (IllegalArgumentException e){
            return beforeScale;
        }
    }

    public void setBackGround(View backGround) {
        this.backGround = backGround;
    }


    public void setOnScrollListener(OnScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    public interface OnScrollListener{
        void onScroll(float scroll);
    }

    public interface OnTouchUpListener{
        void onTouchUp();
    }


    public void setOnTouchUpListener(OnTouchUpListener onTouchUpListener) {
        this.onTouchUpListener = onTouchUpListener;
    }
}
