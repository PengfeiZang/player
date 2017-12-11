package cn.com.tv.videoplayer.fragment;

import android.view.View;
import android.widget.RelativeLayout;

import cn.com.tv.videoplayer.R;
import cn.com.tv.videoplayer.base.BasePlayVideoFragment;
import cn.com.tv.videoplayer.ui.LivePlayVideoActivity;
import cn.com.tv.videoplayer.ui.MainActivity;

/**
 * Created by ${ZangPengfei} on 2017/10/31.
 */

public class LivePlayVideoFragment extends BasePlayVideoFragment {

    private LivePlayVideoActivity mLivePlayVideoActivity;

    @Override
    public void initActivity() {
        mLivePlayVideoActivity = (LivePlayVideoActivity) getActivity();
    }

    boolean isFull = false;

    @Override
    public void bigOrSmall() {

        if(isFull){
            isFull = false;
            mLivePlayVideoActivity.mRecyclerView.setVisibility(View.VISIBLE);
            go_back.setBackgroundResource(R.drawable.left);
        }else{
            isFull = true;
            go_back.setBackgroundResource(R.drawable.right);
            mLivePlayVideoActivity.mRecyclerView.setVisibility(View.GONE);
        }

    }

}
