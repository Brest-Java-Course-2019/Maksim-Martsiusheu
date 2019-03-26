package com.epam.course.cp.dto;

public class SubCategoryDTO {

    private Integer subCategoryId;
    private String subCategoryName;
    private Integer productsAmount;

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public Integer getProductsAmount() {
        return productsAmount;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public void setProductsAmount(Integer productsAmount) {
        this.productsAmount = productsAmount;
    }

    @Override
    public String toString() {
        return "SubCategoryDTO{" +
                "subCategoryId=" + subCategoryId +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", productsAmount=" + productsAmount +
                '}';
    }
}
