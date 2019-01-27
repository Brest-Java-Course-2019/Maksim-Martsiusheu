package com.epam.brest.course.calculator;

import java.math.BigDecimal;

public interface Calculator {

    BigDecimal calculateCost(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm);

    BigDecimal calculateCost(DataItem data);
}
