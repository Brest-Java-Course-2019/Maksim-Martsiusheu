package com.epam.course.cp.model;

import java.time.LocalDate;

public class Product {

    private Integer productId;
    private String productName;
    private Integer productAmount;
    private LocalDate dateAdded;
    private Integer categoryId;

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public Integer getCategoryId() {
        return categoryId;
    }



    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productAmount=" + productAmount +
                ", dateAdded=" + dateAdded +
                ", categoryId=" + categoryId +
                '}';
    }
}
