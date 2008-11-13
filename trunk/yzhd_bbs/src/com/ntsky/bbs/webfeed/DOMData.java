package com.ntsky.bbs.webfeed;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class DOMData {

    private static Logger __logger = Logger.getLogger(DOMData.class);
    private Document doc ;
    private static DOMData domData = null;
    
    public synchronized static DOMData getInstance(){
        if(domData == null){
            __logger.info("创建dom数据处理实例.....");
            domData = new DOMData();
        }
        return domData;
    }
    
    /**
     * 初始化document
     *
     */
    public void initDocument() {
        Document document = null;
        DocumentBuilderFactory docbf = DocumentBuilderFactory.newInstance();
        try {
            docbf.setNamespaceAware(true);
            DocumentBuilder documentBuilder = docbf.newDocumentBuilder();
            document = documentBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            __logger.error("创建DOM失败 : " + e.getMessage());
        }
        this.doc = document;
    } 
    
    /**
     * 创建RSS的root节点
     * @param doc
     * @param elementName
     * @param versionValue
     * @return
     */
    public Element newRSSRoot(String versionValue) {
        Element root = doc.createElement(Globals.RSS);
        root.setAttribute(Globals.VERSION,versionValue);
        doc.appendChild(root); 
        return root;
    }
    
    /**
     * 创建RDF的root节点
     * @param doc
     * @param elementName
     * @param versionValue
     * @return
     */
    public Element newRDFRoot() {
        Element root = doc.createElement(Globals.RDF);        
        root.setAttribute(Globals.RDF_XMLNS_RDF,Globals.RDF_XMLNS_RDF_VALUE);
        root.setAttribute(Globals.RDF_XMLNS_DC,Globals.RDF_XMLNS_DC_VALUE);
        root.setAttribute(Globals.RDF_XMLNS,Globals.RDF_XMLNS_VALUE);  
        doc.appendChild(root);
        return root;
    }    

    /**
     * 创建Atom的root节点
     * @param doc
     * @param elementName
     * @param versionValue
     * @return
     */
    public Element newAtomRoot(String version,String language) {
        Element root = doc.createElement(Globals.FEED);  
        root.setAttribute(Globals.VERSION,version);
        root.setAttribute(Globals.ATOM_XMLNS,Globals.ATOM_XMLNS_VALUE);
        root.setAttribute(Globals.ATOM_XMLNS_DC,Globals.ATOM_XMLNS_DC_VALUE);
        root.setAttribute(Globals.ATOM_XML_LANG,language);
        doc.appendChild(root);
        return root;
    } 
    
    /**
     * 添加文本类型的element
     * @param doc
     * @param parElement
     * @param elementName
     * @param elementText
     */
    public void addTextElement(Element parElement,String elementName, String elementText) {
        Element element = doc.createElement(elementName); 
        parElement.appendChild(element);
        Text text = doc.createTextNode(elementText); 
        element.appendChild(text); 
    }
    
    /**
     * 添加非标准文本的element
     * @param doc
     * @param parElement
     * @param elementName
     * @param elementText
     * @return
     */
    public void addCDATAElement(Element parElement,String elementName, String elementText) {
        Element element = doc.createElement(elementName); 
        parElement.appendChild(element);
        Text text = doc.createCDATASection(elementText); 
        element.appendChild(text); 
    }
    
    /**
     * 添加没有节点内容元素
     * @param doc
     * @param parElement
     * @param elementName
     * @return
     */
    public Element addElement(Element parElement, String elementName){
        Element element = doc.createElement(elementName);
        parElement.appendChild(element);
        return element;
    }    
    
    /**
     * 设置带属性元素节点值
     * @param parElement
     * @param elementName
     * @param attName
     * @param attValue
     * @param elementText
     */
    public void addTextAttElement(Element parElement,String elementName,String attName,String attValue,String elementText){
        Element element = doc.createElement(elementName); 
        parElement.appendChild(element);
        element.setAttribute(attName,attValue);
        Text text = doc.createTextNode(elementText); 
        element.appendChild(text);         
    }
    
    /**
     * 多种属性的情况
     * @param parElement
     * @param elementName
     * @param map
     */
    public void addMoreAttElement(Element parElement,String elementName,Map map){
        Element element = doc.createElement(elementName);
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry keyEntry = (Map.Entry) iterator.next();
            element.setAttribute(((String) keyEntry.getKey()).trim(),((String)keyEntry.getValue()).trim());
        }
        parElement.appendChild(element);
    }
    
    
    /**
     * 把当前document对象输出到指定文件
     *
     * @param destXMLFile
     *            String 指定输出的文件名
     */
    public void outPutXML(String destXMLFile) {
        try {
        	File file=new File(destXMLFile);
        	if(!file.exists()){
        		file.createNewFile();
        	}
            //创建一个DOMSource对象,该构造函数的参数可以是一个Document对象
            DOMSource source = new DOMSource(doc);
            //创建一个StreamResult对象,该构造函数的参数取为File对象
            StreamResult result = new StreamResult(new FileOutputStream(
                    file));
            //创建一个TransformerFactory对象,再由此创建Transformer对象
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            //设置输出属性
            Properties properties = transformer.getOutputProperties();
            properties.setProperty(OutputKeys.ENCODING, Globals.XML_ENCODING);
            transformer.setOutputProperties(properties);
            transformer.transform(source, result);
        } catch (Exception e) {
            __logger.error("输出xml产生异常 : " + e.getMessage());
        }
    }   
}
