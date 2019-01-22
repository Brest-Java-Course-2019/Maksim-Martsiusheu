package com.epam.brest.cources.parser;

import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public interface XmlParser {
    ArrayList<Coefficient> getCoefficients(final String filePath, final String tagName) throws XmlParserException;
}
