package com.epam.course.cp.service;

import com.epam.course.cp.dao.CategoryDao;
import com.epam.course.cp.dto.CategoryDTO;
import com.epam.course.cp.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplMockTest {

    private static final Integer ONCE = 1;

    private static final String CATEGORY_NAME = "TestCategory";
    private static final Integer CATEGORY_PARENT_ID = 1;

    private static final Integer FIRST_CATEGORY_ID = 1;
    private static final Integer SECOND_CATEGORY_ID = 2;
    private static final Integer WRONG_CATEGORY_ID = 1000;

    private static final String CATEGORY_DTO_NAME = "TestCategoryDTO";
    private static final Integer CATEGORY_DTO_PRODUCT_AMOUNT = 2500;

    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void shouldFindCategoryById() {

        Mockito.when(categoryDao.findById(any())).thenReturn(Optional.of(createCategory(FIRST_CATEGORY_ID)));
        Category category = categoryService.findById(FIRST_CATEGORY_ID);
        assertNotNull(category);
        assertTrue(FIRST_CATEGORY_ID == category.getCategoryId());

        Mockito.verify(categoryDao, Mockito.times(ONCE)).findById(anyInt());
        Mockito.verifyNoMoreInteractions(categoryDao);
    }

    @Test
    void shouldGetExceptionByWrongId() {

        Mockito.when(categoryDao.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            categoryService.findById(WRONG_CATEGORY_ID);
        });

        Mockito.verify(categoryDao, Mockito.times(ONCE)).findById(anyInt());
        Mockito.verifyNoMoreInteractions(categoryDao);
    }

    @Test
    void shouldFindAllCategoryDTOs() {

        Mockito.when(categoryDao.findAllCategoryDTOs())
                .thenReturn(Stream.of(createCategoryDTO(FIRST_CATEGORY_ID), createCategoryDTO(SECOND_CATEGORY_ID)));

        List<CategoryDTO> categoryDTOs = categoryService.findAllCategoryDTOs();
        assertNotNull(categoryDTOs);
        assertTrue(2 == categoryDTOs.size());

        Mockito.verify(categoryDao, Mockito.times(ONCE)).findAllCategoryDTOs();
        Mockito.verifyNoMoreInteractions(categoryDao);
    }

    @Test
    void shouldFindCategoryDTOsByParentId() {

        Mockito.when(categoryDao.findSubCategoryDTOsByCategoryId(anyInt()))
                .thenReturn(Stream.of(createCategoryDTO(FIRST_CATEGORY_ID),
                        createCategoryDTO(SECOND_CATEGORY_ID)));

        List<CategoryDTO> subCategoryDTOs = categoryService.findSubCategoryDTOsByCategoryId(CATEGORY_PARENT_ID);
        assertNotNull(subCategoryDTOs);
        assertTrue(2 == subCategoryDTOs.size());

        Mockito.verify(categoryDao, Mockito.times(ONCE)).findSubCategoryDTOsByCategoryId(anyInt());
        Mockito.verifyNoMoreInteractions(categoryDao);
    }

    @Test
    void shouldAddCategory() {

        Mockito.when(categoryDao.add(any())).thenReturn(Optional.of(createCategory(FIRST_CATEGORY_ID)));
        categoryService.add(any());
        Mockito.verify(categoryDao, Mockito.times(ONCE)).add(any());
        Mockito.verifyNoMoreInteractions(categoryDao);
    }

    @Test
    void shouldUpdateCategory() {

        categoryService.update(any(Category.class));
        Mockito.verify(categoryDao, Mockito.times(ONCE)).update(any());
        Mockito.verifyNoMoreInteractions(categoryDao);
    }

    @Test
    void shouldDeleteCategory() {

        categoryService.delete(anyInt());
        Mockito.verify(categoryDao, Mockito.times(ONCE)).delete(anyInt());
        Mockito.verifyNoMoreInteractions(categoryDao);
    }

    private Category createCategory(Integer id) {

        Category category = new Category();
        category.setCategoryId(id);
        category.setCategoryName(CATEGORY_NAME + id);
        category.setParentId(CATEGORY_PARENT_ID);

        return category;
    }

    private CategoryDTO createCategoryDTO(Integer id) {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(id);
        categoryDTO.setCategoryName(CATEGORY_DTO_NAME + id);
        categoryDTO.setProductsAmount(CATEGORY_DTO_PRODUCT_AMOUNT);

        return categoryDTO;
    }

}