package com.epam.brest.course.file;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class XmlCoefficientParser implements XmlParser {
    @Override
    public NodeList parse(String filePath, String tagName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(filePath);

            return doc.getElementsByTagName(tagName);

        } catch (ParserConfigurationException e) {

        } catch (IOException e) {

        }
    }
}

