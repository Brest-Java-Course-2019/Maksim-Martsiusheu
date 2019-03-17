package com.epam.course.cp.dao;

import com.epam.course.cp.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ProductDaoJdbcImpl implements ProductDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductDaoJdbcImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SELECT_ALL = "SELECT prod_id, prod_name, prod_amount, date_added, category_id  FROM product";
    private static final String SELECT_BY_ID = "SELECT prod_id, prod_name, prod_amount, date_added, category_id  FROM product WHERE prod_id = :prod_id";
    private static final String SELECT_BY_CATEGORY = "SELECT prod_id, prod_name, prod_amount, date_added, category_id  FROM product WHERE category_id = :category_id";
    private static final String SELECT_FROM_DATE_INTERVAL = "SELECT prod_id, prod_name, prod_amount, date_added, category_id  FROM product WHERE date_added BETWEEN :date_begin AND :date_end";
    private static final String INSERT = "INSERT INTO product (prod_name, prod_amount, date_added, category_id) VALUES (:prod_name, :prod_amount, :date_added, :category_id)";
    private static final String UPDATE = "UPDATE product SET prod_name = :prod_name, prod_amount = :prod_amount, date_added = :date_added, category_id = :category_id WHERE prod_id = :prod_id";
    private static final String DELETE = "DELETE FROM product WHERE prod_id = :prod_id";

    private static final String PRODUCT_ID = "prod_id";
    private static final String PRODUCT_NAME = "prod_name";
    private static final String PRODUCT_AMOUNT = "prod_amount";
    private static final String DATE_ADDED = "date_added";
    private static final String PRODUCT_CATEGORY_ID = "category_id";

    private static final String DATE_INTERVAL_BEGIN = "date_begin";
    private static final String DATE_INTERVAL_END = "date_end";

    public ProductDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Product> findAll() {

        LOGGER.debug("findAll()");

        List<Product> productList = namedParameterJdbcTemplate.query(SELECT_ALL, new ProductMapper());

        return productList;
    }

    @Override
    public Optional<Product> findById(Integer productId) {

        LOGGER.debug("findById()", productId);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_ID, productId);
        Product product = namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, namedParameters, new ProductMapper());

        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findByCategory(Integer categoryId) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_CATEGORY_ID, categoryId);

        List<Product> productList = namedParameterJdbcTemplate.query(SELECT_BY_CATEGORY, namedParameters, new ProductMapper());

        return productList;
    }

    @Override
    public List<Product> findFromDateInterval(LocalDate dateBegin, LocalDate dateEnd) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(DATE_INTERVAL_BEGIN, dateBegin);
        namedParameters.addValue(DATE_INTERVAL_END, dateEnd);

        List<Product> productList = namedParameterJdbcTemplate.query(SELECT_FROM_DATE_INTERVAL, namedParameters, new ProductMapper());

        return productList;
    }

    @Override
    public Optional<Product> add(Product product) {

        LOGGER.debug("add({})", product);

        MapSqlParameterSource namedParameters = getProductSqlParametersSource(product);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT, namedParameters, keyHolder);

        product.setProductId(keyHolder.getKey().intValue());
        return Optional.of(product);
    }

    @Override
    public void update(Product product) {

        LOGGER.debug("update({})", product);

        MapSqlParameterSource namedParameters = getProductSqlParametersSource(product);
        namedParameters.addValue(PRODUCT_ID, product.getProductId());

        Optional.of(namedParameterJdbcTemplate.update(UPDATE, namedParameters))
                .filter(this::successfullyUpdate)
                .orElseThrow(() -> new RuntimeException("Failed to update product in DB"));
    }

    @Override
    public void delete(Integer productId) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_ID, productId);

        Optional.of(namedParameterJdbcTemplate.update(DELETE, namedParameters))
                .filter(this::successfullyUpdate)
                .orElseThrow(() -> new RuntimeException("Failed to delete product in DB"));
    }

    private boolean successfullyUpdate(Integer numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    private MapSqlParameterSource getProductSqlParametersSource(Product product) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(PRODUCT_NAME, product.getProductName());
        namedParameters.addValue(PRODUCT_AMOUNT, product.getProductAmount());
        namedParameters.addValue(DATE_ADDED, product.getDateAdded());
        namedParameters.addValue(PRODUCT_CATEGORY_ID, product.getCategoryId());

        return namedParameters;
    }

    private class ProductMapper implements RowMapper<Product> {

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
}
