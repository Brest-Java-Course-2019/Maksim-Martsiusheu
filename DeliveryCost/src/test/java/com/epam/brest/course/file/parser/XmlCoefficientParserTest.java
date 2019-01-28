package com.epam.brest.course.file.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XmlCoefficientParserTest {
    final static String INVALID_FILE_PATH = "src/invalidFolder/invalidTestCoefficient.xml";
    final static String FILE_NAME = "testResources.xml";
    final static String TAG_NAME = "pricePerKg";

    XmlCoefficientParser parser = new XmlCoefficientParser();

    @Test
    void testInvalidFilePath() {
        Assertions.assertThrows(XmlParserException.class, () -> {
            parser.parse(INVALID_FILE_PATH, TAG_NAME);
        });
    }

    @Test
    void testFileParsing() throws XmlParserException {
        Assertions.assertFalse(parser.parse(FILE_NAME, TAG_NAME).isEmpty());
    }

}