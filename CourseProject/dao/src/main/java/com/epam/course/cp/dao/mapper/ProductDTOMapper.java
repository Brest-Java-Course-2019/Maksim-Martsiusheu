package com.epam.course.cp.dao.mapper;

import com.epam.course.cp.dto.ProductDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductDTOMapper implements RowMapper<ProductDTO> {

    public static final String PRODUCT_DTO_ID = "prod_id";
    public static final String PRODUCT_DTO_CATEGORY_NAME = "category_name";
    public static final String PRODUCT_DTO_SUBCATEGORY_NAME = "subcategory_name";
    public static final String PRODUCT_DTO_NAME = "prod_name";
    public static final String PRODUCT_DTO_AMOUNT = "prod_amount";
    public static final String PRODUCT_DTO_DATE_ADDED = "date_added";
    public static final String PRODUCT_DTO_CATEGORY_ID = "category_id";


    @Override
    public ProductDTO mapRow(ResultSet resultSet, int i) throws SQLException {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(resultSet.getInt(PRODUCT_DTO_ID));
        productDTO.setCategoryName(resultSet.getString(PRODUCT_DTO_CATEGORY_NAME));
        productDTO.setSubCategoryName(resultSet.getString(PRODUCT_DTO_SUBCATEGORY_NAME));
        productDTO.setCategoryName(resultSet.getString(PRODUCT_DTO_NAME));
        productDTO.setProductAmount(resultSet.getInt(PRODUCT_DTO_AMOUNT));
        productDTO.setDateAdded(resultSet.getDate(PRODUCT_DTO_DATE_ADDED).toLocalDate());
        productDTO.setCategoryId(resultSet.getInt(PRODUCT_DTO_CATEGORY_ID));

        return productDTO;
    }
}
