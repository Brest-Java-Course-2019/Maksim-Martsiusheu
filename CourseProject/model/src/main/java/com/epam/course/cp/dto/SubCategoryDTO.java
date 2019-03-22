package com.epam.course.cp.dto;

public class SubCategoryDTO {

    private Integer subCategoryId;
    private Integer productsAmount;
    private String subCategoryName;

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public Integer getProductsAmount() {
        return productsAmount;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public void setProductsAmount(Integer productsAmount) {
        this.productsAmount = productsAmount;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    @Override
    public String toString() {
        return "SubCategoryDTO{" +
                "subCategoryId=" + subCategoryId +
                ", productsAmount=" + productsAmount +
                ", subCategoryName='" + subCategoryName + '\'' +
                '}';
    }
}
