package com.epam.course.cp.dao.mapper;

import com.epam.course.cp.dto.SubCategoryDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SubCategoryDTOMapper implements RowMapper<SubCategoryDTO> {

    public static final String SUBCATEGORY_DTO_ID = "category_id";
    public static final String SUBCATEGORY_DTO_NAME = "category_name";
    public static final String SUBCATEGORY_DTO_PRODUCTS_AMOUNT = "product_amount";

    @Override
    public SubCategoryDTO mapRow(ResultSet resultSet, int i) throws SQLException {

        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setSubCategoryId(resultSet.getInt(SUBCATEGORY_DTO_ID));
        subCategoryDTO.setSubCategoryName(resultSet.getString(SUBCATEGORY_DTO_NAME));
        subCategoryDTO.setProductsAmount(resultSet.getInt(SUBCATEGORY_DTO_PRODUCTS_AMOUNT));

        return subCategoryDTO;
    }
}
