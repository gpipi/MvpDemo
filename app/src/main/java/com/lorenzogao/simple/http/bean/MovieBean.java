package com.lorenzogao.simple.http.bean;

import java.util.List;

/**
 * 作者：Lorenzo Gao
 * Date: 2018/7/31
 * Time: 9:04
 * 邮箱：2508719070@qq.com
 * Description:
 */

public class MovieBean {


    /**
     * resultcode : 200
     * reason : success
     * result : [{"rid":"1","name":"奇异博士","wk":"2016.11.7- 2016.11.13（单位：万元）","wboxoffice":"27000","tboxoffice":"57100"},{"rid":"2","name":"比利·林恩的中场战事","wk":"2016.11.7- 2016.11.13（单位：万元）","wboxoffice":"8100","tboxoffice":"8100"},{"rid":"3","name":"航海王之黄金城","wk":"2016.11.7- 2016.11.13（单位：万元）","wboxoffice":"6980","tboxoffice":"6980"},{"rid":"4","name":"外公芳龄38","wk":"2016.11.7- 2016.11.13（单位：万元）","wboxoffice":"3490","tboxoffice":"3490"},{"rid":"5","name":"驴得水","wk":"2016.11.7- 2016.11.13（单位：万元）","wboxoffice":"2880","tboxoffice":"16312"},{"rid":"6","name":"但丁密码","wk":"2016.11.7- 2016.11.13（单位：万元）","wboxoffice":"4370","tboxoffice":"12930"},{"rid":"7","name":"捉迷藏","wk":"2016.11.7- 2016.11.13（单位：万元）","wboxoffice":"2750","tboxoffice":"6758"},{"rid":"8","name":"邻家大贱谍","wk":"2016.11.7- 2016.11.13（单位：万元）","wboxoffice":"1110","tboxoffice":"1110"},{"rid":"9","name":"湄公河行动","wk":"2016.11.7- 2016.11.13（单位：万元）","wboxoffice":"880","tboxoffice":"117908"},{"rid":"10","name":"机械师2：复活","wk":"2016.11.7- 2016.11.13（单位：万元）","wboxoffice":"533","tboxoffice":"33733"}]
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * rid : 1
         * name : 奇异博士
         * wk : 2016.11.7- 2016.11.13（单位：万元）
         * wboxoffice : 27000
         * tboxoffice : 57100
         */

        private String rid;
        private String name;
        private String wk;
        private String wboxoffice;
        private String tboxoffice;

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWk() {
            return wk;
        }

        public void setWk(String wk) {
            this.wk = wk;
        }

        public String getWboxoffice() {
            return wboxoffice;
        }

        public void setWboxoffice(String wboxoffice) {
            this.wboxoffice = wboxoffice;
        }

        public String getTboxoffice() {
            return tboxoffice;
        }

        public void setTboxoffice(String tboxoffice) {
            this.tboxoffice = tboxoffice;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "rid='" + rid + '\'' +
                    ", name='" + name + '\'' +
                    ", wk='" + wk + '\'' +
                    ", wboxoffice='" + wboxoffice + '\'' +
                    ", tboxoffice='" + tboxoffice + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MovieBean{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", error_code=" + error_code +
                ", result=" + result +
                '}';
    }
}
