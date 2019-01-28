package com.epam.brest.course.file.parser;

import java.net.URL;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class XmlCoefficientParser implements XmlParser {

    final static Logger LOGGER = LogManager.getLogger();

    public ArrayList<Node> parse(String fileName, String tagName) throws XmlParserException {
        try {

            LOGGER.debug("Parsing start");

            ArrayList<Node> correctCoefficient = new ArrayList<>();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(getFilePath(fileName));
            NodeList nodeLists = doc.getElementsByTagName(tagName).item(0).getChildNodes();

            for (int i = 0; i < nodeLists.getLength(); i++) {
                if (nodeLists.item(i).getNodeType() != Node.TEXT_NODE) {
                    correctCoefficient.add(nodeLists.item(i));
                }
            }

            LOGGER.debug("Parsing end");

            return correctCoefficient;

        } catch (ParserConfigurationException e) {
            LOGGER.error("Incorrect parser configuration! Error: [{}]", e);
            throw new XmlParserException(e.getMessage(), e.getCause());
        } catch (SAXException e) {
            LOGGER.error("Incorrect parser configuration! Error: [{}]", e);
            throw new XmlParserException(e.getMessage(), e.getCause());
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found! Error: [{}]", e);
            throw new XmlParserException(e.getMessage(), e.getCause());
        } catch (IOException e) {
            LOGGER.error("Incorrect parse data. Error: [{}]", e);
            throw new XmlParserException(e.getMessage(), e.getCause());
        }
    }

    private String getFilePath(String fileName) throws FileNotFoundException {
        URL filePath = getClass().getClassLoader().getResource(fileName);
        if (filePath == null) {
            throw new FileNotFoundException();
        }
        return filePath.getPath();

    }
}

