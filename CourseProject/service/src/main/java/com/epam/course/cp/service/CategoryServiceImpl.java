package com.epam.course.cp.service;

import com.epam.course.cp.dao.CategoryDao;
import com.epam.course.cp.dto.CategoryDTO;
import com.epam.course.cp.dto.SubCategoryDTO;
import com.epam.course.cp.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Category> findAll() {

        LOGGER.debug("findAll()");
        return categoryDao.findAll().collect(Collectors.toList());
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
    public List<SubCategoryDTO> findSubCategoryDTOsByCategoryId(Integer categoryId) {

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
    public void update(Category category) {

        LOGGER.debug("update({})", category);
        categoryDao.update(category);
    }

    @Override
    public void delete(Integer categoryId) {

        LOGGER.debug("delete({})", categoryId);
        categoryDao.delete(categoryId);
    }
}
