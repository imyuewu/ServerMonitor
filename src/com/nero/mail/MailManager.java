package com.nero.mail;

import com.nero.conf.Server;
import com.nero.utils.Consts;
import com.nero.utils.ErrorCode;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: nero
 * Date: 13-7-19
 * Time: 下午7:49
 * Introduction
 */

public class MailManager {

    private MailManager() {}

    /**
     * 发送邮件方法
     * @param receivers 收件人列表
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public static int sendEmail(String subject, String content, String... receivers) {

        int errorCode = ErrorCode.SUCCESS;
        if (receivers.length > 0) {
            Properties properties = new Properties();
            MailSender sender = MailSender.getMailSender();
            final String username = sender.getUsername();
            final String password = sender.getPassword();

            //设置邮件协议
            properties.put("mail.smtp.host", sender.getHostname());
            properties.put("mail.smtp.port", sender.getPort());
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.stmp.timeout", Consts.MAIL_SEND_TIME_OUT);

            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            };

            try {
                Session session = Session.getDefaultInstance(properties, auth);
                Message message = new MimeMessage(session);
                //发件人
                message.setFrom(new InternetAddress(sender.getAddress()));
                //收件人
                Address[] addresses = new InternetAddress[receivers.length];
                for (int i = 0; i < receivers.length; i++) {
                    addresses[i] = new InternetAddress(receivers[i]);
                }
                message.setRecipients(Message.RecipientType.TO, addresses);
                //主题
                message.setSubject(subject);
                //邮件正文
                message.setText(content);
                //发送时间
                message.setSentDate(new Date());

                Transport.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
                errorCode = ErrorCode.MAIL_SEND_FAIL;
            }
        } else {
            errorCode = ErrorCode.MAIL_RECEIVER_ERROR;
        }
        return errorCode;
    }

    /**
     * 邮件主题生成方法
     * @param server 监控服务器
     * @return subject 主题
     */
    public static String generateSubject(Server server) {
        StringBuilder subject = new StringBuilder();

        subject.append(server.getDomain());
        subject.append(" had crash!!");

        return subject.toString();
    }

    /**
     * 邮件内容生成方法
     * @param server 监控服务器
     * @return content 内容
     */
    public static String generateContent(Server server) {
        StringBuilder content = new StringBuilder();

        content.append("Your domain: ");
        content.append(server.getDomain());
        content.append(" had crashed! Please go check your server immediately!\n At ");
        content.append(new Date());

        return content.toString();
    }
}
