package com.epam.course.cp.web_app.validator;

import com.epam.course.cp.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "productName", "product.emptyName");

        Product product = (Product) o;
        if (product.getProductAmount() == null){
            errors.rejectValue("productAmount", "product.emptyAmount");
        } else {
            if (product.getProductAmount() < 0) {
                errors.rejectValue("productAmount", "product.negativeAmount");
            }
        }
    }
}
