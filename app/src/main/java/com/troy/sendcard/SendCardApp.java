package com.troy.sendcard;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.troy.sendcard.config.Constant;
import com.troy.sendcard.util.SPUtil;


/**
 * Created by chenlongfei on 16/4/4.
 */
public class SendCardApp extends Application {
    public static Context mContext;
    public static UserInfo userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static UserInfo getUserInfo() {
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    public static class UserInfo {
        private String userName;
        private String passWord;
        private String userId;
        private String secret;

        public String getUserName() {
            if (null != this.userName) {
                return this.userName;
            }
            return SPUtil.getStringValue(Constant.SP.SP_KEY_USERNAME);
        }

        public void setUserName(String userName) {
            SPUtil.putStringValue(Constant.SP.SP_KEY_USERNAME, userName);
            this.userName = userName;
        }

        public String getPassWord() {
            if (null != this.passWord) {
                return this.passWord;
            }
            return SPUtil.getStringValue(Constant.SP.SP_SECRET);
        }

        public void setPassWord(String passWord) {
            SPUtil.putStringValue(Constant.SP.SP_KEY_PWD, passWord);
            this.passWord = passWord;
        }

        public String getUserId() {
            if (null != userId) {
                return this.userId;
            }
            return SPUtil.getStringValue(Constant.SP.SP_USER_ID);
        }

        public void setUserId(String userId) {
            SPUtil.putStringValue(Constant.SP.SP_USER_ID, userId);
            this.userId = userId;
        }

        public String getSecret() {
            if (null != secret) {
                return this.secret;
            }
            return SPUtil.getStringValue(Constant.SP.SP_SECRET);
        }

        public void setSecret(String secret) {
            SPUtil.putStringValue(Constant.SP.SP_SECRET, secret);
            this.secret = secret;
        }
    }
}
