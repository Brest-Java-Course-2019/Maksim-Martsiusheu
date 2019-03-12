package com.epam.course.cp.dao;

import com.epam.course.cp.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {

    List<Category> findAll();

    Optional<Category> findById(Integer categoryId);

    Optional<Category> add(Category category);

    void update(Category category);

    void delete(Integer categoryId);

}
