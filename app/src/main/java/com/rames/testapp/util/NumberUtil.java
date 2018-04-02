package com.rames.testapp.util;

/**
 * 数字处理工具类
 * Created by LvMeng on 15/12/15.
 */
public class NumberUtil {

    /**
     * 格式数据，如果大约1万的话，显示 x.xx万
     *
     * @param num 多少数
     */
    public static String formatLikeNum(double num) {
        return (num < 10000) ? String.format("%.0f", num) : String.format("%.1f万", num / 10000);
    }

    /**
     * 格式数据，如果大约10万的话，显示 x.xx万
     *
     * @param num 多少数
     */
    public static String formatLikeNumFor10W(double num) {
        return (num < 100000) ? String.format("%.0f", num) : String.format("%.1f万", num / 10000);
    }
    /**
     * 格式数据，如果大约100万的话，显示 x.xx万
     *
     * @param num 多少数
     */
    public static String formatLikeNumFor100W(double num) {
        return (num < 1000000) ? String.format("%.0f", num) : String.format("%.1f万", num / 10000);
    }

    public static String formatNumber(double num){
        String result = "";
        // 大于1万,小于1亿,以万为单位
        if(num >= 10000 && num < 100000000){
            return String.format("%.1f万", num / 10000);

            // 大于1亿的
        } else if(num >= 100000000){
            return String.format("%.1f亿", num / 100000000);

        }


        return result;
    }
}
