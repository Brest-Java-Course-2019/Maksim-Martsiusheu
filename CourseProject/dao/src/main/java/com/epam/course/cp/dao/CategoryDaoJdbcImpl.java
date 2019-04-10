package com.epam.course.cp.dao;

import com.epam.course.cp.dao.exception.DaoException;
import com.epam.course.cp.dao.mapper.CategoryDTOMapper;
import com.epam.course.cp.dao.mapper.CategoryMapper;
import com.epam.course.cp.dto.CategoryDTO;
import com.epam.course.cp.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class CategoryDaoJdbcImpl implements CategoryDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryDaoJdbcImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CategoryMapper categoryMapper;
    private final CategoryDTOMapper categoryDTOMapper;

    @Value("${category.selectAll}")
    private String getAllCategoriesSql;

    @Value("${category.selectParents}")
    private String getAllPossibleParentsForIdSql;

    @Value("${category.selectChildren}")
    private String getAllSybCategoriesSql;

    @Value("${category.selectById}")
    private String getCategoryByIdSql;

    @Value("${categoryDTO.selectCategoryDTOById}")
    private String getCategoryDTOByIdSql;

    @Value("${category.insert}")
    private String insertCategorySql;

    @Value("${category.update}")
    private String updateCategorySql;

    @Value("${category.delete}")
    private String deleteCategorySql;

    @Value("${categoryDTO.selectAllCategoryDTOs}")
    private String getAllCategoryDTOsSql;

    @Value("${subCategoryDTO.selectSubCategoryDTOsByCategoryId}")
    private String getSubCategoryDTOsByCategoryIdSql;

    @Autowired
    public CategoryDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                               CategoryMapper categoryMapper,
                               CategoryDTOMapper categoryDTOMapper) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.categoryMapper = categoryMapper;
        this.categoryDTOMapper = categoryDTOMapper;
    }

    @Override
    public Stream<Category> findAll() {

        LOGGER.debug("findAll()");

        List<Category> categoryList = namedParameterJdbcTemplate.query(getAllCategoriesSql, categoryMapper);
        return categoryList.stream();
    }

    @Override
    public Optional<Category> findById(Integer categoryId) {

        LOGGER.debug("findById({})", categoryId);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(CategoryMapper.CATEGORY_ID, categoryId);
        Category category = namedParameterJdbcTemplate.queryForObject(getCategoryByIdSql, namedParameters, categoryMapper);

        return Optional.ofNullable(category);
    }

    @Override
    public Stream<CategoryDTO> findAllCategoryDTOs() {

        LOGGER.debug("findAllCategoryDTOs()");

        List<CategoryDTO> categoryDTOList = namedParameterJdbcTemplate.query(getAllCategoryDTOsSql, categoryDTOMapper);
        return categoryDTOList.stream();
    }

    @Override
    public Stream<Category> findAllSubCategories() {

        LOGGER.debug("findAllSubCategories()");

        List<Category> categories = namedParameterJdbcTemplate.query(getAllSybCategoriesSql, categoryMapper);
        return categories.stream();
    }

    @Override
    public Optional<CategoryDTO> findCategoryDTOById(Integer categoryId) {

        LOGGER.debug("findCategoryDTOById()");

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(CategoryDTOMapper.CATEGORY_DTO_ID, categoryId);
        CategoryDTO categoryDTO = namedParameterJdbcTemplate
                .queryForObject(getCategoryDTOByIdSql, namedParameters, categoryDTOMapper);

        return Optional.ofNullable(categoryDTO);
    }

    @Override
    public Stream<CategoryDTO> findSubCategoryDTOsByCategoryId(Integer categoryId) {

        LOGGER.debug("findSubCategoryDTOsByCategoryId()");

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(CategoryDTOMapper.CATEGORY_DTO_ID, categoryId);
        List<CategoryDTO> categoryDTOs =
                namedParameterJdbcTemplate
                        .query(getSubCategoryDTOsByCategoryIdSql,
                                namedParameters,
                                categoryDTOMapper);

        return categoryDTOs.stream();
    }

    @Override
    public Optional<Category> add(Category category) {

        LOGGER.debug("add({})", category);

        MapSqlParameterSource namedParameters = getCategorySqlParametersSource(category);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertCategorySql, namedParameters, keyHolder);

        category.setCategoryId(keyHolder.getKey().intValue());

        return Optional.of(category);
    }

    @Override
    public void update(Category category) {

        LOGGER.debug("update({})", category);

        MapSqlParameterSource namedParameters = getCategorySqlParametersSource(category);
        namedParameters.addValue(CategoryMapper.CATEGORY_ID, category.getCategoryId());

        Optional.of(namedParameterJdbcTemplate.update(updateCategorySql, namedParameters))
                .filter(this::successfullyUpdate)
                .orElseThrow(() -> new RuntimeException("Failed to update category in DB"))
        ;

    }

    @Override
    public void delete(Integer categoryId) throws DaoException {

        LOGGER.debug("delete({})", categoryId);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(CategoryMapper.CATEGORY_ID, categoryId);
        try {
            Optional.of(namedParameterJdbcTemplate.update(deleteCategorySql, namedParameters))
                    .filter(this::successfullyUpdate)
                    .orElseThrow(() -> new RuntimeException("Failed to delete category"))
            ;
        } catch (DataIntegrityViolationException ex) {
            throw new DaoException("There ara some products in category", ex);
        }


    }

    @Override
    public Stream<Category> findAllPossibleParentsForId(Integer id) {

        LOGGER.debug("findAllPossibleParentsFromId({})", id);

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(CategoryMapper.CATEGORY_ID, id == null ? -1 : id);
        List<Category> categories = namedParameterJdbcTemplate
                .query(getAllPossibleParentsForIdSql, namedParameters, categoryMapper);

        return categories.stream();
    }

    private boolean successfullyUpdate(Integer numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    private MapSqlParameterSource getCategorySqlParametersSource(Category category) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(CategoryMapper.CATEGORY_NAME, category.getCategoryName());
        namedParameters.addValue(CategoryMapper.PARENT_ID, category.getParentId());

        return namedParameters;
    }
}
