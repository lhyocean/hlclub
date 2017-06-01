package com.huailai.club.huailaiclub.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;

import com.huailai.club.huailaiclub.event.TestEvent;

import org.haitao.common.utils.AppLog;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by lyq on 2016/5/16.
 */
public abstract class BaseAcivity extends FragmentActivity {

    private String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }




    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
            AppLog.e("---","unregist-----EventBus"+ this.getClass().getSimpleName());
        }
    }

    public void onEventMainThread(TestEvent event) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = supportFragmentManager.getFragments();
        if(fragments!=null && fragments.size()==1){
            Fragment fragment = fragments.get(0);
            fragment.onActivityResult(requestCode,resultCode,data);
        }
    }
}
