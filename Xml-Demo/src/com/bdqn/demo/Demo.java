package com.bdqn.demo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：用于演示Java解析XML
 */
public class Demo {
    public static void main(String[] args) {
    }
    /**
     * 方法描述：测试dom解析XML
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 10:21:32 2022/4/24c
     * @param
     * @return
     **/
    @Test
    public void test2() throws DocumentException, IOException, ParserConfigurationException, SAXException {
        long firstTime = System.currentTimeMillis();
        DOMReader domReader = new DOMReader();
        /*读取XMl文件*/
        String path="D:\\materials\\JWeb\\Xml-Demo\\src\\com\\bdqn\\demo\\shopping.xml";
        /*创建解析工厂*/
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        /*获取解析器*/
        DocumentBuilder builder = factory.newDocumentBuilder();
        /*获取document对象*/
        org.w3c.dom.Document parse = builder.parse(path);
        /*获取根节点*/
        Element rootElement = domReader.read(parse).getRootElement();
        List<Element> elements = rootElement.elements();
//        System.out.println(elements.get(0).getName());
        for (Iterator<Element> elementIterator = elements.iterator();elementIterator.hasNext();){
//            System.out.println(elementIterator.next().getName());
//            System.out.println(elementIterator.next().elementIterator().next().getText());
            for (Iterator<Element>  iterator= elementIterator.next().elementIterator();iterator.hasNext();){
                System.out.println(iterator.next().getText());
            }
        }
        long lastTime = System.currentTimeMillis();
        System.out.println(lastTime-firstTime);
       /* while (elementIterator.hasNext()) {

        }*/
        Iterator<Element> iterator = rootElement.elementIterator();
        while (iterator.hasNext()) {
            Element element = rootElement.element(iterator.next().getName());
            for (Iterator<Element> iterator1 = element.elementIterator();iterator1.hasNext();){
//                System.out.println(iterator1.next().getText());
            }
        }

        /*Element element1 = (Element) element.element(name);
        Iterator<Element> iterator2 = element1.elementIterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
        System.out.println();*/
    }
    @Test
    public void test1() throws ParserConfigurationException, DocumentException {
        /*创建解析工厂*/
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        /*获取解析器*/
        DocumentBuilder builder = factory.newDocumentBuilder();
        /*解析XML文档*/
//        Document parse = builder.parse("src/shopping.xml");
        /*先导入dom4j获取XPTH对象*/
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("D:\\materials\\JWeb\\Xml-Demo\\src\\com\\bdqn\\demo\\shopping.xml");
        System.out.println(document);
        /*获取解析的XML的元素树*/
        Element rootElement = document.getRootElement();
        System.out.println(rootElement);
        /*为指定元素进行解析*/
        Element book = rootElement.element("meat");
        /*解析路径*/
        System.out.println(book.getPath());
        /*读取元素内容*/
        System.out.println(book.elementText("price"));
        List<?> list = rootElement.elements();
        System.out.println(list);
        Element ele =(Element) list.get(1);
        /*获取属性*/
        System.out.println(ele.attributeValue("bookname"));
        /*遍历list*/
        Iterator<?> iterator = list.iterator();
        for (Iterator<?> it=book.elementIterator(); it.hasNext();){
            Element next = (Element)it.next();
//            System.out.println(next.getName()+":"+next.getText());
        }
        Iterator<Element> it1 = (Iterator<Element>) list.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next().getName());
            for (Iterator<Element>iterator1 =it1.next().elementIterator();iterator1.hasNext();){
                Element next = (Element)iterator1.next();
                System.out.println(next.getName() + ":"+next.getText());
            }
        }
    }

}
