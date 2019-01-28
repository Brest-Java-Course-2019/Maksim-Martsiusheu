package com.epam.brest.course.file.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Coefficient {

    final static Logger LOGGER = LogManager.getLogger();

    private BigDecimal lowBorder;
    private BigDecimal upperBorder;
    private BigDecimal value;

    public void setLowBorder(BigDecimal lowBorder) {
        LOGGER.debug("Set low border");
        this.lowBorder = lowBorder;
    }

    public void setUpperBorder(BigDecimal upperBorder) {
        LOGGER.debug("Set upper border");
        this.upperBorder = upperBorder;
    }

    public void setValue(BigDecimal value) {
        LOGGER.debug("Set value");
        this.value = value;
    }

    public BigDecimal getLowBorder() {
        return lowBorder;
    }

    public BigDecimal getUpperBorder() {
        return upperBorder;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Coefficient{" +
                "lowBorder=" + lowBorder +
                ", upperBorder=" + upperBorder +
                ", value=" + value +
                '}';
    }
}
