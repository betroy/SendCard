package com.troy.sendcard.bean.request;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlongfei on 16/4/20.
 */
public class CardInfoSubmitParam {

    /**
     * user_id : 12
     * secret : 0b6e9c5401d197bdb35c475494a3f9874feacfbf
     * apply_id : 6
     * send_no : 9016080878
     * send_type : 1
     * cardlist : ["1000119000001832323","1000119000001832324","1000119000001832325"]
     * imglist : [base64,base64]
     * time : 1459341337
     * sign : e0401989bc4ebce5c4bab89617d21c93efe400e6
     */

    private String user_id;
    private String secret;
    private String apply_id;
    private String send_no;
    private String send_type;
    private String cardlist;
    private String imglist;
    private String time;
    private String sign;

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

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getSend_no() {
        return send_no;
    }

    public void setSend_no(String send_no) {
        this.send_no = send_no;
    }

    public String getSend_type() {
        return send_type;
    }

    public void setSend_type(String send_type) {
        this.send_type = send_type;
    }

    public String getCardlist() {
        return cardlist;
    }

    public void setCardlist(String cardlist) {
        this.cardlist = cardlist;
    }

    public String getImglist() {
        return imglist;
    }

    public void setImglist(String imglist) {
        this.imglist = imglist;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Map<String, String> createCardInfoSubmitParam() {
        Map<String, String> params = new HashMap<String, String>();
        if (!TextUtils.isEmpty(user_id)) {
            params.put("user_id", user_id);
        }
        if (!TextUtils.isEmpty(secret)) {
            params.put("secret", secret);
        }
        if (!TextUtils.isEmpty(apply_id)) {
            params.put("apply_id", apply_id);
        }
        if (!TextUtils.isEmpty(send_no)) {
            params.put("send_no", send_no);
        }
        if (!TextUtils.isEmpty(send_type)) {
            params.put("send_type", send_type);
        }
        if (!TextUtils.isEmpty(cardlist)) {
            params.put("cardlist", cardlist);
        }
        if (!TextUtils.isEmpty(imglist)) {
            params.put("imglist", imglist);
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
