package com.epam.brest.course.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorImplTest {

    CalculatorImpl calculator = new CalculatorImpl();

    BigDecimal weight;
    BigDecimal distance;
    BigDecimal pricePerKg;
    BigDecimal pricePerKm;

    DataItem dataItem;

    @BeforeEach
    void setup() {

        weight = new BigDecimal("1");
        distance = new BigDecimal("1");
        pricePerKg = new BigDecimal("1");
        pricePerKm = new BigDecimal("1");

        dataItem = new DataItem();
        dataItem.setWeight(new BigDecimal("1"));
        dataItem.setDistance(new BigDecimal("1"));
        dataItem.setPricePerKg(new BigDecimal("1"));
        dataItem.setPricePerKm(new BigDecimal("1"));


    }

    @Test
    void testCalculateCostByDataItem() {
        BigDecimal result = calculator.calculateCost(dataItem);
        Assertions.assertEquals(new BigDecimal("2"), result);
    }

    @Test
    void testCalculateCostByBigDecimal() {
        BigDecimal result = calculator.calculateCost(weight, distance, pricePerKg, pricePerKm);
        Assertions.assertEquals(new BigDecimal("2"), result);
    }
}