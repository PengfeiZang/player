package cn.com.tv.videoplayer.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zpf on 2015/11/27.
 */
@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView {


    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
