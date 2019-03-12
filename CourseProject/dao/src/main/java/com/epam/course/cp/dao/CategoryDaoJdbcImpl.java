package com.epam.course.cp.dao;

import com.epam.course.cp.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoryDaoJdbcImpl implements CategoryDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryDaoJdbcImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL = "SELECT category_id, category_name, parent_id FROM category";
    private final static String SELECT_BY_ID = "SELECT category_id, category_name, parent_id FROM category WHERE category_id = :category_id";
    private final static String INSERT = "INSERT INTO category (category_name, parent_id) VALUES (:category_name, :parent_id)";
    private final static String UPDATE = "UPDATE category SET category_name = :category_name, parent_id = :parent_id WHERE category_id = :category_id";
    private final static String DELETE = "DELETE FROM category WHERE category_id = :category_id";

    private final static String CATEGORY_ID = "category_id";
    private static final String CATEGORY_NAME = "category_name";
    private static final String PARENT_ID = "parent_id";

    public CategoryDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Category> findAll() {

        LOGGER.debug("findAll()");

        List<Category> categoryList = namedParameterJdbcTemplate.query(SELECT_ALL, new CategoryMapper());
        return categoryList;
    }

    @Override
    public Optional<Category> findById(Integer categoryId) {

        LOGGER.debug("findById({})", categoryId);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(CATEGORY_ID, categoryId);
        Category category = namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, namedParameters, new CategoryMapper());
        return Optional.ofNullable(category);
    }

    @Override
    public Optional<Category> add(Category category) {

        LOGGER.debug("add({})", category);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(CATEGORY_NAME, category.getCategoryName());
        namedParameters.addValue(PARENT_ID, category.getParentId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT, namedParameters, keyHolder);

        category.setCategoryId(keyHolder.getKey().intValue());
        return Optional.of(category);


    }

    @Override
    public void update(Category category) {

        LOGGER.debug("update({})", category);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(CATEGORY_ID, category.getCategoryId());
        namedParameters.addValue(CATEGORY_NAME, category.getCategoryName());
        namedParameters.addValue(PARENT_ID, category.getParentId());

        Optional.of(namedParameterJdbcTemplate.update(UPDATE, namedParameters))
                .filter(this::successfullyUpdate)
                .orElseThrow(()->new RuntimeException("Failed to update category in DB"));

    }

    private boolean successfullyUpdate(Integer numRowsUpdated) {
        return numRowsUpdated > 0;
    }


    @Override
    public void delete(Integer categoryId) {

        LOGGER.debug("delete({})", categoryId);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(CATEGORY_ID, categoryId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, namedParameters))
                .filter(this::successfullyUpdate)
                .orElseThrow(()->new RuntimeException("Failed to delete category"))
                ;

    }

    private class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet resultSet, int i) throws SQLException {

            Category category = new Category();

            category.setCategoryId(resultSet.getInt(CATEGORY_ID));
            category.setCategoryName(resultSet.getString(CATEGORY_NAME));
            category.setParentId(resultSet.getInt(PARENT_ID));

            return category;

        }
    }
}
