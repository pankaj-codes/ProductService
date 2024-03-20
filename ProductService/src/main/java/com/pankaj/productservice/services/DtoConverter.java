package com.pankaj.productservice.services;

import com.pankaj.productservice.models.Category;
import com.pankaj.productservice.models.FakeStoreProductDto;
import com.pankaj.productservice.models.Product;
import com.pankaj.productservice.models.ProductDto;

public class DtoConverter {

    public static  ProductDto convertProductToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getDescription());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        return productDto;
    }

    public static Product convertFakeProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
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
}
