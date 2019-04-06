package com.epam.course.cp.rest_app;

import com.epam.course.cp.dto.CategoryDTO;
import com.epam.course.cp.dto.SubCategoryDTO;
import com.epam.course.cp.model.Category;
import com.epam.course.cp.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRestController.class);

    private CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "")
    public List<Category> findAll() {

        LOGGER.debug("get all categories");
        return categoryService.findAll();
    }

    @GetMapping(value = "/info")
    public List<CategoryDTO> findAllCategoryDTOs() {

        LOGGER.debug("get all categoryDTOs");
        return categoryService.findAllCategoryDTOs();
    }

    @GetMapping(value = "/{id}")
    public Category findById(@PathVariable Integer id) {

        LOGGER.debug("get category by id = {}", id);
        return categoryService.findById(id);
    }

    @GetMapping(value = "/info/{id}")
    public List<SubCategoryDTO> findSubCategoryDTOsByCategoryId(@PathVariable Integer id) {

        LOGGER.debug("get subCategoryDTOs by category id = {}", id);
        return categoryService.findSubCategoryDTOsByCategoryId(id);
    }

    @PostMapping
    public Category add(@RequestBody Category category) {

        LOGGER.debug("add category {}", category);
        return categoryService.add(category);
    }

    @PutMapping(value ="/{id}" )
    public void update(@RequestBody Category category) {

        LOGGER.debug("update category in DB {}", category);
        categoryService.update(category);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {

        LOGGER.debug("delete category with id = {} from DB", id);
        categoryService.delete(id);
    }

}
