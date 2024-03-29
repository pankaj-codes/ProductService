package com.pankaj.productservice.services;

import com.pankaj.productservice.helpers.DtoConverter;
import com.pankaj.productservice.dtos.FakeStoreProductDto;
import com.pankaj.productservice.dtos.FakeStoreProductDtoWrapper;
import com.pankaj.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductService
        implements ProductService {

    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        String url = URLS.getUrl(URLS.FAKESTORE, URLS.PRODUCTS.getValue(), String.valueOf(id));
        return DtoConverter.convertFakeProductDtoToProduct(restTemplate.getForObject(url, FakeStoreProductDto.class));
    }

    @Override
    public List<Product> getAllProducts() {
        String url = URLS.getUrl(URLS.FAKESTORE, URLS.PRODUCTS.getValue());

        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(url, FakeStoreProductDto[].class);

        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : Arrays.stream(fakeStoreProductDtos).toList()) {
            productList.add(DtoConverter.convertFakeProductDtoToProduct(fakeStoreProductDto));
        }
        return productList;
    }

    /**
     * This is an incorrect way of implementation.
     *
     * @return
     */
    @Override
    public List<Product> getAllProductsV1() {
        String url = URLS.getUrl(URLS.FAKESTORE, URLS.PRODUCTS.getValue());
        ResponseEntity<FakeStoreProductDtoWrapper> response =
                restTemplate.getForEntity(url, FakeStoreProductDtoWrapper.class);
        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : response.getBody().getFakeStoreProductDtoList()) {
            productList.add(DtoConverter.convertFakeProductDtoToProduct(fakeStoreProductDto));
        }
        return productList;
    }

    @Override
    public Product createProduct(Product product) {
        String url = URLS.getUrl(URLS.FAKESTORE, URLS.PRODUCTS.getValue());
        FakeStoreProductDto fakeStoreProductDto = DtoConverter.convertProductToFakeProductDto(product);
        FakeStoreProductDto fakeStoreProductDtoResp =
                restTemplate.postForObject(url, fakeStoreProductDto, FakeStoreProductDto.class);
        Product createdProduct = DtoConverter.convertFakeProductDtoToProduct(fakeStoreProductDto);
        return createdProduct;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        String url = URLS.getUrl(URLS.FAKESTORE, URLS.PRODUCTS.getValue(), String.valueOf(id));
        FakeStoreProductDto fakeStoreProductDto = restTemplate.patchForObject(url, product, FakeStoreProductDto.class);
        return DtoConverter.convertFakeProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        String url = URLS.getUrl(URLS.FAKESTORE, URLS.PRODUCTS.getValue(), String.valueOf(id));
        RequestCallback requestCallback =
                restTemplate.httpEntityCallback(DtoConverter.convertProductToFakeProductDto(product),
                        FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor(FakeStoreProductDto.class,
                        restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute(url, HttpMethod.PUT, requestCallback,
                responseExtractor);
        return DtoConverter.convertFakeProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product deleteProduct(Long id) {
        String url = URLS.getUrl(URLS.FAKESTORE, URLS.PRODUCTS.getValue(), id.toString());
        restTemplate.delete(url);
        return null;
    }
}
