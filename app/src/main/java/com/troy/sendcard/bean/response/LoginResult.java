package com.troy.sendcard.bean.response;

/**
 * Created by chenlongfei on 16/4/7.
 */
public class LoginResult {

    /**
     * code : 200
     * msg : SUCCESS
     * data : {"user_id":"12","secret":"0b6e9c5401d197bdb35c475494a3f9874feacfbf"}
     */

    private int code;
    private String msg;
    /**
     * user_id : 12
     * secret : 0b6e9c5401d197bdb35c475494a3f9874feacfbf
     */

    private UserDataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(UserDataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public UserDataEntity getData() {
        return data;
    }

    public static class UserDataEntity {
        private String user_id;
        private String secret;

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getSecret() {
            return secret;
        }
    }
}
