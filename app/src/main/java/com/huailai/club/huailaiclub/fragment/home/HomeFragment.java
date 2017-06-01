package com.huailai.club.huailaiclub.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huailai.club.huailaiclub.R;
import com.huailai.club.huailaiclub.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by ocean on 2017/5/4.
 */
public class HomeFragment extends BaseFragment{
    @Override
    public View initRootView() {
        mRootView=View.inflate(mContext, R.layout.fragment_home,null);
        return mRootView;
    }

    @Override
    public void setViews() {

    }

    @Override
    public void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
