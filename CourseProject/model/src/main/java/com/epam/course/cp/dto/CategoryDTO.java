package com.epam.course.cp.dto;

public class CategoryDTO {

    private Integer categoryId;
    private String categoryName;
    private Integer parentId;
    private Integer productsAmount;

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getProductsAmount() {
        return productsAmount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setProductsAmount(Integer productsAmount) {
        this.productsAmount = productsAmount;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", parentId=" + parentId +
                ", productsAmount=" + productsAmount +
                '}';
    }
}
