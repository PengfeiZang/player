package cn.com.tv.videoplayer.utils;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by ${ZangPengfei} on 2017/11/26.
 */

public class ValueCalculateUtil {
    //获取调用层传传入的值
    public static void setViewLinearLayoutParams(View view, int width, int height, int topMargin, int left, int right, int bottom) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (width != LinearLayout.LayoutParams.MATCH_PARENT || width != LinearLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.width = (int) UIUtils.getInstances(AppContext.getInstance()).getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != LinearLayout.LayoutParams.MATCH_PARENT || height != LinearLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.height = (int) UIUtils.getInstances(AppContext.getInstance()).getHeight(height);
        } else {
            layoutParams.height = height;
        }

        layoutParams.topMargin = (int) UIUtils.getInstances(AppContext.getInstance()).getHeight(topMargin);
        layoutParams.bottomMargin = (int) UIUtils.getInstances(AppContext.getInstance()).getHeight(bottom);
        layoutParams.leftMargin = (int) UIUtils.getInstances(AppContext.getInstance()).getWidth(left);
        layoutParams.rightMargin = (int) UIUtils.getInstances(AppContext.getInstance()).getWidth(right);

        view.setLayoutParams(layoutParams);
    }

    public static void setViewRelativeLayoutParams(View view, int width, int height, int topMargin, int left, int right, int bottom) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT || width != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.width = (int) UIUtils.getInstances(AppContext.getInstance()).getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT || height != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.height = (int) UIUtils.getInstances(AppContext.getInstance()).getHeight(height);
        } else {
            layoutParams.height = height;
        }

        layoutParams.topMargin = (int) UIUtils.getInstances(AppContext.getInstance()).getHeight(topMargin);
        layoutParams.bottomMargin = (int) UIUtils.getInstances(AppContext.getInstance()).getHeight(bottom);
        layoutParams.leftMargin = (int) UIUtils.getInstances(AppContext.getInstance()).getWidth(left);
        layoutParams.rightMargin = (int) UIUtils.getInstances(AppContext.getInstance()).getWidth(right);

        view.setLayoutParams(layoutParams);
    }
}
