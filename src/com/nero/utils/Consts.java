package com.nero.utils;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created with IntelliJ IDEA.
 * User: nero
 * Date: 13-7-19
 * Time: 下午7:48
 * Introduction
 */

public interface Consts {
    //配置文件默认位置
    public static final String CONFIG_FILE_PATH = "./config.xml";
    //发送邮件默认超时时间 10s
    public static final int MAIL_SEND_TIME_OUT = 10 * 1000;
    //监听服务器标签名
    public static final String NODE_NAME_SERVERS = "servers";
    //server节点
    public static final String NODE_NAME_SERVER = "server";
    //发件箱标签名
    public static final String NODE_NAME_SENDER = "sender";
    //域名属性节点名
    public static final String TAG_NAME_DOMAIN = "domain";
    //请求时间间隔属性节点名
    public static final String TAG_NAME_RE_INTERVAL = "reInterval";
    //连续请求丢失属性节点名
    public static final String TAG_NAME_RE_LOST_MAX = "reLostMax";
    //服务器宕机通知邮箱列表属性节点名
    public static final String TAG_NAME_EMAILS = "email";
    //发件箱主机属性节点名
    public static final String TAG_NAME_HOST = "hostname";
    //发件箱端口属性节点名
    public static final String TAG_NAME_PORT = "port";
    //发件箱地址属性节点名
    public static final String TAG_NAME_ADD = "address";

}
