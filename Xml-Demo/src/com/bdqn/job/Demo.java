package com.bdqn.job;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/7 14:28
 * FileName: Demo
 * Description: 使用dom4j对xml进行增删改查
 */
public class Demo {

    private Document document;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    /**
     * 查询节点操作
     */
    @Test
    public void selectNode() throws DocumentException {
        Document read = gainDoc();
        /*获取根元素*/
        Element rootElement = read.getRootElement();
        /*根据根元素一直查询到最后一级(单个)*/
        //String text = rootElement.element("book").element("bookname").getText();
        //System.out.println(text);
        /*查询meat节点下面的元素内容*/
        List<Element> list = rootElement.elements();
        for (Element next : list) {
            for (Element element : next.elements()) {
                System.out.println(element.getName() + ":" + element.getText());
            }
        }
    }


    /**
     * 节点操作
     */
    @Test
    public void nodeOperation() throws DocumentException {
        document = gainDoc();
        /*获取meat节点*/
        List<Element> elements = document.getRootElement().elements();
        for (Element element : elements) {
            Element discount = element.addElement("discount");
            /*设置内容*/
            discount.setText("5");
            /*删除节点*/
            //element.remove(discount);
        }
        /*保存doc对象*/
        setDocument(document);
        /*保存文件*/
        generateXml();
    }

    /**
     * 属性操作
     */
    @Test
    public void attrButeOperation() throws DocumentException {
        document = gainDoc();
        List<Element> elements = document.getRootElement().elements();
        for (Element element : elements) {
            Iterator<Attribute> attributeIterator = element.attributeIterator();
            /*遍历meat节点，获取属性名称和值*/
            while (attributeIterator.hasNext()) {
                Attribute next = attributeIterator.next();
                System.out.println(next.getName() + ":" + next.getText());
                /*修改属性*/
                if(next.getText().equals("内蒙古科尔沁")){
                    element.attribute("Origin").setText("内蒙古科尔沁草原");
                }
                /*删除属性*/
                //Attribute id = element.attribute("id");
                //element.remove(id);
            }
            /*新增属性*/
            element.addAttribute("name", element.attributeValue("Origin") + element.attributeValue("varieties"));
        }
        /*设置doc对象*/
        setDocument(document);
        generateXml();
    }


    /**
     * 生成xml
     */
    public void generateXml() {
        try {
            OutputFormat compactFormat = OutputFormat.createCompactFormat();
            compactFormat.setEncoding("UTF-8");
            XMLWriter xmlWriter = new XMLWriter(new FileWriter("D:\\materials\\JWeb\\Xml-Demo\\src\\com\\bdqn\\job\\shopping.xml"), compactFormat);
            xmlWriter.write(getDocument());
            xmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取doc对象
     */
    public Document gainDoc() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(new File("D:\\materials\\JWeb\\Xml-Demo\\src\\com\\bdqn\\job\\shopping.xml"));
    }
}
