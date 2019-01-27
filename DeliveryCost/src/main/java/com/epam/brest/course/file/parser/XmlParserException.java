package com.epam.brest.course.file;

public class XmlParserException extends Exception {
    public XmlParserException(Throwable cause) {
        super(cause);
    }

    public XmlParserException(String message) {
        super(message);
    }

    public XmlParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
