package cn.com.tv.videoplayer.utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.tv.videoplayer.R;

public class ToastUtil {

    private static Toast lastToast;
    private static String lastToastMessage = "";
    private static long lastToastTime;


    public static void showToast(int message) {
        showToast(message, Toast.LENGTH_SHORT, 0);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
    }

    public static void showToast(int message, int icon) {
        showToast(message, Toast.LENGTH_SHORT, icon);
    }

    public static void showToast(String message, int icon) {
        showToast(message, Toast.LENGTH_SHORT, icon, Gravity.BOTTOM);
    }

    public static void showToastLong(int message) {
        showToast(message, Toast.LENGTH_SHORT, 0);
    }

    public static void showToastLong(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
    }

    public static void showToastLong(int message, Object... args) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM, args);
    }

    public static void showToast(int message, int duration, int icon) {
        showToast(message, duration, icon, Gravity.BOTTOM);
    }

    public static void showToast(int message, int duration, int icon,
                                 int gravity) {
        showToast(AppContext.getInstance().getString(message), duration, icon, gravity);
    }

    public static void showToast(int message, int duration, int icon,
                                 int gravity, Object... args) {
        showToast(AppContext.getInstance().getString(message, args), duration, icon, gravity);
    }

    public static void showToast(String message, int duration, int icon,
                                 int gravity) {
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToastMessage)
                    || Math.abs(time - lastToastTime) > 2000) {
                View view = LayoutInflater.from(AppContext.getInstance()).inflate(
                        R.layout.view_toast, null);
                ((TextView) view.findViewById(R.id.tv_title)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.iv_icon))
                            .setImageResource(icon);
                    ((ImageView) view.findViewById(R.id.iv_icon))
                            .setVisibility(View.VISIBLE);
                } else {
                    ((ImageView) view.findViewById(R.id.iv_icon))
                            .setVisibility(View.GONE);
                }
                if (lastToast != null) lastToast.cancel();
                Toast toast = new Toast(AppContext.getInstance());
                toast.setView(view);
                if (gravity == Gravity.CENTER) {
                    toast.setGravity(gravity, 0, 0);
                } else {
                    toast.setGravity(gravity, 0, (int) TDevice.dpToPixel(60));
                }

                toast.setDuration(duration);
                toast.show();
                lastToast = toast;
                lastToastMessage = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }
}
