package cn.com.tv.videoplayer.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.tv.videoplayer.R;
import cn.com.tv.videoplayer.bean.Constants;
import cn.com.tv.videoplayer.bean.ImgBean;
import cn.com.tv.videoplayer.bean.LoginScussBean;
import cn.com.tv.videoplayer.okhttp.CallBackUtil;
import cn.com.tv.videoplayer.okhttp.OkhttpUtil;
import cn.com.tv.videoplayer.utils.AppContext;
import cn.com.tv.videoplayer.utils.DialogUtil;
import cn.com.tv.videoplayer.utils.StringUtil;
import cn.com.tv.videoplayer.utils.ToastUtil;
import okhttp3.Call;

/**
 * Created by ${ZangPengfei} on 2017/11/1.
 */

public class StartActivity extends AppCompatActivity {

    private static String IP;//121.196.217.158
    public static String URL = "http://" + IP + Constants.BASE_URi;

    @Bind(R.id.ll_login)
    AutoLinearLayout ll_login;
    @Bind(R.id.ip)
    AutoCompleteTextView ip;
    @Bind(R.id.name)
    AutoCompleteTextView name;
    @Bind(R.id.mima)
    AutoCompleteTextView mima;
    @Bind(R.id.lunch_img)
    ImageView lunch_img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appstart);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        ll_login.setVisibility(AppContext.get(Constants.KEY_ISLOGIN, false) ? View.GONE : View.VISIBLE);
        if (AppContext.get(Constants.KEY_ISLOGIN, false)) {
            showLunchImg();
        }
    }

    public void login(View view) {

        String username = name.getText().toString().trim();
        String password = mima.getText().toString().trim();
        String IP = ip.getText().toString().trim();

//        if (TextUtils.isEmpty(IP)) { //TODO
//            IP = "122.190.35.138:8088";
//        } else {
//            IP = "121.196.217.158:8080";
//        }
//        username = "test2"; //TODO
//        password = "test123";

        AppContext.set(Constants.KEY_IP, IP);
        URL = "http://" + IP + Constants.BASE_URi;

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(IP) || TextUtils.isEmpty(password)) {
            ToastUtil.showToast("账户名，密码和IP不能为空");
            return;
        }
        String url = URL + "api/user/login";
        Map<String, String> map = new WeakHashMap<>();
        map.put("username", username);
        map.put("passwd", password);
        getData(url, map);

    }

    private void getData(String url, Map<String, String> map) {

        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(StartActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    LoginScussBean loginScussBean = StringUtil.jsonToObject(response, LoginScussBean.class);
                    if (loginScussBean.getCode() == 200) {
                        AppContext.set(Constants.KEY_TOKEN, loginScussBean.getData().getToken());
                        AppContext.set(Constants.KEY_USERNAME, loginScussBean.getData().getUsername());
                        AppContext.set(Constants.KEY_ISLOGIN, true);
                        AppContext.set(Constants.KEY_BASE_URL, "http://" + AppContext.get(Constants.KEY_IP, "") + Constants.BASE_URi);
                        ll_login.setVisibility(View.GONE);
                        Toast.makeText(StartActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();

                        getImgUrl = AppContext.get(Constants.KEY_BASE_URL, "") + "api/app/img";
                        Log.d("img", getImgUrl);

                        paramsMap.clear();
                        paramsMap.put("username", loginScussBean.getData().getUsername());
                        paramsMap.put("token", loginScussBean.getData().getToken());
                        connGetImg(getImgUrl, paramsMap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(StartActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }

                Log.d("login", response);
            }
        });
    }

    private String getImgUrl;
    private Map<String, String> paramsMap = new WeakHashMap<>();

    /**
     * 获取广告图
     */
    private void connGetImg(String url, final Map<String, String> map) {

        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                reConnOrGo(getImgUrl, map);
                Toast.makeText(StartActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    ImgBean imgBean = StringUtil.jsonToObject(response, ImgBean.class);
                    if (imgBean.getCode() == 200) {
                        if (imgBean.getData().size() == 0) {
                            AppContext.set(Constants.KEY_GUANGGAO_1_URL, "");
                            AppContext.set(Constants.KEY_GUANGGAO_2_URL, "");
                            AppContext.set(Constants.KEY_LUNCH_IMG_URL, "");
                        } else if (imgBean.getData().size() == 1) {
                            AppContext.set(Constants.KEY_GUANGGAO_1_URL, imgBean.getData().get(0).getAd1());
                            AppContext.set(Constants.KEY_GUANGGAO_2_URL, imgBean.getData().get(0).getAd2());
                            AppContext.set(Constants.KEY_LUNCH_IMG_URL, imgBean.getData().get(0).getLaunch());
                        }
                        showLunchImg();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    reConnOrGo(getImgUrl, map);
                    Toast.makeText(StartActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
                }

                Log.d("img", response);
            }
        });
    }

    private final int SHOW_LUNCHIMG = 100;
    private final int GET_IMG_URL = 101;
    private final int DELAY_TIME = 2000;

    private void showLunchImg() {
        lunch_img.setVisibility(View.VISIBLE);
        Log.d("imgimg", AppContext.get(Constants.KEY_LUNCH_IMG_URL, ""));
        Glide
                .with(this)
                .load(AppContext.get(Constants.KEY_LUNCH_IMG_URL, ""))
//                .placeholder(R.drawable.startup) //设置占位图
                .into(lunch_img);
        handler.sendEmptyMessageDelayed(SHOW_LUNCHIMG, DELAY_TIME);
    }

    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case SHOW_LUNCHIMG:
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case GET_IMG_URL:

                    break;
            }
        }
    };

    private void reConnOrGo(final String url, final Map<String, String> map) {
        DialogUtil.showConfirmDialog(false, this, "", "重新获取图片或跳过！", "跳过", "重获取", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        connGetImg(url, map);
                    }
                }
        );

    }

}
