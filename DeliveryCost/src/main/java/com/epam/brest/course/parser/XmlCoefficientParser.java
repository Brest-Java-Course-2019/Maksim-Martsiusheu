package com.epam.brest.course.parser;

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
    public ArrayList<Coefficient> getCoefficients(String filePath, String tagName) throws XmlParserException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(filePath);
            NodeList xmlCoefficients = doc.getElementsByTagName(tagName);

            ArrayList<Coefficient> coefficients = new ArrayList<>();

            for (int i = 0; i < xmlCoefficients.getLength(); i++) {
                NamedNodeMap coeffAttributes = xmlCoefficients.item(i).getAttributes();

                Coefficient coeff = new Coefficient();
                coeff.setLowBorder(new BigDecimal(coeffAttributes.getNamedItem("from").getNodeValue()));

                if (coeffAttributes.getNamedItem("to").getNodeValue().isEmpty()) {
                    coeff.setUpperBorder(new BigDecimal(Double.MAX_VALUE));
                } else {
                    coeff.setUpperBorder(new BigDecimal(coeffAttributes.getNamedItem("to").getNodeValue()));
                }
                coeff.setValue(new BigDecimal(xmlCoefficients.item(i).getTextContent()));

                coefficients.add(coeff);
            }

            return coefficients;

        } catch (ParserConfigurationException | SAXException e) {
            throw new XmlParserException(e.getCause());
        } catch (IOException e) {
            throw new XmlParserException(e.getCause());
        } catch (NumberFormatException e) {
            throw new XmlParserException(e.getCause());
        }
    }
}
