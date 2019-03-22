package com.epam.course.cp.dao.mapper;

import com.epam.course.cp.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper implements RowMapper<Product> {

    public static final String PRODUCT_ID = "prod_id";
    public static final String PRODUCT_NAME = "prod_name";
    public static final String PRODUCT_AMOUNT = "prod_amount";
    public static final String DATE_ADDED = "date_added";
    public static final String PRODUCT_CATEGORY_ID = "category_id";

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {

        Product product = new Product();

        product.setProductId(resultSet.getInt(PRODUCT_ID));
        product.setProductName(resultSet.getString(PRODUCT_NAME));
        product.setProductAmount(resultSet.getInt(PRODUCT_AMOUNT));
        product.setDateAdded(resultSet.getDate(DATE_ADDED).toLocalDate());
        product.setCategoryId(resultSet.getInt(PRODUCT_CATEGORY_ID));

        return product;
    }
}
