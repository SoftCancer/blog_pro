package com.dongl.utils;

import java.util.UUID;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/18 21:28
 * @Version: 1.0
 */
public class UUIDUtils {

    /**
     * @Description: 获取去除 - 的UUID的字符串。
     * @Author: YaoGuangXun
     * @Date: 2020/3/18 21:29
     **/
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
