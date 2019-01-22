package com.epam.brest.cources.calc;

import java.math.BigDecimal;

public class CalculatorImpl implements Calculator {
    @Override
    public BigDecimal calc(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg) {
        return distance.multiply(weight.multiply(pricePerKg));
    }

    @Override
    public BigDecimal calc(DataItem data) {
        return data.getDistance().multiply(data.getWeight().multiply(data.getPricePerKg()));
    }
}
