package com.epam.course.cp.web_app.consumer;

import com.epam.course.cp.dto.CategoryDTO;
import com.epam.course.cp.dto.SubCategoryDTO;
import com.epam.course.cp.model.Category;
import com.epam.course.cp.service.CategoryService;
import com.epam.course.cp.service.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class CategoryRestConsumer implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public CategoryRestConsumer(String url, RestTemplate restTemplate) {

        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Category findById(Integer categoryId) {

        LOGGER.debug("find category by id = {}", categoryId);
        ResponseEntity<Category> responseEntity = restTemplate.getForEntity(url + "/" + categoryId, Category.class);
        return responseEntity.getBody();
    }

    @Override
    public List<CategoryDTO> findAllCategoryDTOs() {

        LOGGER.debug("find all categoryDTOs");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/info", List.class);
        return (List<CategoryDTO>) responseEntity.getBody();
    }

    @Override
    public List<SubCategoryDTO> findSubCategoryDTOsByCategoryId(Integer categoryId) {

        LOGGER.debug("find all categoryDTOs by id = {}", categoryId);
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/info/" + categoryId, List.class);
        return (List<SubCategoryDTO>) responseEntity.getBody();
    }


    @Override
    public Category add(Category category) {

        LOGGER.debug("add({})", category);
        return restTemplate.postForEntity(url, category, Category.class).getBody();
    }

    @Override
    public ServiceResult update(Category category) {

        LOGGER.debug("update({})", category);
        restTemplate.put(url + "/" + category.getCategoryId(), category);
        return null;
    }

    @Override
    public ServiceResult delete(Integer categoryId) {

        LOGGER.debug("delete category with id = {}", categoryId);
        return restTemplate.exchange(new RequestEntity(HttpMethod.DELETE, URI.create(url + "/" + categoryId)), ServiceResult.class)
                .getBody();
    }

    @Override
    public List<Category> findAllPossibleParentsForId(Integer id) {

        LOGGER.debug("findAllPossibleParentsForId({})", id);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url + "/possibleparents")
                .queryParam("id", id);

        return (List<Category>) restTemplate.getForEntity(builder.toUriString(), List.class).getBody();
    }

}
