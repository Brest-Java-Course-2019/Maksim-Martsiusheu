package com.epam.brest.course.file;

import java.math.BigDecimal;

public class Coefficient {
    private BigDecimal lowBorder;
    private BigDecimal upperBorder;
    private BigDecimal value;

    public void setLowBorder(BigDecimal lowBorder) {
        this.lowBorder = lowBorder;
    }

    public void setUpperBorder(BigDecimal upperBorder) {
        this.upperBorder = upperBorder;
    }

    public void setValue(BigDecimal value) {
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
