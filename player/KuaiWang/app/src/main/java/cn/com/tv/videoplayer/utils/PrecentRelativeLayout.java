package cn.com.tv.videoplayer.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cn.com.tv.videoplayer.R;

/**
 * Created by ${ZangPengfei} on 2017/11/26.
 */

public class PrecentRelativeLayout extends RelativeLayout {
    public PrecentRelativeLayout(Context context) {
        super(context);
    }

    public PrecentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PrecentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PrecentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = View.MeasureSpec.getSize(heightMeasureSpec);

        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            int widthPrecent = 0;
            int heightPrecent = 0;
            if (layoutParams instanceof PrecentRelativeLayout.LayoutParams) {
                //获取到布局文件上的内容
                widthPrecent = (int) ((LayoutParams) layoutParams).getWidthPrecent();
                heightPrecent = (int) ((LayoutParams) layoutParams).getHeightPrecent();
            }

            if (widthPrecent > 0) {
                layoutParams.width = width * widthPrecent;
            }
            if (heightPrecent > 0) {
                layoutParams.height = width * heightPrecent;
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attr) {

        return new LayoutParams(getContext(), attr);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {

        private float widthPrecent;

        public float getWidthPrecent() {
            return widthPrecent;
        }

        public void setWidthPrecent(float widthPrecent) {
            this.widthPrecent = widthPrecent;
        }

        public float getHeightPrecent() {
            return heightPrecent;
        }

        public void setHeightPrecent(float heightPrecent) {
            this.heightPrecent = heightPrecent;
        }

        private float heightPrecent;

        //
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.PrecentRE);
            widthPrecent = array.getFloat(R.styleable.PrecentRE_layout_widthPrecent, 0);
            heightPrecent = array.getFloat(R.styleable.PrecentRE_layout_heightPrecent, 0);
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public LayoutParams(RelativeLayout.LayoutParams source) {
            super(source);
        }
    }
}
