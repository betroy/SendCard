package com.troy.sendcard.bean.response;

/**
 * Created by chenlongfei on 16/4/11.
 */
public class TodoResult {

    /**
     * code : 200
     * msg : success
     * data : {"wait_total":"3","handled_total":"1"}
     */

    private int code;
    private String msg;
    /**
     * wait_total : 3
     * handled_total : 1
     */

    private TodoEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(TodoEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public TodoEntity getData() {
        return data;
    }

    public static class TodoEntity {
        private String wait_total;
        private String handled_total;

        public void setWait_total(String wait_total) {
            this.wait_total = wait_total;
        }

        public void setHandled_total(String handled_total) {
            this.handled_total = handled_total;
        }

        public String getWait_total() {
            return wait_total;
        }

        public String getHandled_total() {
            return handled_total;
        }
    }
}
