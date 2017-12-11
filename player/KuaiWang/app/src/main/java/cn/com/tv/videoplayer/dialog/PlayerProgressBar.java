package cn.com.tv.videoplayer.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ProgressBar;

import cn.com.tv.videoplayer.R;

public class PlayerProgressBar extends ProgressBar {

    private static final String TAG = PlayerProgressBar.class.getName();
    // 记录点的颜色
    private int studentPointBackground, teacherPointBackground;
    // 记录点的宽度、高度
    private int pointWidth, pointHeight;
    private Paint pointPaint;

    public PlayerProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayerProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PlayerProgressBar);
        studentPointBackground = typedArray.getColor(R.styleable.PlayerProgressBar_Player_Student_Point_Background, Color.parseColor("#FFFFFF"));
        teacherPointBackground = typedArray.getColor(R.styleable.PlayerProgressBar_Player_Teacher_Point_Background, Color.parseColor("#FFFFFF"));
        pointWidth = typedArray.getDimensionPixelSize(R.styleable.PlayerProgressBar_Player_Point_Width, 10);
        pointHeight = typedArray.getDimensionPixelSize(R.styleable.PlayerProgressBar_Player_Point_Height, 10);
        typedArray.recycle();

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(pointHeight);
        pointPaint.setColor(studentPointBackground);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
//        TLog.analytics(TAG, "onDraw");
        super.onDraw(canvas);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * scale + 0.5f);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dpValue * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (pxValue / scale + 0.5f);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(pxValue / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }
}