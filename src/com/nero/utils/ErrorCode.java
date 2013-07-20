package com.nero.utils;

/**
 * Created with IntelliJ IDEA.
 * User: nero
 * Date: 13-7-20
 * Time: 上午9:53
 * Introduction
 */

public interface ErrorCode {

    //success
    public static final int SUCCESS                     = -1;
    /*====================================== 错误码 ======================================*/
    //邮件发送失败
    public static final int MAIL_SEND_FAIL              = 10000;
    //邮件认证失败
    public static final int MAIL_AUTH_FAIL              = 10001;
    //获取用户输入失败
    public static final int FAIL_USER_INPUT             = 10002;
    //监听服务器模块参数错误
    public static final int CONFIG_ERROR_SERVER         = 10003;
    //发件箱参数错误
    public static final int CONFIG_ERROR_SENDER         = 10004;
    //域名参数无效
    public static final int CONFIG_ERROR_DOMAIN         = 10005;
    //请求间隔参数无效
    public static final int CONFIG_ERROR_RE_INTERVAL    = 10006;
    //连续中断请求次数参数无效
    public static final int CONFIG_ERROR_RE_LOST_MAX    = 10007;
    //邮箱列表无效
    public static final int CONFIG_ERROR_EMAILS         = 10008;
    //host参数错误
    public static final int CONFIG_ERROR_HOST           = 10009;
    //port参数错误
    public static final int CONFIG_ERROR_PORT           = 10010;
    //address参数错误
    public static final int CONFIG_ERROR_ADD            = 10011;
    //配置文件载入失败
    public static final int CONFIG_LOAD_ERROR           = 10012;
    //收件人错误
    public static final int MAIL_RECEIVER_ERROR         = 10013;
    //启动定时器失败
    public static final int TIMER_START_ERROR           = 10014;
}
