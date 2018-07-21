package com.BarZad.parsers;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public abstract class XMLParser {

    private Document parsedXML;

    public Document getParsedXML() {
        return parsedXML;
    }

    public void setParsedXML(Document parsedXML) {
        this.parsedXML = parsedXML;
    }

    private void parseXml(String xmlPath) throws Exception{
        File fXmlFile = new File(xmlPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        setParsedXML(dBuilder.parse(fXmlFile));
        getParsedXML().getDocumentElement().normalize();
    }

    public void loadXMLDocument(String xmlPath){
        try{
            parseXml(xmlPath);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
