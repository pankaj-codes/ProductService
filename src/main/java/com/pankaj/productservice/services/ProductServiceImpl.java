package com.pankaj.productservice.services;

import com.pankaj.productservice.models.Category;
import com.pankaj.productservice.models.Product;
import com.pankaj.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
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
    RedisTemplate<String, Object> redisTemplate;

    public ProductServiceImpl(ProductRepository productRepository, RedisTemplate redisTemplate) {
        this.productRepository = productRepository;
        this.redisTemplate = redisTemplate;
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
            dbProduct = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + id);
            if (dbProduct != null) {
                System.out.println("Cache hit for product " + id);
                return dbProduct;
            }
            System.out.println("Cache miss for product " + id);
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                dbProduct = optionalProduct.get();
                redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + id, dbProduct);
            }
        }
        return dbProduct;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortDirection) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize, sortDirection.equalsIgnoreCase("asc") ?
                Sort.by("price").ascending() : Sort.by("price").descending()));
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
