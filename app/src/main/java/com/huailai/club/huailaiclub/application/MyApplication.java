package com.huailai.club.huailaiclub.application;

import android.app.Application;

import com.huailai.club.huailaiclub.utils.FileUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


/**
 * Created by ocean on 2017/4/18.
 */
public class MyApplication extends Application{
    public static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();

        instance=this;
        init();
    }

    private void init() {
        initImageLoader();


    }

    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).imageScaleType(ImageScaleType.NONE).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(instance).threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new UsingFreqLimitedMemoryCache((int) (Runtime.getRuntime().maxMemory() / 8)))
                .memoryCacheSizePercentage(13) // default
                .defaultDisplayImageOptions(defaultOptions)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                // .writeDebugLogs() // Remove for release app 日志log
                .diskCache(new UnlimitedDiscCache(FileUtils.getImageFile(instance))).build();

        ImageLoader.getInstance().init(config);

    }

    public static MyApplication getInstance() {
        return instance;
    }
}
