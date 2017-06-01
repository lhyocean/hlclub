package com.huailai.club.huailaiclub.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.huailai.club.huailaiclub.event.TestEvent;

import org.haitao.common.utils.AppLog;

import java.lang.reflect.Field;


import de.greenrobot.event.EventBus;


public abstract class BaseFragment extends Fragment {

    /**
     * 根元素
     */
    protected View mRootView;
    /**
     * 布局渲染工具
     */
    protected Context mContext;
    private String TAG = getClass().getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        mRootView = initRootView();


        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return mRootView;
    }


    public View getRootView() {
        return mRootView;
    }




    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setViews();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();



    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStart() {
        super.onStart();
    }


    /**
     * 初始化View,加载布局
     *
     * @return 布局
     */
    public abstract View initRootView();


    /**
     * 初始化数据
     */
    public abstract void setViews();

    /**
     * 加载网络数据
     */
    public abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
            AppLog.e("---","unregist-----EventBus"+ this.getClass().getSimpleName());
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public void onEventMainThread(TestEvent event) {

    }
}
