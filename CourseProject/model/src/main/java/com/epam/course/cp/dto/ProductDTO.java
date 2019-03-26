package com.epam.course.cp.dto;

import java.time.LocalDate;

public class ProductDTO {

    private Integer productId;
    private String categoryName;
    private String subCategoryName;
    private String productName;
    private Integer productAmount;
    private LocalDate dateAdded;
    private Integer categoryId;

    public Integer getProductId() {
        return productId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
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

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
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
        return "ProductDTO{" +
                "productId=" + productId +
                ", categoryName='" + categoryName + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", productName='" + productName + '\'' +
                ", productAmount=" + productAmount +
                ", dateAdded=" + dateAdded +
                ", categoryId=" + categoryId +
                '}';
    }
}
