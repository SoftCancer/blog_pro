package com.dongl.entity;

/**
 * @Description:  系统状态码
 * @author: YaoGuangXun
 * @date: 2020/3/13 22:27
 * @Version: 1.0
 */
public class StatusCode {
    /**  成功  **/
    public static final int OK = 20000;
    /**  失败  **/
    public static final int ERROR = 20000;
    /**  用户名密码错误  **/
    public static final int LOGIN_ERROR = 20000;
    /**  权限不足  **/
    public static final int ACCESS_ERROR = 20000;
    /**  远程调用失败  **/
    public static final int REMOTE_ERROR = 20000;
    /**  重复操作  **/
    public static final int REP_ERROR = 20000;

}
