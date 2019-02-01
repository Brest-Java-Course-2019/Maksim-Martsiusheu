package com.epam.brest.course.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Objects;

public class Coefficient {

    final static Logger LOGGER = LogManager.getLogger();

    private BigDecimal lowBorder;
    private BigDecimal upperBorder;
    private BigDecimal value;

    public Coefficient() {
    }

    public Coefficient(BigDecimal lowBorder, BigDecimal upperBorder, BigDecimal value) {
        this.lowBorder = lowBorder;
        this.upperBorder = upperBorder;
        this.value = value;
    }

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coefficient that = (Coefficient) o;
        return Objects.equals(lowBorder, that.lowBorder) &&
                Objects.equals(upperBorder, that.upperBorder) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowBorder, upperBorder, value);
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
