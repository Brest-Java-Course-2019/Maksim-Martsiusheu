package com.epam.course.cp.service;

import com.epam.course.cp.dto.Filter;
import com.epam.course.cp.dto.ProductDTO;
import com.epam.course.cp.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Integer productId);

    List<ProductDTO> findAllProductDTOs();

    List<ProductDTO> findProductDTOsByCategoryId(Integer categoryId);

    List<ProductDTO> findProductDTOsByFilter(Filter filter);

    Product add(Product product);

    void update(Product product);

    void delete(Integer productId);
}
