package com.pankaj.productservice.services;

import com.pankaj.productservice.models.Category;
import com.pankaj.productservice.models.Product;
import com.pankaj.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
    /*
    @Primary will pass this class' instance in the interface object during injection, For Eg when we use autowire how
    the code will know which implementation of the interface we want to inject. Eg - Check @ProductController -->
    productService.
     */
@Primary
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void deleteProduct(Long id) {
        if (id != null && id > 0) {
            productRepository.deleteById(id);
        }
    }

    @Override
    public Product getProductById(Long id) {
        Product dbProduct = null;
        if (id != null && id > 0) {
            Optional<Product> optionalProduct = productRepository.findById(id);
            dbProduct = optionalProduct.get();
        }
        return dbProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Product newProductCreated = null;
        if (product != null) {
            newProductCreated = productRepository.save(product);
        }
        return newProductCreated;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product updatedProduct = null;
        if (id != null && id > 0 && product != null) {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                updatedProduct = createUpdatedProduct(product, optionalProduct);
                return productRepository.save(updatedProduct);
            }
        }
        return null;
    }

    private Product createUpdatedProduct(Product product, Optional<Product> optionalProductFromDb) {
        Product updatedProduct = null;
        if (product != null && optionalProductFromDb.isPresent()) {
            updatedProduct = optionalProductFromDb.get();
            if (product.getDescription() != null) {
                updatedProduct.setDescription(product.getDescription());
            }
            if (product.getCategory() != null && product.getCategory().getTitle() != null) {
                Category category = null;
                if (updatedProduct.getCategory() == null) {
                    category = new Category(product.getCategory().getTitle(), null);
                    updatedProduct.setCategory(category);
                } else {
                    updatedProduct.getCategory().setTitle(product.getCategory().getTitle());
                }
            }
            if (product.getTitle() != null) {
                updatedProduct.setTitle(product.getTitle());
            }
            if (product.getPrice() != null && product.getPrice() >= 0) {
                updatedProduct.setPrice(product.getPrice());
            }
        }
        return updatedProduct;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        if (id != null && id > 0) {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                product.setId(optionalProduct.get().getId());
                return productRepository.save(product);
            }
        }
        return null;
    }
}
