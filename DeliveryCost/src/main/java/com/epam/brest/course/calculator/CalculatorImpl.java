package com.epam.brest.course.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class CalculatorImpl implements Calculator {

    final static Logger LOGGER = LogManager.getLogger();

    public BigDecimal calculateCost(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm) {
        LOGGER.debug("Calculate cost by BigDecimal params");

        BigDecimal weightCost = weight.multiply(pricePerKg);
        BigDecimal distanceCost = distance.multiply(pricePerKm);

        return weightCost.add(distanceCost);
    }

    public BigDecimal calculateCost(DataItem dataItem) {
        LOGGER.debug("Calculate cost by DataItem");

        BigDecimal weightCost = dataItem.getWeight().multiply(dataItem.getPricePerKg());
        BigDecimal distanceCost = dataItem.getDistance().multiply(dataItem.getPricePerKm());

        return weightCost.add(distanceCost);
    }
}
