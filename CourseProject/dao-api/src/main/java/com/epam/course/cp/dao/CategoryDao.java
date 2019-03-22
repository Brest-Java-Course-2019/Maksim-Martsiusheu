package com.epam.course.cp.dao;

import com.epam.course.cp.model.Category;

import java.util.Optional;
import java.util.stream.Stream;

public interface CategoryDao {

    Stream<Category> findAll();

    Optional<Category> findById(Integer categoryId);

    Optional<Category> add(Category category);

    void update(Category category);

    void delete(Integer categoryId);

}
