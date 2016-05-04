package com.troy.sendcard.util;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class StringUtil {
    public static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
