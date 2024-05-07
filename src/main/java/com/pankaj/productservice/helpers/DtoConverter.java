package com.pankaj.productservice.helpers;

import com.pankaj.productservice.models.Category;
import com.pankaj.productservice.dtos.FakeStoreProductDto;
import com.pankaj.productservice.models.Product;
import com.pankaj.productservice.dtos.ProductDto;

public class DtoConverter {

    public static ProductDto convertProductToProductDto(Product product) {
        ProductDto productDto = null;
        if (product != null) {
            productDto = new ProductDto();
            if (!product.getId().equals(0L)) {
                productDto.setId(product.getId());
            }
            productDto.setDescription(product.getDescription());
            productDto.setCategory(product.getCategory().getTitle());
            productDto.setTitle(product.getTitle());
            productDto.setPrice(product.getPrice());
            productDto.setImage(product.getImage());
        }
        return productDto;
    }

    public static Product convertProductDtoToProduct(ProductDto productDto) throws NullPointerException {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        Category category = new Category(productDto.getDescription(), null);
        product.setCategory(category);
        if (productDto.getId() != null && !productDto.getId().equals(0)) {
            product.setId(productDto.getId());
        }
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        return product;
    }

    public static Product convertFakeProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        if (fakeStoreProductDto != null && !fakeStoreProductDto.getId().equals(0L)) {
            product.setId(fakeStoreProductDto.getId());
        }
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        return product;
    }

    public static FakeStoreProductDto convertProductToFakeProductDto(Product product) throws NullPointerException {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getTitle());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImage());
        return fakeStoreProductDto;
    }
}
