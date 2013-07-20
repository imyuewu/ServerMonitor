package com.nero.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: nero
 * Date: 13-7-19
 * Time: 下午4:41
 * Introduction
 */

public class XMLHelper {

    /**
     * 根据文件获取DOM文件方法
     * @param file 文件
     * @return document DOM文档
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static Document getDocument(File file) throws ParserConfigurationException, IOException, SAXException {
        Document document = null;

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(file);

        return document;
    }

}
