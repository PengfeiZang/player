package cn.com.tv.videoplayer.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by ${ZangPengfei} on 2017/11/26.
 */

public class UIUtils {

    private UIUtils() {

    }

    private static UIUtils instances;

    public static UIUtils getInstances() {
        return instances;
    }

    public static UIUtils getInstances(Context context) {
        if(instances==null){
            instances=new UIUtils(context);
        }
        return instances;
    }

    //基准宽高
    public static final int STANARD_WIDTH = 1280;
    public static final int STANARD_HEIGHT = 800;
    public static final String DIMEN_CLASS = "com.android.internal.R$dimen";

    //实际设备分辨率
    public float displayMetricsWidth;
    public float displayMetricsHeight;

    private UIUtils(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displayMetricsWidth == 0.0f || displayMetricsHeight == 0.0f) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            //状态栏高度
            int systemBarHeight = getSystemBarHeight(context);
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {//横屏
                this.displayMetricsWidth = (float) displayMetrics.heightPixels;
                this.displayMetricsHeight = (float) displayMetrics.widthPixels - systemBarHeight;
            } else {
                this.displayMetricsWidth = (float) displayMetrics.widthPixels;
                this.displayMetricsHeight = (float) displayMetrics.heightPixels - systemBarHeight;

            }
        }

    }

    private int getSystemBarHeight(Context context) {

        return getValue(context, DIMEN_CLASS, "system_bar_height", 48);
    }

    private int getValue(Context context, String dimenClass, String system_bar_height, int i) {

        try {
            Class e = Class.forName(dimenClass);
            Object obj = e.newInstance();
            Field field = e.getField(system_bar_height);
            int x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelOffset(x);
        } catch (Exception e1) {
            e1.printStackTrace();
            return i;
        }
    }

    //获取缩放以后的结果
    public float getWidth(float width) {
        return width * this.displayMetricsWidth / 1080.f;
    }

    public float getHeight(float height) {
        return height * this.displayMetricsHeight / 1872.f;
    }


}
