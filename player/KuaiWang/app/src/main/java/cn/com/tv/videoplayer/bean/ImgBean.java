package cn.com.tv.videoplayer.bean;

import java.util.List;

/**
 * Created by ${ZangPengfei} on 2017/11/24.
 */

public class ImgBean {

    /**
     * code : 200
     * msg :
     * data : [{"launch":"http://122.188.6.5:8088//upload/startup.jpeg","ad1":"http://122.188.6.5:8088//upload/ad1.jpg","ad2":"http://122.188.6.5:8088//upload/ad2.jpg","ad3":""}]
     * totalCount : 1
     */

    private int code;
    private String msg;
    private int totalCount;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * launch : http://122.188.6.5:8088//upload/startup.jpeg
         * ad1 : http://122.188.6.5:8088//upload/ad1.jpg
         * ad2 : http://122.188.6.5:8088//upload/ad2.jpg
         * ad3 :
         */

        private String launch;
        private String ad1;
        private String ad2;
        private String ad3;

        public String getLaunch() {
            return launch;
        }

        public void setLaunch(String launch) {
            this.launch = launch;
        }

        public String getAd1() {
            return ad1;
        }

        public void setAd1(String ad1) {
            this.ad1 = ad1;
        }

        public String getAd2() {
            return ad2;
        }

        public void setAd2(String ad2) {
            this.ad2 = ad2;
        }

        public String getAd3() {
            return ad3;
        }

        public void setAd3(String ad3) {
            this.ad3 = ad3;
        }
    }
}
