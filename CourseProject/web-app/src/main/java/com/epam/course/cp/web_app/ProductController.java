package com.epam.course.cp.web_app;

import com.epam.course.cp.dto.Filter;
import com.epam.course.cp.model.Product;
import com.epam.course.cp.service.CategoryService;
import com.epam.course.cp.service.ProductService;
import com.epam.course.cp.web_app.validator.FilterValidator;
import com.epam.course.cp.web_app.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private ProductService productService;
    private CategoryService categoryService;

    private ProductValidator productValidator;
    private FilterValidator filterValidator;

    @Autowired
    public ProductController(ProductService productService
            , CategoryService categoryService
            , ProductValidator productValidator
            , FilterValidator filterValidator) {

        this.productService = productService;
        this.categoryService = categoryService;
        this.productValidator = productValidator;
        this.filterValidator = filterValidator;
    }

    @GetMapping(value = "/product/{id}")
    public final String gotoUpdateProduct(@PathVariable Integer id, Model model) {

        LOGGER.debug("go to update product with id ={}", id);

        model.addAttribute("product", productService.findById(id));
        model.addAttribute("isNew", false);
        model.addAttribute("categories", categoryService.findAllSubCategories());
        model.addAttribute("location", "products");
        return "product";
    }

    @PostMapping(value = "/product/{id}")
    public final String updateProduct(@Valid Product product, BindingResult result, Model model) {

        LOGGER.debug("update product {}", product);

        productValidator.validate(product, result);
        if (result.hasErrors()) {
            model.addAttribute(categoryService.findAllSubCategories());
            return "product";
        } else {
            productService.update(product);
            return "redirect:/products";
        }
    }

    @GetMapping(value = "/product")
    public final String gotoAddProduct(Model model) {

        LOGGER.debug("gotoAddProduct({})", model);
        Product product = new Product();
        model.addAttribute("isNew", true);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAllSubCategories());
        model.addAttribute("location", "products");
        return "product";
    }

    @PostMapping(value = "/product")
    public final String addProduct(@Valid Product product, BindingResult result, Model model) {

        LOGGER.debug("addProduct({})", product);

        productValidator.validate(product, result);

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAllSubCategories());
            return "product";
        } else {
            productService.add(product);
            return "redirect:/products";
        }
    }

    @GetMapping(value = "/products")
    public final String findAllProductsDTOs(Filter filter, Model model) {

        LOGGER.debug("find allProducts({})", model);
        model.addAttribute("filter", filter);
        model.addAttribute("categories", categoryService.findAllPossibleParentsForId(null));
        model.addAttribute("products", productService.findProductDTOsByFilter(new Filter()));
        model.addAttribute("location", "products");
        return "products";

    }

    @PostMapping(value = "/products/filter")
    public final String findAllProductsDTOsByFilter(@Valid Filter filter, BindingResult result, Model model) {

        LOGGER.debug("findAllProductsDTOsFromDateInterval ({})", filter);
        model.addAttribute("categories", categoryService.findAllPossibleParentsForId(null));
        model.addAttribute("location", "products");

        filterValidator.validate(filter, result);

        if (result.hasErrors()) {
            return "products";
        } else {
            model.addAttribute("products", productService.findProductDTOsByFilter(filter));
            return "products";
        }
    }

    @GetMapping(value = "/products/{id}")
    public final String deleteProduct(@PathVariable Integer id) {
        LOGGER.debug("delete product by id = {}", id);

        productService.delete(id);

        return "redirect:/products";
    }
}
