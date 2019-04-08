package com.epam.course.cp.dao;

import com.epam.course.cp.dto.CategoryDTO;
import com.epam.course.cp.dto.SubCategoryDTO;
import com.epam.course.cp.model.Category;

import java.util.Optional;
import java.util.stream.Stream;

public interface CategoryDao {

    Stream<Category> findAll();

    Optional<Category> findById(Integer categoryId);

    Stream<CategoryDTO> findAllCategoryDTOs();

    Stream<SubCategoryDTO> findSubCategoryDTOsByCategoryId(Integer categoryId);

    Optional<Category> add(Category category);

    void update(Category category);

    void delete(Integer categoryId);

    Stream<Category> findAllPossibleParentsForId(Integer id);

}
