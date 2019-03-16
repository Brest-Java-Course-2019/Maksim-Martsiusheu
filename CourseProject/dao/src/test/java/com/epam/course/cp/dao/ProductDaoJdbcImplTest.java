package com.epam.course.cp.dao;

import com.epam.course.cp.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:dao-test.xml"})
@Transactional
@Rollback
class ProductDaoJdbcImplTest {

    private final static Integer PRODUCTS_AMOUNT = 6;

    private static final Integer TEST_PRODUCT_ID = 1;
    private static final String TEST_PRODUCT_NAME = "Red bricks";
    private static final Integer TEST_PRODUCT_AMOUNT = 2500;
    private static final LocalDate TEST_PRODUCT_DATE_ADDED = LocalDate.parse("2012-06-18");
    private static final Integer TEST_PRODUCT_CATEGORY_ID = 5;

    private static final String NEW_PRODUCT_NAME = "Yellow bricks";
    private static final Integer NEW_PRODUCT_AMOUNT = 1750;
    private static final LocalDate NEW_PRODUCT_DATE_ADDED = LocalDate.parse("2016-07-16");
    private static final Integer NEW_PRODUCT_CATEGORY_ID = 1;

    private static final Integer BLOCKS_CATEGORY_ID = 6;
    private static final Integer BLOCKS_CATEGORY_PRODUCTS_AMOUNT = 3;

    private static final Integer PRODUCT_ID_TO_DELETE = 1;

    private static final LocalDate DATE_INTERVAL_BEGIN = LocalDate.parse("2018-01-01");
    private static final LocalDate DATE_INTERVAL_END = LocalDate.parse("2019-01-01");

    @Autowired
    private ProductDao productDao;

    @Test
    void shouldFindAllProducts() {

        List<Product> productList = productDao.findAll();

        assertNotNull(productList);
        assertTrue(PRODUCTS_AMOUNT == productList.size());
    }

    @Test
    void shouldFindProductById() {

        Product product = productDao.findById(TEST_PRODUCT_ID).get();

        assertEquals(TEST_PRODUCT_NAME, product.getProductName());
        assertEquals(TEST_PRODUCT_AMOUNT, product.getProductAmount());
        assertEquals(TEST_PRODUCT_DATE_ADDED, product.getDateAdded());
        assertEquals(TEST_PRODUCT_CATEGORY_ID, product.getCategoryId());
    }

    @Test
    void shouldFindProductByCategory() {

        List<Product> productList = productDao.findByCategory(BLOCKS_CATEGORY_ID);

        assertNotNull(productList);
        assertTrue(BLOCKS_CATEGORY_PRODUCTS_AMOUNT == productList.size());
    }

    @Test
    void shouldFindProductFromDateInterval() {

        List<Product> productList = productDao.findFromDateInterval(DATE_INTERVAL_BEGIN, DATE_INTERVAL_END);

        assertNotNull(productList);
        assertTrue(4 == productList.size());

    }

    @Test
    void shouldAddNewProduct() {

        List<Product> productsBeforeInsert = productDao.findAll();

        Product product = createProduct();

        Product newProduct = productDao.add(product).get();
        assertNotNull(newProduct.getProductId());

        List<Product> productsAfterInsert = productDao.findAll();
        assertTrue(1 == productsAfterInsert.size() - productsBeforeInsert.size());

    }

    @Test
    void shouldUpdateProduct() {

        Product product = createProduct();

        Product newProduct = productDao.add(product).get();
        assertNotNull(newProduct.getProductId());

        newProduct.setProductAmount(NEW_PRODUCT_AMOUNT + 10);
        newProduct.setProductName(NEW_PRODUCT_NAME + 10);

        productDao.update(newProduct);
        Product updateProduct = productDao.findById(newProduct.getProductId()).get();

        assertTrue(NEW_PRODUCT_AMOUNT + 10 == updateProduct.getProductAmount());
        assertEquals(NEW_PRODUCT_NAME + 10, updateProduct.getProductName());

    }

    @Test
    void delete() {

        productDao.delete(PRODUCT_ID_TO_DELETE);

        assertThrows(DataAccessException.class, () -> {
            productDao.findById(PRODUCT_ID_TO_DELETE);
        });
    }

    private Product createProduct() {

        Product product = new Product();

        product.setProductName(NEW_PRODUCT_NAME);
        product.setProductAmount(NEW_PRODUCT_AMOUNT);
        product.setDateAdded(NEW_PRODUCT_DATE_ADDED);
        product.setCategoryId(NEW_PRODUCT_CATEGORY_ID);

        return product;
    }
}