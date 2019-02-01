package com.epam.brest.course.file;

import java.util.ArrayList;

public interface XmlParser {
    ArrayList<Coefficient> parse(final String filePath, final String tagName) throws XmlParserException;
}
