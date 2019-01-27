package com.epam.brest.course.file.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.util.ArrayList;

public class XmlCoefficientParser implements XmlParser {

    public ArrayList<Node> parse(String filePath, String tagName) throws XmlParserException {
        try {

            ArrayList<Node> correctCoefficient = new ArrayList<Node>();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(filePath);
            NodeList nodeLists = doc.getElementsByTagName(tagName).item(0).getChildNodes();

            for (int i = 0; i < nodeLists.getLength(); i++) {
                if (nodeLists.item(i).getNodeType() != Node.TEXT_NODE) {
                    correctCoefficient.add(nodeLists.item(i));
                }
            }

            return correctCoefficient;

        } catch (ParserConfigurationException e) {
            throw new XmlParserException(e.getMessage(), e.getCause());
        } catch (SAXException e) {
            throw new XmlParserException(e.getMessage(), e.getCause());
        } catch (IOException e) {
            throw new XmlParserException(e.getMessage(), e.getCause());
        }
    }
}

