package com.epam.brest.cources.calc;

import java.math.BigDecimal;

public interface Calculator {

    BigDecimal calc(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg);

    BigDecimal calc(DataItem data);
}
