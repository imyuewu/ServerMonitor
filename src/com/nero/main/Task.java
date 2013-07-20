package com.nero.main;

import com.nero.conf.Server;
import com.nero.mail.MailManager;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: nero
 * Date: 13-7-19
 * Time: 下午6:54
 * Introduction
 */

public class Task extends TimerTask {

    private Server server;
    private static int lostCount;

    public Task(Server server) {
        this.server = server;
        lostCount = 0;
    }

    @Override
    public void run() {
        try {
            URL url = new URL("http://" + server.getDomain());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(30 * 1000);
            urlConnection.setReadTimeout(30 * 1000);
            int responseCode = urlConnection.getResponseCode();
//            System.out.println(responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) lostCount = 0;

        } catch (Exception e) {
            lostCount++;
            e.printStackTrace();
        } finally {
            if (lostCount >= server.getRequestLostMax()) {
                //发送邮件
                MailManager.sendEmail(MailManager.generateSubject(server), MailManager.generateContent(server), server.getEmails());
            }
        }
    }
}
