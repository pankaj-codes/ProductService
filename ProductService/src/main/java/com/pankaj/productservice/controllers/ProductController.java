package com.pankaj.productservice.controllers;

import com.pankaj.productservice.models.Product;
import com.pankaj.productservice.models.ProductDto;
import com.pankaj.productservice.services.DtoConverter;
import com.pankaj.productservice.services.ProductService;
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
    public ProductDto getProductById(@PathVariable("id") Long id){
       ProductDto productDto = DtoConverter.convertProductToProductDto(productService.getProductById(id));
       return productDto;
    }

    //localhost:8080/products
    @GetMapping("/")
    public List<ProductDto> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        List<ProductDto> productDtoResponse = new ArrayList<>();
        if(productList != null && productList.size() > 0){
            for (Product product : productList) {
                productDtoResponse.add(DtoConverter.convertProductToProductDto(product));
            }
        }
        return productDtoResponse;
    }

    //localhost:8080/products
    @PostMapping
    public ProductDto createProduct(@RequestBody Product product){
        return new ProductDto();
    }

    //localhost:8080/products/10
    @PatchMapping("/{id}")
    public ProductDto updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return new ProductDto();
    }

    //localhost:8080/products/10
    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return new ProductDto();
    }

    //localhost:8080/products/10
    @DeleteMapping("/{id}")
    public ProductDto deleteProduct(@PathVariable("id") Long id){
        return new ProductDto();
    }
}
