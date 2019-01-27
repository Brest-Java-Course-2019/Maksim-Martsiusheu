package com.epam.brest.course.calculator;

import java.math.BigDecimal;

public class CalculatorImpl implements Calculator {

    public BigDecimal calculateCost(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm)
    {
        BigDecimal weightCost = weight.multiply(pricePerKg);
        BigDecimal distanceCost = distance.multiply(pricePerKm);

        return weightCost.add(distanceCost);
    }

    public BigDecimal calculateCost(DataItem data) {
        return data.getDistance().multiply(data.getWeight().multiply(data.getPricePerKg()));
    }
}
