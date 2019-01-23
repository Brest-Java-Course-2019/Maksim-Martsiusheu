package com.epam.brest.course.calculator;

import java.math.BigDecimal;

public class CalculatorImpl implements Calculator {
    @Override
    public BigDecimal calculateCost(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg) {
        return distance.multiply(weight.multiply(pricePerKg));
    }

    @Override
    public BigDecimal calculateCost(DataItem data) {
        return data.getDistance().multiply(data.getWeight().multiply(data.getPricePerKg()));
    }
}
