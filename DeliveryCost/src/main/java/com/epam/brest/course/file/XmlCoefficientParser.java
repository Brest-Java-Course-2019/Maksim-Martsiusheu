package com.epam.brest.course.file;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class XmlCoefficientParser implements XmlParser {

    private final static String INFINITY = "infinity";
    private final static String INFINITY_VALUE = "10e10";
    private final static String TAG_ATTRIBUTE_FROM = "from";
    private final static String TAG_ATTRIBUTE_TO = "to";
    private final static String TAG_ATTRIBUTE_VALUE = "value";

    final static Logger LOGGER = LogManager.getLogger();

    public ArrayList<Coefficient> parse(String fileName, String tagName) throws XmlParserException {
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

            return createCoefficient(correctCoefficient);

        } catch (ParserConfigurationException e) {
            LOGGER.error("Incorrect parser configuration!", e);
            throw new XmlParserException(e.getMessage(), e.getCause());
        } catch (SAXException e) {
            LOGGER.error("Incorrect parser configuration!", e);
            throw new XmlParserException(e.getMessage(), e.getCause());
        } catch (NumberFormatException e) {
            LOGGER.debug("Incorrect tag attributes", e);
            throw new XmlParserException(e.getMessage(), e.getCause());
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found!", e);
            throw new XmlParserException(e.getMessage(), e.getCause());
        } catch (IOException e) {
            LOGGER.error("Incorrect parse data.", e);
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

    private ArrayList<Coefficient> createCoefficient(ArrayList<Node> nodeList) throws NumberFormatException {
        LOGGER.debug("Building coefficients start");

        ArrayList<Coefficient> coefficientList = new ArrayList<>();
        for (int i = 0; i < nodeList.size(); i++) {
            Coefficient coefficient = new Coefficient();

            NamedNodeMap attributes = nodeList.get(i).getAttributes();

            coefficient.setLowBorder(new BigDecimal(attributes.getNamedItem(TAG_ATTRIBUTE_FROM).getNodeValue()));

            if (attributes.getNamedItem(TAG_ATTRIBUTE_TO).getNodeValue().equals(INFINITY)) {
                coefficient.setUpperBorder(new BigDecimal(INFINITY_VALUE));
            } else {
                coefficient.setUpperBorder(new BigDecimal(attributes.getNamedItem(TAG_ATTRIBUTE_TO).getNodeValue()));
            }

            coefficient.setValue(new BigDecimal(attributes.getNamedItem(TAG_ATTRIBUTE_VALUE).getNodeValue()));

            coefficientList.add(coefficient);
        }

        LOGGER.debug("Building coefficients end");
        return coefficientList;

    }
}

