package com.gupiaopiao.util;

public class NumberUtil {
    public static Float parseFloat(String str) {
        return parseFloat(str, null);
    }

    public static Float parseFloat(String str, Float defaultValue) {
        if (!"".equals(str)) {
            try {
                return Float.valueOf(str);
            } catch (Exception e) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static Integer parseInt(String str, Integer defaultValue) {
        if (!"".equals(str)) {
            try {
                return Integer.valueOf(str);
            } catch (Exception e) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static Integer parseInt(String str) {
        return parseInt(str, null);
    }
}
