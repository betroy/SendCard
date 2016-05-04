package com.troy.sendcard.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.troy.sendcard.SendCardApp;
import com.troy.sendcard.config.Constant;

/**
 * Created by chenlongfei on 16/4/4.
 */
public class SPUtil {
    public static String getStringValue(String key) {
        SharedPreferences sp = getSP();
        return sp.getString(key, "");
    }

    public static void putStringValue(String key, String value) {
        SharedPreferences sp = getSP();
        sp.edit().putString(key, value).commit();
    }


    public static SharedPreferences getSP() {
        SharedPreferences sp = SendCardApp.mContext.getSharedPreferences(Constant.SP.SP_NAME, Context.MODE_PRIVATE);
        return sp;
    }

}
