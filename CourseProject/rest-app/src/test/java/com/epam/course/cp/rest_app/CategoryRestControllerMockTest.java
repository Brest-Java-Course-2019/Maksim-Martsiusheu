package com.epam.course.cp.rest_app;

import com.epam.course.cp.dto.CategoryDTO;
import com.epam.course.cp.dto.SubCategoryDTO;
import com.epam.course.cp.model.Category;
import com.epam.course.cp.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
class CategoryRestControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String TEST_CATEGORY_NAME = "TestCategory";
    private static final Integer PRODUCTS_AMOUNT = 2500;
    private static final Integer TEST_CATEGORY_PARENT_ID = null;

    private static final Integer ONCE = 1;
    private static final Integer FIRST_ID = 1;
    private static final Integer SECOND_ID = 2;

    private static Category FIRST_CATEGORY;
    private static Category SECOND_CATEGORY;

    private static ArrayList<Category> ARRAY_LIST_OF_CATEGORIES;

    private static CategoryDTO FIRST_CATEGORY_DTO;
    private static CategoryDTO SECOND_CATEGORY_DTO;

    private static ArrayList<CategoryDTO> ARRAY_LIST_OF_CATEGORY_DTOS;

    private static SubCategoryDTO FIRST_SUBCATEGORY_DTO;
    private static SubCategoryDTO SECOND_SUBCATEGORY_DTO;

    private static ArrayList<SubCategoryDTO> ARRAY_LIST_OF_SUBCATEGORY_DTOS;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryRestController controller;

    private MockMvc mockMvc;

    @BeforeAll
    static void init() {

        FIRST_CATEGORY = createCategory(FIRST_ID);
        SECOND_CATEGORY = createCategory(SECOND_ID);

        ARRAY_LIST_OF_CATEGORIES = new ArrayList<>();
        ARRAY_LIST_OF_CATEGORIES.add(FIRST_CATEGORY);
        ARRAY_LIST_OF_CATEGORIES.add(SECOND_CATEGORY);

        FIRST_CATEGORY_DTO = createCategoryDTO(FIRST_ID);
        SECOND_CATEGORY_DTO = createCategoryDTO(SECOND_ID);

        ARRAY_LIST_OF_CATEGORY_DTOS = new ArrayList<>();
        ARRAY_LIST_OF_CATEGORY_DTOS.add(FIRST_CATEGORY_DTO);
        ARRAY_LIST_OF_CATEGORY_DTOS.add(SECOND_CATEGORY_DTO);

        FIRST_SUBCATEGORY_DTO = createSubCategoryDTO(FIRST_ID);
        SECOND_SUBCATEGORY_DTO = createSubCategoryDTO(SECOND_ID);

        ARRAY_LIST_OF_SUBCATEGORY_DTOS = new ArrayList<>();
        ARRAY_LIST_OF_SUBCATEGORY_DTOS.add(FIRST_SUBCATEGORY_DTO);
        ARRAY_LIST_OF_SUBCATEGORY_DTOS.add(SECOND_SUBCATEGORY_DTO);
    }


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    void shouldFindAllCategories() throws Exception {

        Mockito.when(categoryService.findAll()).thenReturn(ARRAY_LIST_OF_CATEGORIES);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/categories")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string(MAPPER.writeValueAsString(ARRAY_LIST_OF_CATEGORIES))
                )
        ;

        Mockito.verify(categoryService, Mockito.times(ONCE)).findAll();

    }

    @Test
    void shouldFindCategoryById() throws Exception {

        Mockito.when(categoryService.findById(anyInt())).thenReturn(SECOND_CATEGORY);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/categories/2")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string(MAPPER.writeValueAsString(SECOND_CATEGORY))
                )
        ;

        Mockito.verify(categoryService, Mockito.times(ONCE)).findById(anyInt());
    }

    @Test
    void shouldFindAllCategoryDTOs() throws Exception {

        Mockito.when(categoryService.findAllCategoryDTOs()).thenReturn(ARRAY_LIST_OF_CATEGORY_DTOS);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/categories/dtos")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string(MAPPER.writeValueAsString(ARRAY_LIST_OF_CATEGORY_DTOS))
                )
        ;

        Mockito.verify(categoryService, Mockito.times(ONCE)).findAllCategoryDTOs();
    }

    @Test
    void shouldFindSubCategoryDTOsByCategoryId() throws Exception {

        Mockito.when(categoryService.findSubCategoryDTOsByCategoryId(anyInt()))
                .thenReturn(ARRAY_LIST_OF_SUBCATEGORY_DTOS);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/categories/1/dtos")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string(MAPPER.writeValueAsString(ARRAY_LIST_OF_SUBCATEGORY_DTOS))
                )
        ;

        Mockito.verify(categoryService, Mockito.times(ONCE)).findSubCategoryDTOsByCategoryId(anyInt());
    }

    @Test
    void shouldAddCategory() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/categories")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(MAPPER.writeValueAsString(FIRST_CATEGORY))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(categoryService, Mockito.times(ONCE)).add(any());
    }

    @Test
    void shouldUpdateCategory() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/categories")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(MAPPER.writeValueAsString(FIRST_CATEGORY))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(categoryService, Mockito.times(ONCE)).update(any());
    }

    @Test
    void shouldDeleteCategory() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/categories")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(MAPPER.writeValueAsString(1))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(categoryService, Mockito.times(ONCE)).delete(anyInt());
    }

    @AfterEach
    void afterEach() {

        Mockito.verifyNoMoreInteractions(categoryService);
    }

    private static Category createCategory(Integer id) {

        Category category = new Category();
        category.setCategoryId(id);
        category.setCategoryName(TEST_CATEGORY_NAME + id);
        category.setParentId(TEST_CATEGORY_PARENT_ID);

        return category;
    }

    private static CategoryDTO createCategoryDTO(Integer id) {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(id);
        categoryDTO.setCategoryName(TEST_CATEGORY_NAME + id);
        categoryDTO.setTotalProductsAmount(PRODUCTS_AMOUNT);

        return categoryDTO;
    }

    private static SubCategoryDTO createSubCategoryDTO(Integer id) {

        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setSubCategoryId(id);
        subCategoryDTO.setSubCategoryName(TEST_CATEGORY_NAME + id);
        subCategoryDTO.setProductsAmount(PRODUCTS_AMOUNT);

        return subCategoryDTO;
    }

}