package com.epam.course.cp.web_app.consumer;

import com.epam.course.cp.dto.ProductDTO;
import com.epam.course.cp.model.Product;
import com.epam.course.cp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ProductRestConsumer implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public ProductRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
        ClientHttpRequestInterceptor clientHttpRequestInterceptor = new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
                 return clientHttpRequestExecution.execute(httpRequest, bytes);
            }
        };
        restTemplate.setInterceptors(Arrays.asList(clientHttpRequestInterceptor));
    }

    @Override
    public List<Product> findAll() {

        LOGGER.debug("Find all products");

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);

        return (List<Product>) responseEntity.getBody();
    }

    @Override
    public Product findById(Integer productId) {
        LOGGER.debug("Find product with id = {}", productId);

        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url + "/" + productId, Product.class);

        return responseEntity.getBody();
    }

    @Override
    public List<ProductDTO> findAllProductDTOs() {
        LOGGER.debug("Find all products");

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url + "/info", List.class);

        return (List<ProductDTO>) responseEntity.getBody();
    }

    @Override
    public List<ProductDTO> findProductDTOsByCategoryId(Integer categoryId) {

        LOGGER.debug("Find product dtos by category id = {}", categoryId);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url + "/filter")
                .queryParam("id", categoryId);

        ResponseEntity<List> responseEntity = restTemplate.postForEntity(builder.toUriString(), null, List.class);

        return (List<ProductDTO>) responseEntity.getBody();
    }

    @Override
    public List<ProductDTO> findProductDTOsFromDateInterval(LocalDate dateBegin, LocalDate dateEnd) {
        LOGGER.debug("find product DTOs from date interval");


        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url + "/filter")
                .queryParam("from", dateBegin)
                .queryParam("to", dateEnd);

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(builder.toUriString(), List.class);

        return (List<ProductDTO>) responseEntity.getBody();
    }

    @Override
    public List<ProductDTO> findProductDTOsByMixedFilter(LocalDate dateBegin, LocalDate dateEnd, Integer categoryId) {
        LOGGER.debug("find product DTOs from date interval");

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url + "/filter")
                .queryParam("from", dateBegin)
                .queryParam("to", dateEnd)
                .queryParam("id", categoryId);

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(builder.toUriString(), List.class);

        return (List<ProductDTO>) responseEntity.getBody();
    }

    @Override
    public Product add(Product product) {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Integer productId) {
        LOGGER.debug("delete product with id = {}", productId);
        restTemplate.delete(url + "/" + productId);
    }
}
