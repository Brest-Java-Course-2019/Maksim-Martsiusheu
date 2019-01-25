package com.epam.brest.course.calculator;


import org.junit.jupiter.api.*;

import java.math.BigDecimal;



class CalculatorImplTest {

    DataItem dataItem;
    Calculator calculator = new CalculatorImpl();

    @BeforeEach
    void init(){
        dataItem = new DataItem();
        dataItem.setWeight(new BigDecimal("1"));
        dataItem.setDistance(new BigDecimal("1"));
        dataItem.setPricePerKg(new BigDecimal("1"));

        System.out.println("@BeforeEach");
    }

    @Test
    void testCorrectCalculation(){
        BigDecimal result = calculator.calculateCost(dataItem);
        Assertions.assertEquals(new BigDecimal("1"), result);
    }


}