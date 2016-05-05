package com.troy.sendcard.config;

/**
 * Created by chenlongfei on 16/4/4.
 */
public class Constant {
    public static String PAGE_SIZE = "10";
    public static final String KEY_EXTRA_EXPRESS_NO = "expressno";
    public static final int DATA_REFRESH=0;
    public static final int DATA_LOADING_MORE=1;

    public static class Config {
        public static final boolean DEBUG = true;
    }

    public static class SP {
        public static final String SP_NAME = "sendcard";
        public static final String SP_KEY_USERNAME = "username";
        public static final String SP_KEY_PWD = "password";
        public static final String SP_USER_ID = "user_id";
        public static final String SP_SECRET = "secret";
    }

    public static class Host {
        public static final String BASE_HOST = "http://jtest.jtjr.com/sendCard/";
        public static final String DEBUG_HOST = "http://jtest.jtjr.com/sendCard/";
    }

    public static class Api {
        //登录
        public static final String LOGIN = "loginAuth";
        //注销
        public static final String LOGOUT = "logOut";
        //已处理未处理总数
        public static final String TODO = "applyHandleTotal";
        //未处理列表
        public static final String UNHANDLED = "applyUnhandle";
        //已处理列表
        public static final String HANDLED = "applyHandled";
        //发卡信息
        public static final String APPLYFILL = "ApplyFill";
        //发卡信息提交
        public static final String APPLYSUBMISSION = "applySubmission";
        //取消锁定申请
        public static final String APPLYUNLOCK = "applyUnlock";
        //副卡卡号检查
        public static final String CARDVALIDATE = "cardValidate";
    }

    public static class Intent {
        public static final String KEY_APPLY_ID = "applyId";
        public static final String KEY_SENDCARDINFO = "SendCardInfo";
    }

    public static class ResponeStatus {
        public static final int OK = 200;
        public static final int NET_ERROR = 500;
    }

    public static class SendCardStatusCode {
        public static final int APPLY_ID_NULL = 1000;
        public static final int APPLY_ID_INFO_NULL = 1001;
        public static final int CARD_NO_TOTAL_EMPTY = 1002;
        public static final int APPLY_HANDLING = 1003;
    }

    public static String getHost() {
        if (Config.DEBUG) {
            return Host.DEBUG_HOST;
        }
        return Host.BASE_HOST;
    }

    public static String getSecretKey() {
        if (Config.DEBUG) {
            return "jyblife2016_15101056";
        }
        return "jyblife2016_15101056";
    }

}
