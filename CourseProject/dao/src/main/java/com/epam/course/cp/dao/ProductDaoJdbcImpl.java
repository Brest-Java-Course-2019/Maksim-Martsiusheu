package com.epam.course.cp.dao;

import com.epam.course.cp.dao.mapper.ProductMapper;
import com.epam.course.cp.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class ProductDaoJdbcImpl implements ProductDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductDaoJdbcImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ProductMapper productMapper;

    @Value("${product.selectAll}")
    private String getAllProductsSql;

    @Value("${product.selectById}")
    private String getProductByIdSql;

    @Value("${product.selectByCategory}")
    private String getProductByCategorySql;

    @Value("${product.selectFromDateInterval}")
    private String getProductsFromDateIntervalSql;

    @Value("${product.insert}")
    private String insertProductSql;

    @Value("${product.update}")
    private String updateProductSql;

    @Value("${product.delete}")
    private String deleteProductSql;

    private static final String PRODUCT_ID = "prod_id";
    private static final String PRODUCT_NAME = "prod_name";
    private static final String PRODUCT_AMOUNT = "prod_amount";
    private static final String DATE_ADDED = "date_added";
    private static final String PRODUCT_CATEGORY_ID = "category_id";

    private static final String DATE_INTERVAL_BEGIN = "date_begin";
    private static final String DATE_INTERVAL_END = "date_end";

    @Autowired
    public ProductDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ProductMapper productMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.productMapper = productMapper;
    }


    @Override
    public Stream<Product> findAll() {

        LOGGER.debug("findAll()");

        List<Product> productList = namedParameterJdbcTemplate.query(getAllProductsSql, productMapper);

        return productList.stream();
    }


    @Override
    public Optional<Product> findById(Integer productId) {

        LOGGER.debug("findById()", productId);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_ID, productId);
        Product product = namedParameterJdbcTemplate.queryForObject(getProductByIdSql, namedParameters, productMapper);

        return Optional.ofNullable(product);
    }


    @Override
    public Stream<Product> findByCategory(Integer categoryId) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_CATEGORY_ID, categoryId);

        List<Product> productList = namedParameterJdbcTemplate.query(getProductByCategorySql, namedParameters, productMapper);

        return productList.stream();
    }

    @Override
    public Stream<Product> findFromDateInterval(LocalDate dateBegin, LocalDate dateEnd) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(DATE_INTERVAL_BEGIN, dateBegin);
        namedParameters.addValue(DATE_INTERVAL_END, dateEnd);

        List<Product> productList = namedParameterJdbcTemplate.query(getProductsFromDateIntervalSql, namedParameters, productMapper);

        return productList.stream();
    }

    @Override
    public Optional<Product> add(Product product) {

        LOGGER.debug("add({})", product);

        MapSqlParameterSource namedParameters = getProductSqlParametersSource(product);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertProductSql, namedParameters, keyHolder);

        product.setProductId(keyHolder.getKey().intValue());
        return Optional.of(product);
    }

    @Override
    public void update(Product product) {

        LOGGER.debug("update({})", product);

        MapSqlParameterSource namedParameters = getProductSqlParametersSource(product);
        namedParameters.addValue(PRODUCT_ID, product.getProductId());

        Optional.of(namedParameterJdbcTemplate.update(updateProductSql, namedParameters))
                .filter(this::successfullyUpdate)
                .orElseThrow(() -> new RuntimeException("Failed to update product in DB"));
    }

    @Override
    public void delete(Integer productId) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(PRODUCT_ID, productId);

        Optional.of(namedParameterJdbcTemplate.update(deleteProductSql, namedParameters))
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
}
