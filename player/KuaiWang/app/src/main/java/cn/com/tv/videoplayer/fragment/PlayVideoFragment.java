package cn.com.tv.videoplayer.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.Bind;
import cn.com.tv.videoplayer.R;
import cn.com.tv.videoplayer.base.BasePlayVideoFragment;
import cn.com.tv.videoplayer.base.CommonFragment;
import cn.com.tv.videoplayer.base.IMediaPlayerListener;
import cn.com.tv.videoplayer.dialog.PlayerSeekBar;
import cn.com.tv.videoplayer.media.AndroidMediaController;
import cn.com.tv.videoplayer.media.IjkVideoView;
import cn.com.tv.videoplayer.ui.MainActivity;
import cn.com.tv.videoplayer.utils.StringUtil;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by ${ZangPengfei} on 2017/10/31.
 */

public class PlayVideoFragment extends BasePlayVideoFragment {

    private MainActivity mainActivity;
    private boolean isFull = false;

    @Override
    public void initActivity() {
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void bigOrSmall() {
        if (isFull) {
            mainActivity.updateWidth(550, 300);
            go_back.setBackgroundResource(R.drawable.left);
            isFull = false;
        } else {
            go_back.setBackgroundResource(R.drawable.right);
            mainActivity.updateWidth(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            isFull = true;
        }
    }

}
