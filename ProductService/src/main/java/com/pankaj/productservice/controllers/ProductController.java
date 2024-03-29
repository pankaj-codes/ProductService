package com.pankaj.productservice.controllers;

import com.pankaj.productservice.models.Product;
import com.pankaj.productservice.dtos.ProductDto;
import com.pankaj.productservice.helpers.DtoConverter;
import com.pankaj.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //localhost:8080/products/10
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        ProductDto productDto = DtoConverter.convertProductToProductDto(productService.getProductById(id));
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    //localhost:8080/products
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return new ResponseEntity<>(getProductDtos(productList), HttpStatus.OK);
    }

    @GetMapping("/v1")
    public ResponseEntity<List<ProductDto>> getAllProductsV1() {
        List<Product> productList = productService.getAllProductsV1();
        return new ResponseEntity<>(getProductDtos(productList), HttpStatus.OK);
    }

    private List<ProductDto> getProductDtos(List<Product> productList) {
        List<ProductDto> productDto = new ArrayList<>();
        if (productList != null && productList.size() > 0) {
            for (Product product : productList) {
                productDto.add(DtoConverter.convertProductToProductDto(product));
            }
        }
        return productDto;
    }

    //localhost:8080/products
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product prod = null;
        if (productDto != null) {
            prod = productService.createProduct(DtoConverter.convertProductDtoToProduct(productDto));
        }
        return new ResponseEntity<>(DtoConverter.convertProductToProductDto(prod), HttpStatus.CREATED);
    }

    //localhost:8080/products/10
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product productResponse = productService.updateProduct(id, product);
        return new ResponseEntity<>(DtoConverter.convertProductToProductDto(productResponse), HttpStatus.OK);
    }

    //localhost:8080/products/10
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {

        Product productResponse = productService.replaceProduct(id, product);
        return new ResponseEntity<>(DtoConverter.convertProductToProductDto(productResponse), HttpStatus.OK);
    }

    //localhost:8080/products/10
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
