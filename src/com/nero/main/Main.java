package com.nero.main;

import com.nero.conf.ServerManager;
import com.nero.timer.TimeManager;
import com.nero.utils.ErrorCode;
import com.nero.conf.Config;

/**
 * Created with IntelliJ IDEA.
 * User: nero
 * Date: 13-7-19
 * Time: 下午7:27
 * 程序入口
 */

public class Main {
    public static int init() {
        int errorCode = ErrorCode.SUCCESS;
        //初始化配置文件
        errorCode = Config.init();
        if (errorCode != ErrorCode.SUCCESS) return errorCode;
        //初始化定时器
        errorCode = TimeManager.start();

        return errorCode;
    }
    public static void main(String[] args) {
        int errorCode = Main.init();
        if (errorCode == ErrorCode.SUCCESS) {
            System.out.println("Monitoring ...");
        } else {
            System.out.println("Error occurs during monitoring! Please check the error code :" + errorCode);
        }
    }
}
