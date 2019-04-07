package com.epam.course.cp.web_app;

import com.epam.course.cp.model.Category;
import com.epam.course.cp.service.CategoryService;
import com.epam.course.cp.web_app.validator.CategoryValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private CategoryService categoryService;

    private CategoryValidator categoryValidator;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryValidator categoryValidator) {
        this.categoryService = categoryService;
        this.categoryValidator = categoryValidator;
    }

    @GetMapping(value = "/categories")
    public final String findAllCategoryDTOs(Model model) {

        LOGGER.debug("find allCategoryDTOs({})", model);
        model.addAttribute("categories", categoryService.findAllCategoryDTOs());
        return "categories";

    }

    @GetMapping(value = "/categories/info/{id}")
    public final String findSubCategoriesByCategoryId(@PathVariable Integer id, Model model) {

        LOGGER.debug("findSubCategoriesByCategoryId({}, {})", id, model);
        model.addAttribute("subcategories", categoryService.findSubCategoryDTOsByCategoryId(id));
        return "subcategories-table";
    }

    @GetMapping(value = "/category/{id}")
    public final String gotoUpdateCategory(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoUpdateCategory({}, {})", id, model);
        Category category = categoryService.findById(id);
        model.addAttribute("isNew", false);
        model.addAttribute("category", category);
        return "category";
    }

    @PostMapping(value = "/category/{id}")
    public final String updateCategory(@Valid Category category, BindingResult result) {

        LOGGER.debug("updateCategory({}, {})", category, result);
        categoryValidator.validate(category, result);

        if (result.hasErrors()) {
            return "category";
        } else {
            categoryService.update(category);
            return "redirect:/categories";
        }
    }

    @PostMapping(value = "/categories")
    public final String addCategory(@RequestBody Category category) {

        LOGGER.debug("add category: {}", category);
        categoryService.add(category);
        return "redirect:/categories";
    }

    @GetMapping(value = "/categories/{id}")
    public final String deleteCategory(@PathVariable Integer id){

        LOGGER.debug("delete");
        categoryService.delete(id);
        return "redirect:/categories";
    }


}
