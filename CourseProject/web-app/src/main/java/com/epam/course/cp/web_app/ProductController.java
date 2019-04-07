package com.epam.course.cp.web_app;

import com.epam.course.cp.model.Product;
import com.epam.course.cp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/{id}")
    public final String gotoUpdateProduct(@PathVariable Integer id, Model model) {

        LOGGER.debug("go to update product with id ={}", id);

        model.addAttribute("product", productService.findById(id));
        model.addAttribute("isNew", false);
        model.addAttribute("location", "products");
        return "product";
    }

    @PostMapping(value = "/product/{id}")
    public final String updateProduct(Product product, Model model) {

        LOGGER.debug("update product {}", product);
        productService.update(product);
        model.addAttribute("location", "products");
        return "redirect:/products";
    }

    @GetMapping(value = "/product")
    public final String gotoAddProduct(Model model) {

        LOGGER.debug("gotoAddProduct({})", model);
        Product product = new Product();
        model.addAttribute("isNew", true);
        model.addAttribute("product", product);
        model.addAttribute("location", "products");
        return "product";
    }

    @PostMapping(value = "/product")
    public final String addProduct(Product product, Model model) {

        LOGGER.debug("addProduct({})", product);
        productService.add(product);
        model.addAttribute("location", "products");
        return "redirect:/products";
    }

    @GetMapping(value = "/products")
    public final String findAllProductsDTOs(Model model) {

        LOGGER.debug("find allProducts({})", model);
        model.addAttribute("products", productService.findAllProductDTOs());
        model.addAttribute("location", "products");
        return "products";

    }

    @PostMapping(value = "/products/filter")
    public final String findAllProductsDTOsFromDateInterval(@RequestParam(value = "from", defaultValue = "1970-01-01")
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateBegin,
                                                            @RequestParam(value = "to", defaultValue = "3000-01-01")
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateEnd,
                                                            @RequestParam(value = "id", required = false) Integer categoryId,
                                                            Model model) {

        LOGGER.debug("findAllProductsDTOsFromDateInterval ({}, {}, {})", dateBegin, dateEnd, categoryId);

        if(categoryId == null) {

            model.addAttribute("productsDTOs", productService.findProductDTOsFromDateInterval(dateBegin, dateEnd));

        } else {

            model.addAttribute("productDTOs", productService.findProductDTOsByMixedFilter(dateBegin, dateEnd, categoryId));
        }

        model.addAttribute("location", "products");

        return "productsDTOs";
    }

    @GetMapping(value = "/products/{id}")
    public final String deleteProduct(@PathVariable Integer id) {
        LOGGER.debug("delete product by id = {}", id);

        productService.delete(id);

        return "redirect:/products";
    }

}
