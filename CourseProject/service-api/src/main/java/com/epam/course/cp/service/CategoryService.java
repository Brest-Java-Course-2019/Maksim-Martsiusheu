package com.epam.course.cp.service;

import com.epam.course.cp.dto.CategoryDTO;
import com.epam.course.cp.dto.SubCategoryDTO;
import com.epam.course.cp.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Integer categoryId);

    List<CategoryDTO> findAllCategoryDTOs();

    List<SubCategoryDTO> findSubCategoryDTOsByCategoryId(Integer categoryId);

    Category add(Category category);

    void update(Category category);

    void delete(Integer categoryId);
}
