package com.epam.brest.course.parser;

import org.junit.jupiter.api.*;



class XmlCoefficientParserTest {

    final static String WRONG_PATH = "/wrongFolder/coefficient.xml";
    final static String FILE_NAME = "coefficient.xml";
    final static String TAG_NAME = "coefficient";

    XmlCoefficientParser parser = new XmlCoefficientParser();

    @Test
    void test() throws XmlParserException{
        String filePath=getClass().getClassLoader().getResource(FILE_NAME).getPath();
        Assertions.assertFalse(parser.getCoefficients(filePath, TAG_NAME).isEmpty());
    }

    @Test
    void testInvalidFilePath() {
        Assertions.assertThrows(XmlParserException.class, () -> {
            parser.getCoefficients(WRONG_PATH,TAG_NAME);
        });
    }

}