package com.epam.brest.course.calculator;

import java.math.BigDecimal;

public class DataItem {
    private BigDecimal weight;
    private BigDecimal distance;
    private BigDecimal pricePerKg;

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public void setPricePerKg(BigDecimal pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public BigDecimal getPricePerKg() {
        return pricePerKg;
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "weight=" + weight +
                ", distance=" + distance +
                ", pricePerKg=" + pricePerKg +
                '}';
    }
}
