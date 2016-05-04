package com.troy.sendcard.util;

import android.util.Log;

import com.troy.sendcard.config.Constant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by chenlongfei on 16/4/11.
 */
public class SignUtil {
    public static String sign(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        Collection<String> keyset = params.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);
        for (String key : list) {
            String value = params.get(key);
            sb.append(key + "=" + value + "&");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(Constant.getSecretKey());
        Log.i("Troy", "sign:" + sb.toString());
        return SHA1(sb.toString());
    }

    public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
