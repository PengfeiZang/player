package cn.com.tv.videoplayer.bean;

import java.util.List;

/**
 * Created by ${ZangPengfei} on 2017/11/26.
 */

public class RecordListBean {

    /**
     * code : 200
     * msg : 获取列表成功
     * data : [{"name":"测试","category":"热门","desc":"测试数据\n123456\n","img":"http://122.188.6.5:8083//热门/测试/background.png","output":"http://122.188.6.5:8083//热门/测试/dongwu.mp4","size":67,"modifyTime":"2017-11-19 16:19:24"},{"name":"测试","category":"电影","desc":"测试数据\n123456\n","img":"http://122.188.6.5:8083//电影/测试/background.png","output":"http://122.188.6.5:8083//电影/测试/dongwu.mp4","size":67,"modifyTime":"2017-11-23 23:00:36"},{"name":"测试","category":"电视剧","desc":"测试数据\n123456\n","img":"http://122.188.6.5:8083//电视剧/测试/background.png","output":"http://122.188.6.5:8083//电视剧/测试/dongwu.mp4","size":67,"modifyTime":"2017-11-23 23:00:40"}]
     * totalCount : 3
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
         * name : 测试
         * category : 热门
         * desc : 测试数据
         123456

         * img : http://122.188.6.5:8083//热门/测试/background.png
         * output : http://122.188.6.5:8083//热门/测试/dongwu.mp4
         * size : 67
         * modifyTime : 2017-11-19 16:19:24
         */

        private String name;
        private String category;
        private String desc;
        private String img;
        private String output;
        private int size;
        private String modifyTime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getOutput() {
            return output;
        }

        public void setOutput(String output) {
            this.output = output;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }
    }
}
