package com.huailai.club.huailaiclub;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnTouchListener {
    private TextView[] mTabs;

    int screenWidth,screenHeight;
    int lastX,lastY;
    private LinearLayout mLlAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();



    }

    private void initView() {
        mTabs = new Button[4];
        mTabs[0] = (Button) findViewById(R.id.btn_home_tab);
        mTabs[1] = (Button) findViewById(R.id.btn_share);
        mTabs[2] = (Button) findViewById(R.id.btn_mess);
        mTabs[3] = (Button) findViewById(R.id.btn_profile);
        // 把第一个tab设为选中状态
        mTabs[0].setSelected(true);
        mLlAd = (LinearLayout)findViewById(R.id.ll_ad);
        mLlAd.setOnTouchListener(this);
        Display dis=this.getWindowManager().getDefaultDisplay();
        screenWidth=dis.getWidth();
        screenHeight=dis.getHeight();

    }

    public void onTabClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_home_tab:
                setCurrentTabSelected(0);
                break;
            case R.id.btn_share:
                setCurrentTabSelected(1);
                break;
            case R.id.btn_mess:
                setCurrentTabSelected(2);
                break;
            case R.id.btn_profile:
                setCurrentTabSelected(3);
                break;

        }
    }
    public void setCurrentTabSelected(int index) {
        int length=mTabs.length;
        for (int i = 0; i < length; i++) {
            if (i == index) {
                mTabs[i].setSelected(true);
            } else {
                mTabs[i].setSelected(false);
            }
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastX=(int)event.getRawX();
                lastY=(int)event.getRawY();

                break;

            case MotionEvent.ACTION_MOVE:
                int dx=(int)event.getRawX()-lastX;
                int dy=(int)event.getRawY()-lastY;

                int top=v.getTop()+dy;

                int left=v.getLeft()+dx;


                if(top<=0)
                {
                    top=0;
                }
                if(top>=screenHeight-mLlAd.getHeight())
                {
                    top=screenHeight-mLlAd.getHeight();
                }
                if(left>=screenWidth-mLlAd.getWidth())
                {
                    left=screenWidth-mLlAd.getWidth();
                }

                if(left<=0)
                {
                    left=0;
                }


                v.layout(left, top, left+mLlAd.getWidth(), top+mLlAd.getHeight());
                lastX=(int)event.getRawX();
                lastY=(int)event.getRawY();

                break;
            case MotionEvent.ACTION_UP:

                break;

        }

        return false;
    }
}
