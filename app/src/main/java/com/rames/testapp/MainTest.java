package com.rames.testapp;

import android.text.TextUtils;

import com.rames.testapp.util.DataDistance;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by yangju1 on 17/4/18.
 * yangju1@staff.weibo.com
 */
public class MainTest {

    public static void main(String[] args) {
//        int a = 50;
//        double b = 13.7;
//        String s = formatNumber(520000000);
//        System.out.print("time: "+(int)b);

//        reTime(1494483105000l);
//        getCurrFormatTime(1494792191000l);

//        print(String.format("%02d", 121));

//        print(getCurrFormatTime("M月dd日 HH : mm", 1513675459));

//        String s = "1456";
//        print(s.length()+"");

//        math(45, 100);
//        fun();

//        testEmpty();
//        long[] time = getDistanceTime(System.currentTimeMillis(), 1494757324);
//        print(String.format("%02d", ((time[0]*24)+time[1])) + ":" + String.format("%02d", time[2]) + ":" + String.format("%02d", time[3]));

        List list = new ArrayList();
        list.add("sdfs");
        list.add("532");

        for (int i=0; i<list.size(); i++){
            String name = list.get(i)+"";
            print(name);
        }

        print("1 == 1: "+(1==1));
    }

    private static boolean priceCheck(int index){
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        String s = list.get(index);
        list.remove(index);

        list.add(0, s);

        print(list.get(0)+","+list.get(1)+","+list.get(2)+","+list.get(3)+","+list.get(4)+",");
        return false;
    }

    private static void reTime(long sTime){
        String formatData = "yyyy-MM-dd hh:mm:ss";
        long nTime = System.currentTimeMillis();
        print("sTime: "+sTime+" | nTime: "+nTime);

        SimpleDateFormat format =  new SimpleDateFormat(formatData);
        String d = format.format(sTime);

        print("smallTime: "+d +" | bigTime: "+format.format(nTime));
        if((sTime) > nTime){
            print("已经开始了");
        }else{
            print("未开始");
            print(DataDistance.getDistanceTime(sTime, nTime));
        }

        print(getCurrFormatTime(formatData, nTime));
    }

    public static String getCurrFormatTime(String format, long time){
        SimpleDateFormat format1 =  new SimpleDateFormat(format);
        if((time+"").length() == 10){
            time = time*1000;
        }
        return format1.format(time);
    }

    /** 得到当前时间 */
    private static void getCurrFormatTime(long time){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM月dd日 HH:mm");
        String d = format.format(time);
        System.out.println("时间: "+d+" | 当前时间: "+format.format(System.currentTimeMillis()));
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

    public static String formatDuration(long startTime) {
        if (startTime == 0) {
            return "--:--";
        }
        long hours = startTime / 3600;
        long minutes = (startTime % 3600) / 60;
        long seconds = startTime % 60;
        if (startTime < 0) {
            return "--:--:--";
        }
        if (hours >= 100) {
            return String.format(Locale.US, "%d:%02d:%02d", hours, minutes, seconds);
        } else if (hours > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
        }
    }

    public static void print(String string){
        System.out.println(string);
    }

    public static long[] getDistanceTime(long smallTime, long bigTime) {
        if((bigTime+"").length() == 10){
            bigTime = bigTime*1000;
        }

        if(smallTime > bigTime){
            long[] time = {0, 0, 0, 0};
            return time;
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str1 = df.format(smallTime);
        String str2 = df.format(bigTime);
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long[] times = {day, hour, min, sec};
        return times;
    }

    private static void math(double du, double cd){
        // 30度
        double x = Math.sin(du/180*Math.PI)*cd;
        double y = Math.cos(du/180*Math.PI)*cd;
        print("x: "+x +" | y：" +y);
    }

    private static void fun(){
        String numbers = "13,134";
        if(numbers.length()>0) {
            String[] values = numbers.split(",");

            if (values.length>2) {
                print(values[0]+"|"+values[1]+"|"+values[2]);
            }
        }
    }

    public static String checkString(String s){
        return isEmpty(s)?"":s;
    }

    public static void testEmpty(){
        String temp = null;
        temp = checkString(temp);

        if(isEmpty(temp)){
            System.out.println("为空");
        }else{
            System.out.println("不为空");
        }

        if(null == temp){
            System.out.print("为null");
        }else{
            System.out.print("不为null");
        }
    }

    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    /** 集合数据位置对换 */
    public static void swap(List<?> list, int i, int j){
        if(i == j) return;
        final List l=list;
        l.set(i, l.set(j, l.get(i)));
    }
}
