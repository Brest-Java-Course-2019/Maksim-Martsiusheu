package com.epam.brest.course.file;

import org.w3c.dom.NodeList;

import java.util.ArrayList;

public interface XmlParser {
    public NodeList parse (final String filePath, final String tagName);
}
