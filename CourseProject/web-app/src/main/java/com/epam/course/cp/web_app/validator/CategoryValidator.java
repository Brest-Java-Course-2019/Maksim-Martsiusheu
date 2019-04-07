package com.epam.course.cp.web_app.validator;


import com.epam.course.cp.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {

    private static final Integer MAX_STRING_SIZE = 255;

    @Override
    public boolean supports(Class<?> aClass) {
        return Category.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "categoryName", "categoryName.empty");
        Category category = (Category) o;

        if (StringUtils.hasLength(category.getCategoryName())
                && category.getCategoryName().length() > MAX_STRING_SIZE) {
            errors.rejectValue("categoryName", "category.maxSize255");
        }
    }
}
