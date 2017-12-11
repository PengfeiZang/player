package cn.com.tv.videoplayer.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import cn.com.tv.videoplayer.R;
import cn.com.tv.videoplayer.base.BaseActivity;
import cn.com.tv.videoplayer.base.BasePlayVideoFragment;
import cn.com.tv.videoplayer.bean.Constants;
import cn.com.tv.videoplayer.bean.LiveListBean;
import cn.com.tv.videoplayer.bean.LoginScussBean;
import cn.com.tv.videoplayer.bean.RecordListBean;
import cn.com.tv.videoplayer.fragment.LivePlayVideoFragment;
import cn.com.tv.videoplayer.fragment.PlayVideoFragment;
import cn.com.tv.videoplayer.okhttp.CallBackUtil;
import cn.com.tv.videoplayer.okhttp.OkhttpUtil;
import cn.com.tv.videoplayer.utils.AppContext;
import cn.com.tv.videoplayer.utils.StringUtil;
import cn.com.tv.videoplayer.utils.ToastUtil;
import cn.com.tv.videoplayer.widget.EmptyLayout;
import okhttp3.Call;

/**
 * Created by ${ZangPengfei} on 2017/11/24.
 */

public class LivePlayVideoActivity extends BaseActivity {

    private String TAG = "LivePlayVideoActivity";

    private List<LiveListBean.DataBean> mList;
    private MyQuickAdapter myQuickAdapter;
    private LivePlayVideoFragment playVideoFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Bind(R.id.video_contener)
    AutoFrameLayout mContainer;
    @Bind(R.id.recyclerview)
    public RecyclerView mRecyclerView;
    @Bind(R.id.empty_layout)
    EmptyLayout mEmpty;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_video;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {

        fragmentManager = getFragmentManager();
        if (null == playVideoFragment) {
            playVideoFragment = new LivePlayVideoFragment();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.video_contener, playVideoFragment).commit();
            playVideoFragment.isLive = true;
        }

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        optionRecyclerViewAddOnItemClickListener();

        showNetworkLoading();
        connGetData();
    }

    private void connGetData() {

        final String url = AppContext.get(Constants.KEY_BASE_URL, "") + "api/live/list";
        Log.d(TAG, "url = " + url);

        Map<String, String> paramsMap = new WeakHashMap<>();
        paramsMap.put("username", AppContext.get(Constants.KEY_USERNAME, ""));
        paramsMap.put("token", AppContext.get(Constants.KEY_TOKEN, ""));

        OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                showNetworkLoadingError();
                mEmpty.setOnLayoutClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showNetworkLoading();
                        connGetData();
                    }
                });

            }

            @Override
            public void onResponse(String response) {
                try {

                    LiveListBean listBean = StringUtil.jsonToObject(response, LiveListBean.class);

                    if (listBean.getCode() == 200) {
                        mList = listBean.getData();
                        playVideoFragment.handleRequestPlayInfoDataSuccess(mList.get(0).getOutput());
                        playVideoFragment.go_back.setVisibility(View.GONE);
                        showUI();
                        updateUI();

                    } else {
                        showServerError();
                        mEmpty.setOnLayoutClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showNetworkLoading();
                                connGetData();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    showServerError();
                    mEmpty.setOnLayoutClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showNetworkLoading();
                            connGetData();
                        }
                    });
                }


                Log.d(TAG, response);
            }
        });


    }

    private void updateUI() {
        if (null == myQuickAdapter) {
            myQuickAdapter = new MyQuickAdapter();
            myQuickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
            myQuickAdapter.isFirstOnly(true);
            mRecyclerView.setAdapter(myQuickAdapter);
        } else {
//            myQuickAdapter.setNewData(mTeaQueEnty.getAnswer());
            myQuickAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }

    }

    public class MyQuickAdapter extends BaseQuickAdapter<LiveListBean.DataBean, BaseViewHolder> {
        public MyQuickAdapter() {
            super(R.layout.list_item_play, mList);
        }

        @Override
        protected void convert(BaseViewHolder helper, LiveListBean.DataBean item) {
            helper.setText(R.id.name, item.getChannel());
            AutoUtils.autoSize(helper.convertView);
        }
    }

    private void optionRecyclerViewAddOnItemClickListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                playVideoFragment.handleRequestPlayInfoDataSuccess(mList.get(i).getOutput());
            }
        });
    }

    public void showNetworkLoading() {
        if (mEmpty != null) {
            mEmpty.setErrorType(EmptyLayout.NETWORK_LOADING);
        }
    }

    public void showUI() {
        if (mEmpty != null) {
            mEmpty.dismiss();
        }
    }

    public void showNetworkLoadingError() {
        if (mEmpty != null) {
            mEmpty.setErrorType(EmptyLayout.NETWORK_ERROR);
        }
    }

    public void showServerError() {
        if (mEmpty != null) {
            mEmpty.setErrorType(EmptyLayout.SERVER_ERROR);
        }
    }


}
