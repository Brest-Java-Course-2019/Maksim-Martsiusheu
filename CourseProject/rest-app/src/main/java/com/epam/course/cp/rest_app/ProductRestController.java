package com.epam.course.cp.rest_app;

import com.epam.course.cp.dto.ProductDTO;
import com.epam.course.cp.model.Product;
import com.epam.course.cp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

    private ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "")
    public List<Product> findAll() {

        LOGGER.debug("get all products");
        return productService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable(value = "id") Integer productId) {

        LOGGER.debug("get product by id = {}", productId);
        return productService.findById(productId);
    }

    @GetMapping(value = "/info")
    public List<ProductDTO> findAllProductDTOs() {

        LOGGER.debug("get all productDTOs");
        return productService.findAllProductDTOs();
    }

    @GetMapping(value = "/filter")
    public List<ProductDTO> findProductDTOsByMixedFilter(
            @RequestParam(value = "from", defaultValue = "1970-01-01")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateBegin,
            @RequestParam(value = "to", defaultValue = "3000-01-01")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateEnd,
            @RequestParam(value = "id", required = false) Integer id) {

        LOGGER.debug("findProductDTOsBYMixedFilter({},{},{})", dateBegin, dateEnd, id);
        if (id == null) {
            return productService.findProductDTOsFromDateInterval(dateBegin, dateEnd);
        } else {
            return productService.findProductDTOsByMixedFilter(dateBegin, dateEnd, id);
        }
    }

    @PostMapping(value = "")
    public Product add(@RequestBody Product product) {

        LOGGER.debug("add({})", product);
        return productService.add(product);
    }

    @PutMapping(value = "/{id}")
    public void update(@RequestBody Product product) {

        LOGGER.debug("update({})", product);
        productService.update(product);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {

        LOGGER.debug("delete({})", id);
        productService.delete(id);
    }
}
