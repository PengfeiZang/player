package cn.com.tv.videoplayer.bean;

/**
 * Created by ${ZangPengfei} on 2017/10/30.
 */

public class LoginScussBean {

    /**
     * code : 200
     * msg :
     * data : {"username":"test2","token":"001038701803199756","loginTime":1509348120,"lastActiveTime":0}
     * totalCount : -1
     */

    private int code;
    private String msg;
    private DataBean data;
    private int totalCount;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public static class DataBean {
        /**
         * username : test2
         * token : 001038701803199756
         * loginTime : 1509348120
         * lastActiveTime : 0
         */

        private String username;
        private String token;
        private int loginTime;
        private int lastActiveTime;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(int loginTime) {
            this.loginTime = loginTime;
        }

        public int getLastActiveTime() {
            return lastActiveTime;
        }

        public void setLastActiveTime(int lastActiveTime) {
            this.lastActiveTime = lastActiveTime;
        }
    }
}
