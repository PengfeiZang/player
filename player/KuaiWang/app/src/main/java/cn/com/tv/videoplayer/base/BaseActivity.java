package cn.com.tv.videoplayer.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseActivity extends BaseTranslucentActivity implements BaseViewInterface, View.OnClickListener {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        ButterKnife.bind(this);

        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
    }

    /**
     * 初始化布局
     */
    protected int getLayoutId() {
        return 0;
    }
}
