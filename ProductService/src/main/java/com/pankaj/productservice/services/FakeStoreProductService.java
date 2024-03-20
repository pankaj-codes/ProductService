package com.pankaj.productservice.services;

import com.pankaj.productservice.models.Category;
import com.pankaj.productservice.models.FakeStoreProductDto;
import com.pankaj.productservice.models.FakeStoreProductDtoList;
import com.pankaj.productservice.models.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;
    UrlCreator urlCreator;

    public FakeStoreProductService(RestTemplate restTemplate, UrlCreator urlCreator) {
        this.restTemplate = restTemplate;
        this.urlCreator = urlCreator;
    }

    @Override
    public Product getProductById(Long id) {
        String url = urlCreator.getUrl(URLS.FAKESTORE, URLS.PRODUCTS.value , String.valueOf(id));
        return DtoConverter.convertFakeProductDtoToProduct(restTemplate.getForObject(url, FakeStoreProductDto.class));
    }

    @Override
    public List<Product> getAllProducts() {
        String url = urlCreator.getUrl(URLS.FAKESTORE, URLS.PRODUCTS.value);

         FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(url, FakeStoreProductDto[].class);

        // FakeStoreProductDtoList fakeStoreProductDtosList = restTemplate.getForObject(url, FakeStoreProductDtoList.class);
        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : Arrays.stream(fakeStoreProductDtos).toList()) {
            productList.add(DtoConverter.convertFakeProductDtoToProduct(fakeStoreProductDto));
        }
        return productList;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}
