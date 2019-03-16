package com.epam.course.cp.dao;

import com.epam.course.cp.model.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> findAll();

    Optional<Product> findById(Integer productId);

    List<Product> findByCategory(Integer categoryId);

    List<Product> findFromDateInterval(LocalDate dateBegin, LocalDate dateEnd);

    Optional<Product> add(Product product);

    void update(Product product);

    void delete(Integer productId);
}
