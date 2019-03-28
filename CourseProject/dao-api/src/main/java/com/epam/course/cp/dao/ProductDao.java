package com.epam.course.cp.dao;

import com.epam.course.cp.dto.ProductDTO;
import com.epam.course.cp.model.Product;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public interface ProductDao {

    Stream<Product> findAll();

    Optional<Product> findById(Integer productId);

    Stream<ProductDTO> findAllProductDTOs();

    Stream<ProductDTO> findProductDTOsByCategoryId(Integer categoryId);

    Stream<ProductDTO> findProductDTOsFromDateInterval(LocalDate dateBegin, LocalDate dateEnd);

    Stream<ProductDTO> findProductDTOsByMixedFilter(LocalDate dateBegin, LocalDate dateEnd, Integer categoryId);

    Optional<Product> add(Product product);

    void update(Product product);

    void delete(Integer productId);
}
