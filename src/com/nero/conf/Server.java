package com.nero.conf;

/**
 * Created with IntelliJ IDEA.
 * User: nero
 * Date: 13-7-19
 * Time: 下午2:32
 * 被监控对象
 */

public class Server {
    /**
     * 监控主机域名
     * e.g. www.imwooy.com
     */
    private String domain;

    /**
     * 向目标域名发送请求时间间隔, 单位ms
     * e.g. 60000
     */
    private long requestInterval;

    /**
     * 最大请求丢失次数
     * 连续丢失该变量个请求后,判定网站不可用
     * 再向目标email发送邮件
     * e.g. 5
     */
    private int requestLostMax;

    /**
     * 通知邮件email列表
     */
    private String[] emails;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

       public long getRequestInterval() {
        return requestInterval;
    }

    public void setRequestInterval(long requestInterval) {
        this.requestInterval = requestInterval;
    }

       public int getRequestLostMax() {
        return requestLostMax;
    }

    public void setRequestLostMax(int requestLostMax) {
        this.requestLostMax = requestLostMax;
    }

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Server) &&
                ((Server) object).getDomain().equals(getDomain());
    }
}
