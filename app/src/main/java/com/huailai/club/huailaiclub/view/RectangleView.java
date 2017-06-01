package com.huailai.club.huailaiclub.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by lenovo on 2017/3/22.
 */
public class RectangleView extends View{

    private Context context;
    public RectangleView(Context context) {
        super(context);
        this.context=context;
    }

    public RectangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public RectangleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    public void setHeightRate(float rate){
        int width = getScreenWidth(context);
        int height =(int)(width*rate) ;
        getLayoutParams().height=height;
    }

    /**
     * 获取屏幕宽度
     * @return the int
     */
    public  int getScreenWidth(Context activity) {
        // 获取屏幕密度（方法2）
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        return  dm.widthPixels;      // 屏幕宽（像素，如：480px）
    }

}
