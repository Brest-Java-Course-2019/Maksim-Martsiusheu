package com.epam.course.cp.web_app;

import com.epam.course.cp.model.Product;
import com.epam.course.cp.service.CategoryService;
import com.epam.course.cp.service.ProductService;
import com.epam.course.cp.web_app.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private ProductService productService;
    private CategoryService categoryService;

    private ProductValidator productValidator;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, ProductValidator productValidator) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productValidator = productValidator;
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
    public final String updateProduct(@Valid Product product, BindingResult result, @PathVariable Integer id) {

        LOGGER.debug("update product {}", product);

        productValidator.validate(product, result);
        if (result.hasErrors()) {
            return "product/" + id;
        } else {
            productService.add(product);
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
    public final String addProduct(@Valid Product product, BindingResult result) {

        LOGGER.debug("addProduct({})", product);

        productValidator.validate(product, result);

        if (result.hasErrors()) {
            return "product";
        } else {
            productService.add(product);
            return "redirect:/products";
        }
    }

    @GetMapping(value = "/products")
    public final String findAllProductsDTOs(Model model) {

        LOGGER.debug("find allProducts({})", model);
        model.addAttribute("categories", categoryService.findAllPossibleParentsForId(null));
        model.addAttribute("products", productService.findProductDTOsFromDateInterval(null, null));
        model.addAttribute("location", "products");
        return "products";

    }

    @PostMapping(value = "/products/filter")
    public final String findAllProductsDTOsFromDateInterval(@RequestParam(value = "from", required = false)
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateBegin,
                                                            @RequestParam(value = "to", required = false)
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateEnd,
                                                            @RequestParam(value = "id", required = false) Integer categoryId,
                                                            Model model) {

        LOGGER.debug("findAllProductsDTOsFromDateInterval ({}, {}, {})", dateBegin, dateEnd, categoryId);

        if (categoryId == null) {
            model.addAttribute("products", productService.findProductDTOsFromDateInterval(dateBegin, dateEnd));
        } else {
            model.addAttribute("products", productService.findProductDTOsByMixedFilter(dateBegin, dateEnd, categoryId));
        }

        model.addAttribute("categories", categoryService.findAllPossibleParentsForId(null));
        model.addAttribute("location", "products");

        return "products";
    }

    @GetMapping(value = "/products/{id}")
    public final String deleteProduct(@PathVariable Integer id) {
        LOGGER.debug("delete product by id = {}", id);

        productService.delete(id);

        return "redirect:/products";
    }

}
