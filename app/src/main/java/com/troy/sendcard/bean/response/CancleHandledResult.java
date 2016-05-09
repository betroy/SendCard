package com.troy.sendcard.bean.response;

/**
 * Created by chenlongfei on 16/4/20.
 */
public class CancleHandledResult {
    /**
     * code : 200
     * msg : SUCCESS
     * data :
     */

    private int code;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
