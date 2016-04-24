package com.github.wens.mybatisplus.toolkit;

/**
 * Created by wens on 16/4/23.
 */
public class StringUtil {

    public static boolean isNotEmpty(String src) {

        if (src != null && !src.trim().equals("")) {
            return true;
        }
        return false;

    }
}
