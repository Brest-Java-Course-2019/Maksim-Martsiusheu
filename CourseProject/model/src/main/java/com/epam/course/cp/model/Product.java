package com.epam.course.cp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Product {

    private Integer productId;
    private String productName;
    private Integer productAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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
