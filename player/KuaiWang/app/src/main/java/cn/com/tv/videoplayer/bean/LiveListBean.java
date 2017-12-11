package cn.com.tv.videoplayer.bean;

import java.util.List;

/**
 * Created by ${ZangPengfei} on 2017/11/24.
 */

public class LiveListBean {

    /**
     * code : 200
     * msg :
     * data : [{"id":9,"channel":"CCTV-12","enable":0,"sourceId":44,"sourceName":"CCTV-12","networkId":18,"networkName":"enp5s0","networkType":null,"networkPppoeDev":null,"output":"http://183.92.30.214:8082/live//9/index.m3u8","createdT":null,"updatedT":1511075265000,"status":"已关闭"},{"id":8,"channel":"test20","enable":1,"sourceId":41,"sourceName":null,"networkId":1,"networkName":null,"networkType":null,"networkPppoeDev":null,"output":"http://183.92.30.214:8082/live//8/index.m3u8","createdT":null,"updatedT":1510682212000,"status":"已关闭"},{"id":6,"channel":"test2","enable":1,"sourceId":41,"sourceName":null,"networkId":1,"networkName":null,"networkType":null,"networkPppoeDev":null,"output":"http://183.92.30.214:8082/live//6/index.m3u8","createdT":null,"updatedT":null,"status":"已关闭"},{"id":4,"channel":"test","enable":0,"sourceId":38,"sourceName":null,"networkId":14,"networkName":null,"networkType":null,"networkPppoeDev":null,"output":"http://183.92.30.214:8082/live//4/index.m3u8","createdT":null,"updatedT":1509034167000,"status":"已关闭"}]
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
         * id : 9
         * channel : CCTV-12
         * enable : 0
         * sourceId : 44
         * sourceName : CCTV-12
         * networkId : 18
         * networkName : enp5s0
         * networkType : null
         * networkPppoeDev : null
         * output : http://183.92.30.214:8082/live//9/index.m3u8
         * createdT : null
         * updatedT : 1511075265000
         * status : 已关闭
         */

        private int id;
        private String channel;
        private int enable;
        private int sourceId;
        private String sourceName;
        private int networkId;
        private String networkName;
        private Object networkType;
        private Object networkPppoeDev;
        private String output;
        private Object createdT;
        private long updatedT;
        private String status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public int getSourceId() {
            return sourceId;
        }

        public void setSourceId(int sourceId) {
            this.sourceId = sourceId;
        }

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public int getNetworkId() {
            return networkId;
        }

        public void setNetworkId(int networkId) {
            this.networkId = networkId;
        }

        public String getNetworkName() {
            return networkName;
        }

        public void setNetworkName(String networkName) {
            this.networkName = networkName;
        }

        public Object getNetworkType() {
            return networkType;
        }

        public void setNetworkType(Object networkType) {
            this.networkType = networkType;
        }

        public Object getNetworkPppoeDev() {
            return networkPppoeDev;
        }

        public void setNetworkPppoeDev(Object networkPppoeDev) {
            this.networkPppoeDev = networkPppoeDev;
        }

        public String getOutput() {
            return output;
        }

        public void setOutput(String output) {
            this.output = output;
        }

        public Object getCreatedT() {
            return createdT;
        }

        public void setCreatedT(Object createdT) {
            this.createdT = createdT;
        }

        public long getUpdatedT() {
            return updatedT;
        }

        public void setUpdatedT(long updatedT) {
            this.updatedT = updatedT;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
