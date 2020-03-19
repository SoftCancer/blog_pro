package com.dongl.utils;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/19 23:23
 * @Version: 1.0
 */
public class StrUtils {


    /**
     * @Description: 判断对象是否为空，若为空返回：true，否则：false
     * @Author: YaoGuangXun
     * @Date: 2020/3/19 23:24
     **/
    public static Boolean isEmptyObj(Object o){

        if (null == o || "".equals(o)){
            return true;
        }
        return false;
    }

    /**
     * @Description: 判断 字符串是否为空，若为空返回：true，否则：false
     * @Author: YaoGuangXun
     * @Date: 2020/3/19 23:24
     **/
    public static Boolean isEmpty(String string){

        if (null == string || string.length() == 0){
            return true;
        }
        return false;
    }

    /**
     * 判断，如果字符串是否为 ：null ,"null","" ," " 则返回 true;
     * 否则返回：false ;
     * @Author: YaoGuangXun
     * @Date: 2020/3/19 23:38
     **/
    public static Boolean isBlank(String str){
        str = str.trim();
        if (null == str || str.length() == 0|| "null".equals(str)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String obj = " ";
        System.out.println("isEmpty : " + isBlank(obj));
    }
}
