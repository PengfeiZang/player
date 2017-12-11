package cn.com.tv.videoplayer.base;

import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoRelativeLayout;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import cn.com.tv.videoplayer.R;
import cn.com.tv.videoplayer.bean.AdTextBean;
import cn.com.tv.videoplayer.bean.Constants;
import cn.com.tv.videoplayer.bean.LiveListBean;
import cn.com.tv.videoplayer.dialog.PlayerSeekBar;
import cn.com.tv.videoplayer.media.AndroidMediaController;
import cn.com.tv.videoplayer.media.IjkVideoView;
import cn.com.tv.videoplayer.okhttp.CallBackUtil;
import cn.com.tv.videoplayer.okhttp.OkhttpUtil;
import cn.com.tv.videoplayer.ui.LivePlayVideoActivity;
import cn.com.tv.videoplayer.ui.MainActivity;
import cn.com.tv.videoplayer.utils.AppContext;
import cn.com.tv.videoplayer.utils.DateTimeUtil;
import cn.com.tv.videoplayer.utils.MyTextView;
import cn.com.tv.videoplayer.utils.StringUtil;
import okhttp3.Call;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by ${ZangPengfei} on 2017/10/31.
 */

public abstract class BasePlayVideoFragment extends CommonFragment implements IMediaPlayerListener {

    private String PLAYER_TAG = "BasePlayVideoFragment";
    private String adContent;
    public boolean isLive = false;

    //    private String url5 = "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";
//    private String url5 = "http://122.188.6.5:8082/live//9/index.m3u8";
//    private String url5 = "http://122.188.6.5:8083//电影/测试/dongwu.mp4";
    private String url5 = "http://la.sdiread.cn/o_1as1jp3qimkhhuo1o54sve1nlt9.mp4";

    private String path;

    @Bind(R.id.video_view)
    IjkVideoView mVideoView;

    @Bind(R.id.rl_video)
    RelativeLayout rl_video;

    @Bind(R.id.go_back)
    public ImageButton go_back;

    // 首次加载界面View
    @Bind(R.id.first_view_play_video_loading)
    ViewGroup viewFirstLoading;

    // 加载界面View
    @Bind(R.id.view_play_video_loading)
    ViewGroup viewLoading;
    // 加载失败界面View
    @Bind(R.id.view_play_video_loading_failed)
    AutoRelativeLayout viewLoadingFailed;
    @Bind(R.id.btn_loading_failed_reload)
    ImageButton btnLoadingFailedReload;

    // Bottom Tool View
    @Bind(R.id.rl_bottombar)
    public AutoRelativeLayout rlBottombar;
    @Bind(R.id.view_play_video_seekbar)
    ViewGroup viewPlayVideoSeekbar;
    @Bind(R.id.seekbar_video)
    PlayerSeekBar seekbarVideo;
    @Bind(R.id.tv_video_current_time)
    TextView tvVideoCurrentTime;
    @Bind(R.id.tv_video_duration)
    TextView tvVideoDuration;
    @Bind(R.id.iv_play)
    ImageView ivPlay;
    @Bind(R.id.tv_ad)
    MyTextView tvAd;

    private AndroidMediaController mMediaController;
    private boolean mBackPressed;
    private AnimationDrawable animationDrawable;
    private final int deleyTime = 3000;
    private static final int MAX_PROGRESS_VALUE = 1000; // 进度最大值
    private int mDuration; // 视频总时长
    private int mPosition; // 视频当前播放时长

    @Override
    protected int getLayoutId() {
        return R.layout.play_video_layout_fragment;
    }

    @Override
    public void initView(View v) {

        rlBottombar.setVisibility(isLive ? View.GONE : View.VISIBLE);

        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnErrorListener(this);

        rl_video.setOnClickListener(this);
        go_back.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        btnLoadingFailedReload.setOnClickListener(this);

        seekbarVideo.setMax(MAX_PROGRESS_VALUE);
        seekbarVideo.setOnSeekBarChangeListener(mOnSeekBarChangeListener);

//        showFirstLoadingView();
//        handleRequestPlayInfoDataSuccess(url5);
    }

    public abstract void initActivity();

    public abstract void bigOrSmall();

    @Override
    public void initData() {

        initActivity();

        initPlayer();

        mMediaController = new AndroidMediaController(getApplication(), true);

        mVideoView.setMediaController(mMediaController);

        getAD();
    }

    private void getAD() {

        final String url = AppContext.get(Constants.KEY_BASE_URL, "") + "api/app/caption";
        Log.d(PLAYER_TAG, "url = " + url);

        Map<String, String> paramsMap = new WeakHashMap<>();
        paramsMap.put("username", AppContext.get(Constants.KEY_USERNAME, ""));
        paramsMap.put("token", AppContext.get(Constants.KEY_TOKEN, ""));

        OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
//                Toast.makeText(LivePlayVideoActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {

                    AdTextBean listBean = StringUtil.jsonToObject(response, AdTextBean.class);

                    if (listBean.getCode() == 200) {
                        adContent = listBean.getData().get(0).getContent();
                        startTime = DateTimeUtil.getDateLongByPattern(listBean.getData().get(0).getStartTime(), DateTimeUtil.timeFormaterNoSecond);
                        endTime = DateTimeUtil.getDateLongByPattern(listBean.getData().get(0).getEndTime(), DateTimeUtil.timeFormaterNoSecond);
                        handler.sendEmptyMessage(SHOW_AD);
                    } else {

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                Log.d(PLAYER_TAG, response);
            }
        });

    }

    private long startTime;
    private long endTime;

    private final SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(final SeekBar seekBar, final int progress, boolean fromUser) {
            if (!fromUser) {
                return;
            }
            mPosition = (int) (Float.valueOf(progress) / MAX_PROGRESS_VALUE * mDuration);
            tvVideoCurrentTime.setText(StringUtil.stringForTime(mPosition));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.e(PLAYER_TAG, "onStartTrackingTouch()");
            mPlayerState = PLAYER_STATE_DRAGGING;
            removeUpdateProgressMsg();
            handler.removeMessages(HIDESHOW);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.e(PLAYER_TAG, "onStopTrackingTouch()");
            // 预览图隐藏
            mPlayerState = PLAYER_STATE_PLAYING;
            Log.e(PLAYER_TAG, "seekTo mPosition = " + mPosition);
            mVideoView.seekTo(mPosition);
            handler.removeMessages(HIDESHOW);
            handler.sendEmptyMessageDelayed(HIDESHOW, deleyTime);
            sendUpdateProgressMsg(0);
        }
    };


    private void initPlayer() {
        mPlayerState = PLAYER_STATE_PREPARING;
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
    }

    private final int HIDESHOW = 100;
    private final int MSG_KEY_UPDATE_PROGRESS = 101;
    private final int SHOW_AD = 102;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:

                bigOrSmall();

                break;
            case R.id.rl_video:
                if (go_back.getVisibility() == View.VISIBLE) {
                    handler.removeMessages(HIDESHOW);
                    go_back.setVisibility(View.GONE);
                    rlBottombar.setVisibility(View.GONE);
                } else {
                    go_back.setVisibility(View.VISIBLE);
                    if (!isLive) {
                        rlBottombar.setVisibility(View.VISIBLE);
                    }
                    handler.removeMessages(HIDESHOW);
                    handler.sendEmptyMessageDelayed(HIDESHOW, deleyTime);
                }
                break;
            case R.id.btn_loading_failed_reload: // 重新加载
                showFirstLoadingView();
                handleRequestPlayInfoDataSuccess(path);
                break;
            case R.id.iv_play: // 播放or暂停
                handlePauseOrStart();
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HIDESHOW:
                    try {
                        go_back.setVisibility(View.GONE);
                        rlBottombar.setVisibility(View.GONE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case MSG_KEY_UPDATE_PROGRESS:
                    if (mPlayerState != PLAYER_STATE_PLAYING) {
                        return;
                    }
                    updateProgress();
                    break;
                case SHOW_AD://TODO
                    long time = DateTimeUtil.getDateLongByPattern(DateTimeUtil.getDateStringByPattern(DateTimeUtil.timeFormaterNoSecond), DateTimeUtil.timeFormaterNoSecond);
                    ;
                    if (time > startTime && time < endTime) {
                        tvAd.setText(adContent);
                        tvAd.setVisibility(View.VISIBLE);
                    } else {
                        tvAd.setVisibility(View.INVISIBLE);
                    }
                    ;
                    Log.d("timetime", "time = " + time + "; start = " + startTime + "; end = " + endTime);
                    break;

            }
        }
    };

    @Override
    public void onPrepared(IMediaPlayer mp) {
        mPlayerState = PLAYER_STATE_PREPARED;
        hideFirstLoadingView();
        try {
            if (go_back.getVisibility() == View.VISIBLE) {
                handler.sendEmptyMessageDelayed(HIDESHOW, deleyTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(PLAYER_TAG, "onPrepared ");
    }

    @Override
    public void onCompletion(IMediaPlayer mp) {
        mPlayerState = PLAYER_STATE_COMPLETED;
        updatePauseOrPlayUI();
        Log.e(PLAYER_TAG, "onCompletion ");
    }

    @Override
    public boolean onError(IMediaPlayer mp, int what, int extra) {
        Log.e(PLAYER_TAG, "onError " + what + " : " + extra);
        if (mPlayerState == PLAYER_STATE_COMPLETED) return false;

        mPlayerState = PLAYER_STATE_ERROR;
        showLoadingFailedView();
        return false;
    }

    @Override
    public boolean onInfo(IMediaPlayer mp, int what, int extra) {
        Log.e(PLAYER_TAG, "onInfo ");
        switch (what) {
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                Log.e(PLAYER_TAG, "MEDIA_INFO_VIDEO_RENDERING_START");
                handlePlayOnPrepared();
                hideLoadingView();
                break;
            case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                Log.e(PLAYER_TAG, "MEDIA_INFO_BUFFERING_START");
                showLoadingView();
                break;
            case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                hideLoadingView();
                break;
        }

        handlePlayOnPrepared();
        updateProgress();
        return false;
    }

    public void handleRequestPlayInfoDataSuccess(String url) {
        showFirstLoadingView();
        this.path = url;
        mVideoView.setVideoPath(url);
        mVideoView.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        handleVideoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        handleVideoPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //点击返回或不允许后台播放时 释放资源
        if (mVideoView != null) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        }
        mPlayerState = PLAYER_STATE_IDLE;
        IjkMediaPlayer.native_profileEnd();
    }

    /**
     * 播放前的处理
     */
    private void handlePlayOnPrepared() {
        if (mVideoView != null) {
            hideFirstLoadingView();
            mPlayerState = PLAYER_STATE_PLAYING;
            mDuration = mVideoView.getDuration();
            tvVideoDuration.setText("/" + StringUtil.stringForTime(mDuration));
            updatePauseOrPlayUI();
        }
    }

    /**
     * 更新播放进度
     */
    private void updateProgress() {
        if (mDuration == 0) {
            return;
        }
        mPosition = mVideoView.getCurrentPosition();
        tvVideoCurrentTime.setText(StringUtil.stringForTime(mPosition));
        int pos = (int) (Float.valueOf(mPosition) / Float.valueOf(mDuration) * MAX_PROGRESS_VALUE);
        seekbarVideo.setProgress(pos);
        if (pos < MAX_PROGRESS_VALUE) {
            sendUpdateProgressMsg(1000);
        }
    }

    /**
     * 更新播放进度的消息
     *
     * @param delayMillis
     */
    private void sendUpdateProgressMsg(int delayMillis) {
        removeUpdateProgressMsg();
        handler.sendEmptyMessageDelayed(MSG_KEY_UPDATE_PROGRESS, delayMillis);
    }

    /**
     * 移除播放进度的消息
     */
    private void removeUpdateProgressMsg() {
        handler.removeMessages(MSG_KEY_UPDATE_PROGRESS);
    }

    /**
     * 隐藏第一次加载状态
     */
    private void hideFirstLoadingView() {
        if (viewFirstLoading != null) {
            viewFirstLoading.setVisibility(View.GONE);
        }
    }

    /**
     * 显示加载状态
     */
    private void showLoadingView() {
        hideFirstLoadingView();
        hideLoadingFailedView();
        viewLoading.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏加载状态
     */
    private void hideLoadingView() {
        if (viewLoading != null) {
            viewLoading.setVisibility(View.GONE);
        }
    }

    /**
     * 显示加载失败状态
     */
    private void showLoadingFailedView() {
        hideLoadingView();
        hideFirstLoadingView();
        if (viewLoadingFailed != null) {
            viewLoadingFailed.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏加载失败状态
     */
    private void hideLoadingFailedView() {
        if (viewLoadingFailed != null) {
            viewLoadingFailed.setVisibility(View.GONE);
        }
    }


    private int mPlayerState = PLAYER_STATE_IDLE; // 播放器状态
    private int mUserOperatePlayerState = PLAYER_STATE_PLAYING; // 播放器状态
    private static final int PLAYER_STATE_ERROR = -1; // 播放页，异常状态
    private static final int PLAYER_STATE_IDLE = 0; // 播放页，初始状态
    private static final int PLAYER_STATE_PREPARING = 1; // 播放页，预加载状态
    private static final int PLAYER_STATE_PREPARED = 2; // 播放页，加载完毕状态
    private static final int PLAYER_STATE_PLAYING = 3; // 播放页，播放状态
    private static final int PLAYER_STATE_PAUSED = 4; // 播放页，暂停状态
    private static final int PLAYER_STATE_COMPLETED = 5; // 播放页，播放完毕状态
    private static final int PLAYER_STATE_DRAGGING = 6; // 播放页，拖拽进度状态

    /**
     * 处理视频的播放和暂停
     */
    private void handlePauseOrStart() {
        switch (mPlayerState) {
            case PLAYER_STATE_PLAYING: // 暂停操作
                mUserOperatePlayerState = PLAYER_STATE_PAUSED;
                handleVideoPause();
                break;
            case PLAYER_STATE_PAUSED: // 播放操作
                mUserOperatePlayerState = PLAYER_STATE_PLAYING;
                handleVideoPlay();
                break;
        }
    }

    /**
     * 暂停视频
     */
    private void handleVideoPause() {
        mVideoView.pause();
        mPlayerState = PLAYER_STATE_PAUSED;
        removeUpdateProgressMsg();
        updatePauseOrPlayUI();
    }

    /**
     * 播放视频
     */
    private void handleVideoPlay() {
        if (mUserOperatePlayerState == PLAYER_STATE_PLAYING && mPlayerState == PLAYER_STATE_PAUSED) {
            mVideoView.start();
            mPlayerState = PLAYER_STATE_PLAYING;
            sendUpdateProgressMsg(0);
            updatePauseOrPlayUI();
        }
    }

    /**
     * 更新播放按键状态
     */
    private void updatePauseOrPlayUI() {
        switch (mPlayerState) {
            case PLAYER_STATE_PLAYING:
                ivPlay.setImageResource(R.drawable.video_play_suspend);
                ivPlay.setContentDescription("pause");
                break;
            case PLAYER_STATE_PAUSED:
            case PLAYER_STATE_COMPLETED:
                ivPlay.setImageResource(R.drawable.video_play_play);
                ivPlay.setContentDescription("play");
                break;
        }
    }

    /**
     * 显示第一次加载状态
     */
    private void showFirstLoadingView() {
        hideLoadingView();
        hideLoadingFailedView();
        if (viewFirstLoading != null) {
            viewFirstLoading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mVideoView != null && mVideoView.isPlaying()) {
            mVideoView.stopBackgroundPlay();
            mVideoView.pause();
        }
    }
}
