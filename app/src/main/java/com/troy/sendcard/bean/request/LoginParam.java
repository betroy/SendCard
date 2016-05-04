package com.troy.sendcard.bean.request;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlongfei on 16/4/8.
 */
public class LoginParam {

    /**
     * url_req_type : 1
     * user_account : 12
     * user_pwd : 0b6e9c5401d197bdb35c475494a3f9874feacfbf
     * time : 1459341337
     * sign : e0401989bc4ebce5c4bab89617d21c93efe400e6
     */

    private String url_req_type;
    private String user_account;
    private String user_pwd;
    private String time;
    private String sign;

    public static final String REQ_TYPE = "1";  //url_req_type=1这个固定写死的参数，用来区分是登录请求。

    public void setUrl_req_type(String url_req_type) {
        this.url_req_type = url_req_type;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUrl_req_type() {
        return url_req_type;
    }

    public String getUser_account() {
        return user_account;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public String getTime() {
        return time;
    }

    public String getSign() {
        return sign;
    }

    public Map<String, String> createLoginParams() {
        Map<String, String> params = new HashMap<String, String>();
        if(!TextUtils.isEmpty(url_req_type)){
            params.put("url_req_type", url_req_type);
        }
        if(!TextUtils.isEmpty(user_account)){
            params.put("user_account", user_account);
        }
        if(!TextUtils.isEmpty(user_pwd)){
            params.put("user_pwd", user_pwd);
        }
        if(!TextUtils.isEmpty(time)){
            params.put("time", time);
        }
        if(!TextUtils.isEmpty(sign)){
            params.put("sign", sign);
        }
        return params;
    }
}
