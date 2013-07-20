package com.nero.conf;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: nero
 * Date: 13-7-19
 * Time: 下午7:16
 * 监控对象管理类
 */

public class ServerManager {

    /**
     * 保存Server对象的一个list
     * server对象是从配置文件中获取的
     * 一个监控对象
     */
    private static final Set<Server> serverList = new HashSet<Server>();

    private ServerManager() {}

    /**
     * 把server对象放入serverList中
     * @param server 监控对象
     */
    public static void addServer(Server server) {
        serverList.add(server);
    }

    /**
     * 获取server对象列表
     * @return
     */
    public static Set<Server> getServers() {
        return serverList;
    }
}
