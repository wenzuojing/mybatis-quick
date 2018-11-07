package com.github.wens.mybatis.support.util;


/**
 * @author wens
 * @Date 2018-10-10
 */
public class StringUtils {

    public static final String EMPTY = "";

    public static boolean isEmpty(String src) {
        return src == null || "".equals(src);
    }

    public static boolean isNotEmpty(String src) {

        if (src != null && !src.trim().equals("")) {
            return true;
        }
        return false;

    }
}
