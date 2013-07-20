package com.nero.timer;

import com.nero.conf.Server;
import com.nero.conf.ServerManager;
import com.nero.main.Task;
import com.nero.utils.ErrorCode;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: nero
 * Date: 13-7-19
 * Time: 下午7:30
 * Introduction
 */

public class TimeManager {

    private TimeManager() {}

    /**
     * 启动定时器
     * @return -1 成功
     */
    public static int start() {
        int errorCode = ErrorCode.SUCCESS;
        try {
            for (Server server : ServerManager.getServers()) {
                Timer timer = new Timer();
                long timeInterval = server.getRequestInterval();
                TimerTask timerTask = new Task(server);
                timer.schedule(timerTask, new Date(), timeInterval);
            }
        } catch (Exception e) {
            errorCode = ErrorCode.TIMER_START_ERROR;
            e.printStackTrace();
        }
        return errorCode;
    }

}
