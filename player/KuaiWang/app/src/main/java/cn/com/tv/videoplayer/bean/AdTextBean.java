package cn.com.tv.videoplayer.bean;

import java.util.List;

/**
 * Created by ${ZangPengfei} on 2017/11/26.
 */

public class AdTextBean {

    /**
     * code : 200
     * msg :
     * data : [{"content":"测试字幕啦啦啦啦~~~","startTime":"10:11","endTime":"10:22"}]
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
         * content : 测试字幕啦啦啦啦~~~
         * startTime : 10:11
         * endTime : 10:22
         */

        private String content;
        private String startTime;
        private String endTime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
}
