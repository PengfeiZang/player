package cn.com.tv.videoplayer.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoFrameLayout;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.tv.videoplayer.R;
import cn.com.tv.videoplayer.bean.Constants;
import cn.com.tv.videoplayer.bean.LiveListBean;
import cn.com.tv.videoplayer.fragment.PlayVideoFragment;
import cn.com.tv.videoplayer.okhttp.CallBackUtil;
import cn.com.tv.videoplayer.okhttp.OkhttpUtil;
import cn.com.tv.videoplayer.utils.AppContext;
import cn.com.tv.videoplayer.utils.DialogUtil;
import cn.com.tv.videoplayer.utils.StringUtil;
import cn.com.tv.videoplayer.utils.ToastUtil;
import okhttp3.Call;


/**
 * 主程序入口
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private PlayVideoFragment playVideoFragment;
    private final String TAG = "MainActivity";

    //    private String url5 = "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";
//    private String url5 = "http://122.188.6.5:8082/live//6/index.m3u8";
//    private String url5 = "http://122.188.6.5:8083//电影/测试/dongwu.mp4";
    private String url5 = "http://la.sdiread.cn/o_1as1jp3qimkhhuo1o54sve1nlt9.mp4";


    @Bind(R.id.live_play)
    Button live_play;
    @Bind(R.id.record_play)
    Button record_play;
    @Bind(R.id.guanggao1)
    ImageView guanggao1;
    @Bind(R.id.guanggao2)
    ImageView guanggao2;
    @Bind(R.id.logout)
    Button logout;
    @Bind(R.id.go_back)
    Button go_back;
    @Bind(R.id.video_contener)
    AutoFrameLayout video_contener;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

        initData();
    }

    private void initData() {
        fragmentManager = getFragmentManager();
        if (null == playVideoFragment) {
            playVideoFragment = new PlayVideoFragment();
            fragmentTransaction = fragmentManager.beginTransaction();
            playVideoFragment.isLive = false;
        }
        showSmallVideo();
        connGetData();
    }

    public void showSmallVideo() {
        fragmentTransaction.replace(R.id.video_contener, playVideoFragment).commit();
        mHandler.sendEmptyMessageDelayed(SHOW_FRAGMENT, 2000);
    }

    private final int SHOW_FRAGMENT = 100;

    private final Handler mHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case SHOW_FRAGMENT:
                    if (playVideoFragment.isAdded()) {
//                        playVideoFragment.handleRequestPlayInfoDataSuccess(url5);
                    }
                    break;
            }
        }
    };

    private void initView() {
        live_play.setOnClickListener(this);
        record_play.setOnClickListener(this);
        guanggao1.setOnClickListener(this);
        guanggao2.setOnClickListener(this);
        logout.setOnClickListener(this);
        go_back.setOnClickListener(this);

        initAd();
    }

    private void initAd() {
        Glide
                .with(this)
                .load(AppContext.get(Constants.KEY_GUANGGAO_1_URL, ""))
//                .placeholder(R.drawable.startup) //设置占位图
                .into(guanggao1);

        Glide
                .with(this)
                .load(AppContext.get(Constants.KEY_GUANGGAO_2_URL, ""))
//                .placeholder(R.drawable.startup) //设置占位图
                .into(guanggao2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.live_play:
                Intent intent = new Intent(this, LivePlayVideoActivity.class);
                startActivity(intent);
                break;
            case R.id.record_play:
                Intent intent1 = new Intent(this, RecordPlayVideoActivity.class);
                startActivity(intent1);
                break;
            case R.id.guanggao1:
                ToastUtil.showToast("广告1");
                break;
            case R.id.guanggao2:
                ToastUtil.showToast("广告2");
                break;
            case R.id.go_back:
                goBack();
                break;
            case R.id.logout:
                DialogUtil.showConfirmDialog(false, this, "", "确定退出登录吗？", getString(R.string.make_sure), getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AppContext.set(Constants.KEY_TOKEN, "");
                                AppContext.set(Constants.KEY_USERNAME, "");
                                AppContext.set(Constants.KEY_IP, "");
                                AppContext.set(Constants.KEY_ISLOGIN, false);
                                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }
                );

                break;
        }
    }

    private void goBack() {
        DialogUtil.showConfirmDialog(false, this, "", "确定退出应用吗？", getString(R.string.video_exit), getString(R.string.video_stay_here), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }
        );
    }


    public void updateWidth(int width, int heigth) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) video_contener.getLayoutParams();
        params.width = width;
        params.height = heigth;
        video_contener.setLayoutParams(params);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goBack();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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

            }

            @Override
            public void onResponse(String response) {
                try {

                    LiveListBean listBean = StringUtil.jsonToObject(response, LiveListBean.class);

                    if (listBean.getCode() == 200) {
                        playVideoFragment.handleRequestPlayInfoDataSuccess(listBean.getData().get(0).getOutput());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d(TAG, response);
            }
        });


    }

}


