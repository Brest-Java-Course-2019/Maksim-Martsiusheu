package com.epam.course.cp.service;

import com.epam.course.cp.dao.CategoryDao;
import com.epam.course.cp.dao.exception.DaoException;
import com.epam.course.cp.dto.CategoryDTO;
import com.epam.course.cp.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category findById(Integer categoryId) {

        LOGGER.debug("findById({})", categoryId);
        return categoryDao.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Field to get category from DB"));
    }

    @Override
    public List<CategoryDTO> findAllCategoryDTOs() {

        LOGGER.debug("findAllCategoryDTOs()");
        return categoryDao.findAllCategoryDTOs().collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findCategoryDTOById(Integer categoryId) {

        LOGGER.debug("findCategoryDTOById({})", categoryId);
        return categoryDao.findCategoryDTOById(categoryId)
                .orElseThrow(() -> new RuntimeException("Field to get categoryDTO from DB"));
    }

    @Override
    public List<CategoryDTO> findSubCategoryDTOsByCategoryId(Integer categoryId) {

        LOGGER.debug("findSubCategoryDTOsByCategoryId({})", categoryId);
        return categoryDao.findSubCategoryDTOsByCategoryId(categoryId).collect(Collectors.toList());
    }

    @Override
    public Category add(Category category) {

        LOGGER.debug("add({})", category);
        return categoryDao.add(category)
                .orElseThrow(() -> new RuntimeException("Field to add category to DB"));
    }

    @Override
    public ServiceResult update(Category category) {

        LOGGER.debug("update({})", category);
        try {

            categoryDao.update(category);

            return ServiceResult.ok("Category updating", "Category category updated successfully");

        } catch (DaoException ex) {

            return ServiceResult.error("Category updating", ex.getMessage());
        }
    }

    @Override
    public ServiceResult delete(Integer categoryId) {

        LOGGER.debug("delete({})", categoryId);
        ServiceResult result;
        try {

            categoryDao.delete(categoryId);
            result = ServiceResult.ok("Category deleting", "Category deleted successfully");

            return result;

        } catch (DaoException ex) {

            result = ServiceResult.error("Category deleting", ex.getMessage());

            return result;
        }
    }

    @Override
    public List<Category> findAllPossibleParentsForId(Integer id) {

        LOGGER.debug("findAllPossibleParentsForId({})", id);

        //if it's existing category
        if (id != null) {
            //and it's top level category (i.e. parent == null)
            Optional<Category> currentCategory = categoryDao.findById(id);
            if (currentCategory.isPresent() && currentCategory.get().getParentId() == 0) {
                //and it has subcategories
                Optional<CategoryDTO> anySubcategory = categoryDao.findSubCategoryDTOsByCategoryId(id).findAny();
                if (anySubcategory.isPresent()) {
                    //then the category cannot change its parent
                    return Collections.emptyList();
                }
            }
        }
        return categoryDao.findAllPossibleParentsForId(id).collect(Collectors.toList());
    }
}
