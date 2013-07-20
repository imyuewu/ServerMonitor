package com.nero.conf;

import com.nero.mail.MailSender;
import com.nero.utils.Consts;
import com.nero.utils.ErrorCode;
import com.nero.utils.XMLHelper;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nero
 * Date: 13-7-19
 * Time: 下午2:23
 * Introduction
 */
public class Config {

    /**
     * 配置初始化方法
     * @return -1 成功
     */
    public static int init() {
        int errorCode;
        try {
            //获取DOM文档
            Document document = XMLHelper.getDocument(new File(Consts.CONFIG_FILE_PATH));
            //获取根元素 config
            Element rootElement = document.getDocumentElement();
            //获取server节点
            NodeList servers = ((Element) rootElement.getElementsByTagName(Consts.NODE_NAME_SERVERS).item(0)).
                    getElementsByTagName(Consts.NODE_NAME_SERVER);
            errorCode = initServer(servers);
            if (errorCode != ErrorCode.SUCCESS) return errorCode;

            //获取sender节点
            NodeList senders = rootElement.getElementsByTagName(Consts.NODE_NAME_SENDER);
            errorCode = initSender(senders);
            if (errorCode != ErrorCode.SUCCESS) return errorCode;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            errorCode = ErrorCode.CONFIG_LOAD_ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            errorCode = ErrorCode.CONFIG_LOAD_ERROR;
        } catch (SAXException e) {
            e.printStackTrace();
            errorCode = ErrorCode.CONFIG_LOAD_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            errorCode = ErrorCode.CONFIG_LOAD_ERROR;
        }

        return errorCode;
    }

    /**
     * 载入servers列表
     * @param servers server列表
     * @return -1 配置载入成功
     */
    private static int initServer(NodeList servers) {
        int errorCode = ErrorCode.SUCCESS;
        int serversLen = servers.getLength();
        if (serversLen > 0) {
            try {
                for (int i = 0; i < serversLen; i++) {
                    Element server = (Element) servers.item(i);
                    Server serverInstance = new Server();
                    String value = null;

                    //domain
                    NodeList domain = server.getElementsByTagName(Consts.TAG_NAME_DOMAIN);
                    value = domain.item(0).getTextContent();
                    if (domain.getLength() != 1 || value.equals(null) || value.equals("")) {
                        errorCode = ErrorCode.CONFIG_ERROR_DOMAIN;
                        return errorCode;
                    } else {
                        serverInstance.setDomain(value);
                    }

                    //request interval
                    NodeList reInterval = server.getElementsByTagName(Consts.TAG_NAME_RE_INTERVAL);
                    value = reInterval.item(0).getTextContent();
                    if (reInterval.getLength() != 1 || value.equals(null) || value.equals("")) {
                        errorCode = ErrorCode.CONFIG_ERROR_RE_INTERVAL;
                        return errorCode;
                    } else {
                        long interval = 1;
                        for (String str : value.split("\\*")){
                            if (!str.equals("")) {
                                interval *= Integer.valueOf(str);
                            }
                        }
                        serverInstance.setRequestInterval(interval);
                    }

                    //request lost max count
                    NodeList reLostMax = server.getElementsByTagName(Consts.TAG_NAME_RE_LOST_MAX);
                    value = reLostMax.item(0).getTextContent();
                    if (reLostMax.getLength() != 1 || value.equals(null) || value.equals("")) {
                        errorCode = ErrorCode.CONFIG_ERROR_RE_LOST_MAX;
                        return errorCode;
                    } else {
                        serverInstance.setRequestLostMax(Integer.valueOf(value));
                    }

                    //email
                    NodeList emails = server.getElementsByTagName(Consts.TAG_NAME_EMAILS);
                    value = emails.item(0).getTextContent();
                    if (emails.getLength() != 1 || value.equals(null) || value.equals("")) {
                        errorCode = ErrorCode.CONFIG_ERROR_EMAILS;
                        return errorCode;
                    } else {
                        List<String> temp = new ArrayList<String>();
                        for (String str : value.split("\\|")) {
                            if (!str.equals("") && str.indexOf('@') != -1) {
                                temp.add(str);
                            }
                        }
                        serverInstance.setEmails(temp.toArray(new String[1]));
                    }
                    //加入监控队列
                    ServerManager.addServer(serverInstance);
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorCode = ErrorCode.CONFIG_ERROR_SERVER;
            }
        } else {
            errorCode = ErrorCode.CONFIG_ERROR_SERVER;
        }
        return errorCode;
    }

    /**
     * 载入发件箱配置
     * @param senders 发件箱
     * @return -1 成功
     */
    private static int initSender(NodeList senders) {
        int errorCode = ErrorCode.SUCCESS;
        if (senders.getLength() != 1) {
            errorCode = ErrorCode.CONFIG_ERROR_SENDER;
        } else {
            try {
                Element sender = (Element) senders.item(0);
                MailSender mailSender = MailSender.getMailSender();
                String value = null;

                //host
                NodeList host = sender.getElementsByTagName(Consts.TAG_NAME_HOST);
                value = host.item(0).getTextContent();
                if (host.getLength() != 1 || value.equals(null) || value.equals("")) {
                    errorCode = ErrorCode.CONFIG_ERROR_HOST;
                    return errorCode;
                } else {
                    mailSender.setHostname(value);
                }
                //port
                NodeList port = sender.getElementsByTagName(Consts.TAG_NAME_PORT);
                value = port.item(0).getTextContent();
                if (port.getLength() != 1 || value.equals(null) || value.equals("")) {
                    errorCode = ErrorCode.CONFIG_ERROR_PORT;
                    return errorCode;
                } else {
                    mailSender.setPort(Integer.parseInt(value));
                }
                //address
                NodeList address = sender.getElementsByTagName(Consts.TAG_NAME_ADD);
                value = address.item(0).getTextContent();
                if (address.getLength() != 1 || value.equals(null) || value.equals("") || value.indexOf('@') == -1) {
                    errorCode = ErrorCode.CONFIG_ERROR_ADD;
                    return errorCode;
                } else {
                    mailSender.setAddress(value);
                }

                //验证邮箱
                do {
                    errorCode = mailSender.auth();
                } while (errorCode != ErrorCode.SUCCESS);

            } catch (Exception e) {
                e.printStackTrace();
                errorCode = ErrorCode.CONFIG_ERROR_SENDER;
            }
        }
        return errorCode;
    }
}
