package com.huailai.club.huailaiclub;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huailai.club.huailaiclub.base.BaseAcivity;
import com.huailai.club.huailaiclub.base.BaseFragment;
import com.huailai.club.huailaiclub.fragment.home.HomeFragment;
import com.huailai.club.huailaiclub.fragment.publish.PublishFragment;
import com.huailai.club.huailaiclub.fragment.search.EasySearchFragment;
import com.huailai.club.huailaiclub.fragment.user.UserCenterFragment;
import com.huailai.club.huailaiclub.utils.StatusBarUtil;

import org.haitao.common.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseAcivity implements View.OnTouchListener {
    private TextView[] mTabs;

    int screenWidth,screenHeight;
    int lastX,lastY;
    private LinearLayout mLlAd;
    List<BaseFragment> mFragmentList=new ArrayList<>();
    private RelativeLayout mLayoutTop;
    private long lastTime;
    private String mTAG="MainActivity";
    private ViewGroup.LayoutParams mLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.StatusBarLightMode(this);
        initView();
        initFragment();

    }

    private void initFragment() {
        if (mFragmentList!=null&&mFragmentList.size()==0){
            mFragmentList.add(new HomeFragment());
            mFragmentList.add(new PublishFragment());
            mFragmentList.add(new EasySearchFragment());
            mFragmentList.add(new UserCenterFragment());
        }
        if (mFragmentList!=null&&mFragmentList.size()>0){
            this.switchFragment(mFragmentList.get(0));
        }
    }

    private void initView() {
        mTabs = new Button[4];
        mTabs[0] = (Button) findViewById(R.id.btn_home_tab);
        mTabs[1] = (Button) findViewById(R.id.btn_share);
        mTabs[2] = (Button) findViewById(R.id.btn_mess);
        mTabs[3] = (Button) findViewById(R.id.btn_profile);
        mLayoutTop= (RelativeLayout) findViewById(R.id.main_top);

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
                switchFragment(mFragmentList.get(0));
                break;
            case R.id.btn_share:
                switchFragment(mFragmentList.get(1));
                break;
            case R.id.btn_mess:
                switchFragment(mFragmentList.get(2));
                break;
            case R.id.btn_profile:
                switchFragment(mFragmentList.get(3));
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

        hideOrShowTop(index);

    }

    private void hideOrShowTop(int index) {
        if (index==0||index==2){
            if (mLayoutTop!=null){
                mLayoutTop.setVisibility(View.VISIBLE);
            }
        }else {
            if (mLayoutTop!=null){
                mLayoutTop.setVisibility(View.GONE);
            }
        }
    }

    public void switchFragment(BaseFragment currentFragment) {

        AppLog.i("tag", "currentFragment 名称 " + currentFragment.getClass().getSimpleName());
        String fragmentName = currentFragment.getClass().getSimpleName();
        if (fragmentName != null) {
            if (fragmentName.equals("HomeFragment")) {
                setCurrentTabSelected(0);
            } else if (fragmentName.equals("PublishFragment")) {
                setCurrentTabSelected(1);
            } else if (fragmentName.equals("EasySearchFragment")) {
                setCurrentTabSelected(2);
            } else if (fragmentName.equals("UserCenterFragment")) {
                setCurrentTabSelected(3);
            }
        }

        try {
            FragmentTransaction transaction = this.getSupportFragmentManager()
                    .beginTransaction();

            transaction.replace(R.id.fragment_container,
                    currentFragment, currentFragment.getClass().getSimpleName());
            transaction.addToBackStack(currentFragment.getClass().getSimpleName());

            transaction.commitAllowingStateLoss();

        } catch (Exception e) {
            e.printStackTrace();
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
                int dx1=(int)event.getRawX()-lastX;
                int dy1=(int)event.getRawY()-lastY;

                int top1=v.getTop()+dy1;

                int left1=v.getLeft()+dx1;


                if(top1<=0)
                {
                    top1=0;
                }
                if(top1>=screenHeight-mLlAd.getHeight())
                {
                    top1=screenHeight-mLlAd.getHeight();
                }
                if(left1>=screenWidth-mLlAd.getWidth())
                {
                    left1=screenWidth-mLlAd.getWidth();
                }

                if(left1<=0)
                {
                    left1=0;
                }
                if (lastX<screenWidth/2){

                    v.layout(0, top1, 0+mLlAd.getWidth(), top1+mLlAd.getHeight());
                }else {
                    v.layout(screenWidth-mLlAd.getWidth(), top1, screenWidth, top1+mLlAd.getHeight());

                }
                mLayoutParams = v.getLayoutParams();
                lastX=(int)event.getRawX();
                lastY=(int)event.getRawY();
                break;
        }

        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            String currentFragmet = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();


            AppLog.i("MainActivity", "------currentFragmet" + currentFragmet);
            if ((("HomeFragment".equals(currentFragmet)) || "PublishFragment".equals(currentFragmet)
                    || "EasySearchFragment".equals(currentFragmet) || "UserCenterFragment".equals(currentFragmet))) {



                long time = System.currentTimeMillis();//当前点击按键时间
                if ((time - lastTime) > 3000) {
                    Toast.makeText(this, "双击退出", Toast.LENGTH_SHORT).show();

                } else {
                    //小于1.5s可以退出应用程序
                    finish();
//                    System.exit(0);
                }
                lastTime = time;
                return true;
            } else {

                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    getSupportFragmentManager().popBackStack();
                } else {

                    finish();

                }
                return true;
            }


        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e(mTAG, "onResume: " );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(mTAG, "onRestart: " );
    }



    }
