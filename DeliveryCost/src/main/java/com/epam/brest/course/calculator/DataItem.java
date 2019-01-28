package com.epam.brest.course.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class DataItem {
    private final static Logger LOGGER = LogManager.getLogger();

    private BigDecimal weight;
    private BigDecimal distance;
    private BigDecimal pricePerKg;
    private BigDecimal pricePerKm;

    public void setWeight(BigDecimal weight) {
        LOGGER.debug("Set weight");
        this.weight = weight;
    }

    public void setDistance(BigDecimal distance) {
        LOGGER.debug("Set distance");
        this.distance = distance;
    }

    public void setPricePerKg(BigDecimal pricePerKg) {
        LOGGER.debug("Set price per kg");
        this.pricePerKg = pricePerKg;
    }

    public void setPricePerKm(BigDecimal pricePerKm) {
        LOGGER.debug("Set price per km");
        this.pricePerKm = pricePerKm; }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public BigDecimal getPricePerKg() {
        return pricePerKg;
    }

    public BigDecimal getPricePerKm() { return pricePerKm; }

    @Override
    public String toString() {
        return "DataItem{" +
                "weight=" + weight +
                ", distance=" + distance +
                ", pricePerKg=" + pricePerKg +
                ", pricePerKm=" + pricePerKm +
                '}';
    }
}
