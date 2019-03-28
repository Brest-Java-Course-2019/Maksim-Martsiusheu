package com.epam.course.cp.dao;

import com.epam.course.cp.dao.mapper.ProductDTOMapper;
import com.epam.course.cp.dao.mapper.ProductMapper;
import com.epam.course.cp.dto.ProductDTO;
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
    private final ProductDTOMapper productDTOMapper;

    @Value("${product.selectAll}")
    private String getAllProductsSql;

    @Value("${product.selectById}")
    private String getProductByIdSql;

    @Value("${productDTO.selectAll}")
    private String getAllProductDTOsSql;

    @Value("${productDTO.selectByCategoryId}")
    private String getProductDTOsByCategorySql;

    @Value("${productDTO.selectFromDateInterval}")
    private String getProductDTOsFromDateIntervalSql;

    @Value("${productDTO.selectByMixedFilter}")
    private String getProductDTOsByMixedFilterSql;

    @Value("${product.insert}")
    private String insertProductSql;

    @Value("${product.update}")
    private String updateProductSql;

    @Value("${product.delete}")
    private String deleteProductSql;

    private static final String DATE_INTERVAL_BEGIN = "date_begin";
    private static final String DATE_INTERVAL_END = "date_end";

    @Autowired
    public ProductDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                              ProductMapper productMapper,
                              ProductDTOMapper productDTOMapper) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.productMapper = productMapper;
        this.productDTOMapper = productDTOMapper;
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

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(ProductMapper.PRODUCT_ID, productId);
        Product product = namedParameterJdbcTemplate.queryForObject(getProductByIdSql, namedParameters, productMapper);

        return Optional.ofNullable(product);
    }

    @Override
    public Stream<ProductDTO> findAllProductDTOs() {

        LOGGER.debug("findAllProductsDTOs()");

        List<ProductDTO> productDTOList = namedParameterJdbcTemplate.query(getAllProductDTOsSql, productDTOMapper);
        return productDTOList.stream();
    }

    @Override
    public Stream<ProductDTO> findProductDTOsByCategoryId(Integer categoryId) {

        LOGGER.debug("findProductDTOsByCategory({})", categoryId);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(ProductDTOMapper.PRODUCT_DTO_CATEGORY_ID, categoryId);
        List<ProductDTO> productDTOList = namedParameterJdbcTemplate.query(getProductDTOsByCategorySql, namedParameters, productDTOMapper);

        return productDTOList.stream();
    }

    @Override
    public Stream<ProductDTO> findProductDTOsFromDateInterval(LocalDate dateBegin, LocalDate dateEnd) {

        LOGGER.debug("findProductDTOsFromDateInterval({}, {})", dateBegin, dateEnd);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(DATE_INTERVAL_BEGIN, dateBegin);
        namedParameters.addValue(DATE_INTERVAL_END, dateEnd);

        List<ProductDTO> productDTOList = namedParameterJdbcTemplate.query(getProductDTOsFromDateIntervalSql, namedParameters, productDTOMapper);

        return productDTOList.stream();
    }

    @Override
    public Stream<ProductDTO> findProductDTOsByMixedFilter(LocalDate dateBegin, LocalDate dateEnd, Integer categoryId) {

        LOGGER.debug("findProductDTOsByMixedFilter({}, {}, {})", dateBegin, dateEnd, categoryId);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(DATE_INTERVAL_BEGIN, dateBegin);
        namedParameters.addValue(DATE_INTERVAL_END, dateEnd);
        namedParameters.addValue(ProductDTOMapper.PRODUCT_DTO_CATEGORY_ID, categoryId);

        List<ProductDTO> productDTOList = namedParameterJdbcTemplate.query(getProductDTOsByMixedFilterSql, namedParameters, productDTOMapper);
        return productDTOList.stream();

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
        namedParameters.addValue(ProductMapper.PRODUCT_ID, product.getProductId());

        Optional.of(namedParameterJdbcTemplate.update(updateProductSql, namedParameters))
                .filter(this::successfullyUpdate)
                .orElseThrow(() -> new RuntimeException("Failed to update product in DB"));
    }

    @Override
    public void delete(Integer productId) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(ProductMapper.PRODUCT_ID, productId);

        Optional.of(namedParameterJdbcTemplate.update(deleteProductSql, namedParameters))
                .filter(this::successfullyUpdate)
                .orElseThrow(() -> new RuntimeException("Failed to delete product in DB"));
    }

    private boolean successfullyUpdate(Integer numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    private MapSqlParameterSource getProductSqlParametersSource(Product product) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(ProductMapper.PRODUCT_NAME, product.getProductName());
        namedParameters.addValue(ProductMapper.PRODUCT_AMOUNT, product.getProductAmount());
        namedParameters.addValue(ProductMapper.DATE_ADDED, product.getDateAdded());
        namedParameters.addValue(ProductMapper.PRODUCT_CATEGORY_ID, product.getCategoryId());

        return namedParameters;
    }
}
