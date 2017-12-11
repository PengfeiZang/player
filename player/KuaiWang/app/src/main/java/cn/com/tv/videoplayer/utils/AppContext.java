package cn.com.tv.videoplayer.utils;

import com.zhy.autolayout.config.AutoLayoutConifg;

import cn.com.tv.videoplayer.base.BaseApplication;

public class AppContext extends BaseApplication {

    private static AppContext instance;

    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();
        instance = this;
    }
}
