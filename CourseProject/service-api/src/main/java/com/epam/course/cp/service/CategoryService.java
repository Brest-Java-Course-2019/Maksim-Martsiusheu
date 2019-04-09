package com.epam.course.cp.service;

import com.epam.course.cp.dto.CategoryDTO;
import com.epam.course.cp.model.Category;

import java.util.List;

public interface CategoryService {

    Category findById(Integer categoryId);

    CategoryDTO findCategoryDTOById(Integer categoryId);

    List<CategoryDTO> findAllCategoryDTOs();

    List<CategoryDTO> findSubCategoryDTOsByCategoryId(Integer categoryId);

    Category add(Category category);

    ServiceResult update(Category category);

    ServiceResult delete(Integer categoryId);

    List<Category> findAllPossibleParentsForId(Integer id);
}
