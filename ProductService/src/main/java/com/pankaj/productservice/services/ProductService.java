package com.pankaj.productservice.services;

import com.pankaj.productservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {

    //localhost:8080/products/10
    public Product getProductById(Long id);

    public List<Product> getAllProducts();

    public Product createProduct(Product product);

    public Product updateProduct(Long id, Product product);

    public Product replaceProduct(Long id, Product product);

    public Product deleteProduct(Long id);
}
