package com.epam.course.cp.dao.mapper;

import com.epam.course.cp.dto.CategoryDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategoryDTOMapper implements RowMapper<CategoryDTO> {

    public final static String CATEGORY_DTO_ID = "category_id";
    public final static String CATEGORY_DTO_NAME = "category_name";
    public final static String CATEGORY_DTO_PARENT_ID = "parent_id";
    public final static String CATEGORY_DTO_PRODUCT_AMOUNT = "product_amount";

    @Override
    public CategoryDTO mapRow(ResultSet resultSet, int i) throws SQLException {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(resultSet.getInt(CATEGORY_DTO_ID));
        categoryDTO.setCategoryName(resultSet.getString(CATEGORY_DTO_NAME));
        categoryDTO.setParentId(resultSet.getInt(CATEGORY_DTO_PARENT_ID));
        categoryDTO.setProductsAmount(resultSet.getInt(CATEGORY_DTO_PRODUCT_AMOUNT));

        return categoryDTO;
    }
}
