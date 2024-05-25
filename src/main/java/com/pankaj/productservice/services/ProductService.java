package com.pankaj.productservice.services;

import com.pankaj.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    //localhost:8080/products/10
    public Product getProductById(Long id);

    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortDirection);

//  public List<Product> getAllProductsV1();

    public Product createProduct(Product product);

    public Product updateProduct(Long id, Product product);

    public Product replaceProduct(Long id, Product product);

    public void deleteProduct(Long id);
}
