package com.troy.sendcard.bean.request;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class UnhandledParam {

    /**
     * user_id : 12
     * secret : 0b6e9c5401d197bdb35c475494a3f9874feacfbf
     * page : 1
     * pageSize : 10
     * time : 1459341337
     * sign : e0401989bc4ebce5c4bab89617d21c93efe400e6
     */

    private String user_id;
    private String secret;
    private String page;
    private String pageSize;
    private String time;
    private String sign;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
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

    public String getPage() {
        return page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public String getTime() {
        return time;
    }

    public String getSign() {
        return sign;
    }

    public Map<String, String> createUnhandledParam() {
        Map<String, String> params = new HashMap<String, String>();
        if (!TextUtils.isEmpty(user_id)) {
            params.put("user_id", user_id);
        }
        if (!TextUtils.isEmpty(secret)) {
            params.put("secret", secret);

        }
        if (!TextUtils.isEmpty(page)) {
            params.put("page", page);
        }
        if (!TextUtils.isEmpty(pageSize)) {
            params.put("pageSize", pageSize);
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
