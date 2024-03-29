package com.pankaj.productservice.helpers;

import com.pankaj.productservice.models.Category;
import com.pankaj.productservice.dtos.FakeStoreProductDto;
import com.pankaj.productservice.models.Product;
import com.pankaj.productservice.dtos.ProductDto;

public class DtoConverter {

    public static ProductDto convertProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getDescription());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        return productDto;
    }

    public static Product convertProductDtoToProduct(ProductDto productDto) throws NullPointerException {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        Category category = new Category(productDto.getDescription());
        product.setCategory(category);
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        return product;
    }

    public static Product convertFakeProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        return product;
    }

    public static FakeStoreProductDto convertProductToFakeProductDto(Product product) throws NullPointerException {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getDescription());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImage());
        return fakeStoreProductDto;
    }
}