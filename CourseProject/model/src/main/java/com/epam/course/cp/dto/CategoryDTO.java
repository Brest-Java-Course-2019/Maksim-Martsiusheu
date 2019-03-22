package com.epam.course.cp.dto;

public class CategoryDTO {

    private Integer categoryId;
    private String categoryName;
    private Integer totalProductsAmount;

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getTotalProductsAmount() {
        return totalProductsAmount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setTotalProductsAmount(Integer totalProductsAmount) {
        this.totalProductsAmount = totalProductsAmount;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryId=" + categoryId +
                ", totalProductsAmount=" + totalProductsAmount +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
