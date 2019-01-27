package com.epam.brest.course.file.parser;

import org.w3c.dom.Node;

import java.util.ArrayList;

public interface XmlParser {
    ArrayList<Node> parse(final String filePath, final String tagName) throws XmlParserException;
}
