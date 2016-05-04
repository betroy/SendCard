package com.troy.sendcard.bean.request;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlongfei on 16/4/21.
 */
public class CardValidateParam {

    /**
     * user_id : 78
     * secret : 7a84c22e307899fb263bee49a9c1b4ab7d3493c6
     * time : nowtime
     * card_no : 1000119000000802222
     */

    private String user_id;
    private String secret;
    private String time;
    private String card_no;
    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public Map<String, String> createParam() {
        Map<String, String> param = new HashMap<String, String>();
        if (!TextUtils.isEmpty(user_id)) {
            param.put("user_id", user_id);
        }
        if (!TextUtils.isEmpty(secret)) {
            param.put("secret", secret);
        }
        if (!TextUtils.isEmpty(time)) {
            param.put("time", time);
        }
        if (!TextUtils.isEmpty(card_no)) {
            param.put("card_no", card_no);
        }
        if (!TextUtils.isEmpty(sign)) {
            param.put("sign", sign);
        }
        return param;
    }
}
