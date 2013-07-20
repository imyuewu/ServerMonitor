package com.nero.mail;

import com.nero.utils.ErrorCode;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: nero
 * Date: 13-7-20
 * Time: 上午9:23
 * 发件箱 使用单例模式
 */

public class MailSender {
    /**
     * 邮件服务器主机
     */
    private String hostname;
    /**
     * 注册邮箱用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱地址
     */
    private String address;
    /**
     * 邮箱服务器端口
     */
    private int port;

    private static final MailSender mailSender = new MailSender();

    private MailSender() {}

    public static MailSender getMailSender() {
        return mailSender;
    }

    public int auth() {
        int errorCode = ErrorCode.SUCCESS;
        try {
            /* 获取发件箱用户名和密码 */
            //jdk 1.6版本获取用户名和密码
            Console console = System.console();
            if (console != null) {
                setUsername(console.readLine("Enter mail's username: \n"));
                setPassword(new String(console.readPassword("Enter password: \n")));
            } else {
                //使用其他方法读取用户名和密码
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter mail's username: ");
                setUsername(bufferedReader.readLine());
                System.out.println("Enter password: ");
                setPassword(bufferedReader.readLine());
                bufferedReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorCode =  ErrorCode.FAIL_USER_INPUT;
        }
        /* 发送测试邮件 */
        if (MailManager.sendEmail("Test Mail!", "TestMail!", getAddress()) == ErrorCode.MAIL_SEND_FAIL) {
            errorCode =  ErrorCode.MAIL_AUTH_FAIL;
        }

        return errorCode;
    }


    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
