package com.epam.brest.course.file.parser;

import com.epam.brest.course.file.Coefficient;
import com.epam.brest.course.file.XmlCoefficientParser;
import com.epam.brest.course.file.XmlParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

class XmlCoefficientParserTest {
    private final static String INVALID_FILE_PATH = "src/invalidFolder/invalidTestCoefficient.xml";
    private final static String FILE_NAME = "testResources.xml";
    private final static String TAG_NAME = "pricePerKg";
    private final static String TAG_NAME_WITH_INVALID_ATTR = "pricePerKm";

    final static String FIRST_COEFFICIENT_LOW_BORDER = "0";
    final static String FIRST_COEFFICIENT_UPPER_BORDER = "5";
    final static String FIRST_COEFFICIENT_VALUE = "1.75";

    final static String SECOND_COEFFICIENT_LOW_BORDER = "5";
    final static String SECOND_COEFFICIENT_UPPER_BORDER = "10e10";
    final static String SECOND_COEFFICIENT_VALUE = "2.25";

    private XmlCoefficientParser parser = new XmlCoefficientParser();


    @Test
    void testInvalidFilePath() {
        Assertions.assertThrows(XmlParserException.class, () -> {
            parser.parse(INVALID_FILE_PATH, TAG_NAME);
        });
    }

    @Test
    void testInvalidAttribute() {
        Assertions.assertThrows(XmlParserException.class, () -> {
            parser.parse(FILE_NAME, TAG_NAME_WITH_INVALID_ATTR);
        });
    }


    @Test
    void testFileParsing() throws XmlParserException {
        ArrayList<Coefficient> coefficients = new ArrayList<>();

        Coefficient firstCoefficient = new Coefficient(new BigDecimal(FIRST_COEFFICIENT_LOW_BORDER),
                                                       new BigDecimal(FIRST_COEFFICIENT_UPPER_BORDER),
                                                       new BigDecimal(FIRST_COEFFICIENT_VALUE));

        Coefficient secondCoefficient = new Coefficient(new BigDecimal(SECOND_COEFFICIENT_LOW_BORDER),
                                                        new BigDecimal(SECOND_COEFFICIENT_UPPER_BORDER),
                                                        new BigDecimal(SECOND_COEFFICIENT_VALUE));

        coefficients.add(firstCoefficient);
        coefficients.add(secondCoefficient);

        ArrayList<Coefficient> result = parser.parse(FILE_NAME, TAG_NAME);

        Assertions.assertArrayEquals(coefficients.toArray(), result.toArray());
    }

}