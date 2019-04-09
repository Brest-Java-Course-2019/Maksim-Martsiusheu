package com.epam.course.cp.dao;

import com.epam.course.cp.dto.CategoryDTO;
import com.epam.course.cp.model.Category;

import java.util.Optional;
import java.util.stream.Stream;

public interface CategoryDao {

    Stream<Category> findAll();

    Optional<Category> findById(Integer categoryId);

    Stream<CategoryDTO> findAllCategoryDTOs();

    Optional<CategoryDTO> findCategoryDTOById(Integer id);

    Stream<CategoryDTO> findSubCategoryDTOsByCategoryId(Integer id);

    Optional<Category> add(Category category);

    void update(Category category);

    void delete(Integer categoryId);

    Stream<Category> findAllPossibleParentsForId(Integer id);

}
