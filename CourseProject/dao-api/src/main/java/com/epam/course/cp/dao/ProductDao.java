package com.epam.course.cp.dao;

import com.epam.course.cp.model.Product;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public interface ProductDao {

    Stream<Product> findAll();

    Optional<Product> findById(Integer productId);

    Stream<Product> findByCategory(Integer categoryId);

    Stream<Product> findFromDateInterval(LocalDate dateBegin, LocalDate dateEnd);

    Optional<Product> add(Product product);

    void update(Product product);

    void delete(Integer productId);
}
