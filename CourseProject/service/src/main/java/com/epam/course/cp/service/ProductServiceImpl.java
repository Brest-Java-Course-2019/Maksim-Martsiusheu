package com.epam.course.cp.service;

import com.epam.course.cp.dao.ProductDao;
import com.epam.course.cp.dto.ProductDTO;
import com.epam.course.cp.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAll() {

        LOGGER.debug("findAll()");
        return productDao.findAll().collect(Collectors.toList());
    }

    @Override
    public Product findById(Integer productId) {

        LOGGER.debug("findById({})", productId);
        return productDao.findById(productId)
                .orElseThrow(() -> new RuntimeException("Field to get product from DB"));
    }

    @Override
    public List<ProductDTO> findAllProductDTOs() {

        LOGGER.debug("findAllProductDTOs()");
        return productDao.findAllProductDTOs().collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductDTOsByCategoryId(Integer categoryId) {

        LOGGER.debug("findProductDTOsByCategoryId({})", categoryId);
        return productDao.findProductDTOsByCategoryId(categoryId).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductDTOsFromDateInterval(LocalDate dateBegin, LocalDate dateEnd) {

        LOGGER.debug("findProductDTOsFromDateInterval({}, {})", dateBegin, dateEnd);
        return productDao.findProductDTOsFromDateInterval(dateBegin, dateEnd).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductDTOsByMixedFilter(LocalDate dateBegin, LocalDate dateEnd, Integer categoryId) {

        LOGGER.debug("findProductDTOsByMixedFilter({}, {}, {})", dateBegin, dateEnd, categoryId);
        return productDao.findProductDTOsByMixedFilter(dateBegin, dateEnd, categoryId).collect(Collectors.toList());
    }

    @Override
    public Product add(Product product) {

        LOGGER.debug("add({})", product);
        return productDao.add(product)
                .orElseThrow(()->new RuntimeException("Field to add product to DB"));
    }

    @Override
    public void update(Product product) {

        LOGGER.debug("update({})", product);
        productDao.update(product);
    }

    @Override
    public void delete(Integer productId) {

        LOGGER.debug("delete({})", productId);
        productDao.delete(productId);
    }
}
