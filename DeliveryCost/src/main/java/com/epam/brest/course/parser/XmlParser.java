package com.epam.brest.course.parser;

import java.util.ArrayList;

public interface XmlParser {
    ArrayList<Coefficient> getCoefficients(final String filePath, final String tagName) throws XmlParserException;
}
