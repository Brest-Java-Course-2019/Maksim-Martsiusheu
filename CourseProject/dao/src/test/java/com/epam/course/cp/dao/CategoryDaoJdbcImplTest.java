package com.epam.course.cp.dao;

import com.epam.course.cp.dto.CategoryDTO;
import com.epam.course.cp.dto.SubCategoryDTO;
import com.epam.course.cp.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:dao-test.xml"})
@Transactional
@Rollback
class CategoryDaoJdbcImplTest {

    private static final Integer CATEGORIES_AMOUNT = 7;
    private static final Integer CATEGORY_DTOS_AMOUNT = 4;

    private static final Integer PARENT_CATEGORY_ID = 1;
    private static final Integer PARENT_CATEGORY_DTO_AMOUNT = 2;

    private static final Integer TEST_CATEGORY_ID = 5;
    private static final String TEST_CATEGORY_NAME = "Bricks";
    private static final Integer TEST_CATEGORY_PARENT_ID = 1;

    private static final String NEW_CATEGORY_NAME = "New Category";
    private static final Integer NEW_CATEGORY_PARENT_ID = 1;

    private static final Integer CATEGORY_ID_TO_DELETE = 4;

    @Autowired
    private CategoryDao categoryDao;

    @Test
    void shouldFindAllCategories() {

        Stream<Category> categories = categoryDao.findAll();

        assertNotNull(categories);
        assertTrue(CATEGORIES_AMOUNT == categories.count());

    }

    @Test
    void shouldFindCategoryById() {

        Category category = categoryDao.findById(TEST_CATEGORY_ID).get();

        assertEquals(TEST_CATEGORY_NAME, category.getCategoryName());
        assertEquals(TEST_CATEGORY_PARENT_ID, category.getParentId());

    }

    @Test
    void shouldFindAllCategoryDTOs() {

        Stream<CategoryDTO> categoryDTOs = categoryDao.findAllCategoryDTOs();

        assertNotNull(categoryDTOs);
        assertTrue(CATEGORY_DTOS_AMOUNT == categoryDTOs.count());
    }

    @Test
    void shouldFindSubCategoryDTOsByCategoryId() {

        Stream<CategoryDTO> categoryDTOs = categoryDao.findSubCategoryDTOsByCategoryId(PARENT_CATEGORY_ID);

        assertNotNull(categoryDTOs);
        assertTrue(PARENT_CATEGORY_DTO_AMOUNT == categoryDTOs.count());
    }

    @Test
    void shouldAddNewCategory() {

        Stream<Category> categoriesBeforeInsert = categoryDao.findAll();

        Category category = new Category();
        category.setCategoryName(NEW_CATEGORY_NAME);
        category.setParentId(NEW_CATEGORY_PARENT_ID);

        Category addedCategory = categoryDao.add(category).get();
        assertNotNull(addedCategory.getCategoryId());

        Stream<Category> categoriesAfterInsert = categoryDao.findAll();

        assertTrue(1 == categoriesAfterInsert.count() - categoriesBeforeInsert.count());
    }

    @Test
    void shouldUpdateCategory() {

        Category category = new Category();
        category.setCategoryName(NEW_CATEGORY_NAME);
        category.setParentId(NEW_CATEGORY_PARENT_ID);

        Category newCategory = categoryDao.add(category).get();
        assertNotNull(newCategory.getCategoryId());

        newCategory.setCategoryName(NEW_CATEGORY_NAME + 1);

        categoryDao.update(newCategory);
        Category updatedCategory = categoryDao.findById(newCategory.getCategoryId()).get();

        assertEquals(NEW_CATEGORY_NAME + 1, updatedCategory.getCategoryName());

    }

    @Test
    void shouldDeleteCategory() {

        categoryDao.delete(CATEGORY_ID_TO_DELETE);

        assertThrows(DataAccessException.class, () -> {
            categoryDao.findById(CATEGORY_ID_TO_DELETE);
        });
    }
}
