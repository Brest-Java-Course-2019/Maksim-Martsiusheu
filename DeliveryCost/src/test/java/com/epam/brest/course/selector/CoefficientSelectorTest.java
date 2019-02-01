package com.epam.brest.course.selector;

import com.epam.brest.course.file.Coefficient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

class CoefficientSelectorTest {

    CoefficientSelector selector = new CoefficientSelector();
    ArrayList<Coefficient> coefficients;
    Coefficient firstCoefficient;
    Coefficient secondCoefficient;

    final static String PARAM_VALUE = "3.25";

    final static String FIRST_COEFFICIENT_LOW_BORDER = "0";
    final static String FIRST_COEFFICIENT_UPPER_BORDER = "5";
    final static String FIRST_COEFFICIENT_VALUE = "1.75";

    final static String SECOND_COEFFICIENT_LOW_BORDER = "5";
    final static String SECOND_COEFFICIENT_UPPER_BORDER = "10";
    final static String SECOND_COEFFICIENT_VALUE = "2.25";

    @BeforeEach
    void init() {
        coefficients = new ArrayList<>();

        firstCoefficient = new Coefficient(new BigDecimal(FIRST_COEFFICIENT_LOW_BORDER),
                                                  new BigDecimal(FIRST_COEFFICIENT_UPPER_BORDER),
                                                  new BigDecimal(FIRST_COEFFICIENT_VALUE));

        secondCoefficient = new Coefficient(new BigDecimal(SECOND_COEFFICIENT_LOW_BORDER),
                                                  new BigDecimal(SECOND_COEFFICIENT_UPPER_BORDER),
                                                  new BigDecimal(SECOND_COEFFICIENT_VALUE));

        coefficients.add(firstCoefficient);
        coefficients.add(secondCoefficient);
    }

    @Test
    void testSelectCoefficient() {
        Coefficient result = selector.selectCoefficient(coefficients,new BigDecimal(PARAM_VALUE));
        Assertions.assertEquals(firstCoefficient, result);
    }
}