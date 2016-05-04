package com.troy.sendcard.bean.request;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlongfei on 16/4/11.
 */
public class TodoParam {

    /**
     * user_id : 12
     * secret : 0b6e9c5401d197bdb35c475494a3f9874feacfbf
     * time : 1459341337
     * sign : e0401989bc4ebce5c4bab89617d21c93efe400e6
     */

    private String user_id;
    private String secret;
    private String time;
    private String sign;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getSecret() {
        return secret;
    }

    public String getTime() {
        return time;
    }

    public String getSign() {
        return sign;
    }

    public Map<String, String> createTodoParam() {
        Map<String, String> params = new HashMap<String, String>();
        if (!TextUtils.isEmpty(user_id)) {
            params.put("user_id", user_id);
        }
        if (!TextUtils.isEmpty(secret)) {
            params.put("secret", secret);
        }
        if (!TextUtils.isEmpty(time)) {
            params.put("time", time);
        }
        if (!TextUtils.isEmpty(sign)) {
            params.put("sign", sign);
        }
        return params;
    }
}
