package com.pankaj.productservice.controllers;

import com.pankaj.productservice.commons.AuthenticationCommons;
import com.pankaj.productservice.models.Product;
import com.pankaj.productservice.dtos.ProductDto;
import com.pankaj.productservice.helpers.DtoConverter;
import com.pankaj.productservice.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final AuthenticationCommons authenticationCommons;
    private final RestTemplate restTemplate;

    public ProductController(ProductService productService, AuthenticationCommons authenticationCommons, RestTemplate restTemplate) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
        this.restTemplate = restTemplate;
    }

    //localhost:8080/products/10
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        ProductDto productDto = DtoConverter.convertProductToProductDto(productService.getProductById(id));
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    //localhost:8080/products
    @GetMapping()
    public ResponseEntity<Page<ProductDto>> getAllProducts(@RequestParam("page") int pageNumber,
                                                           @RequestParam("size") int pageSize,
                                                           @RequestParam("sort") String sortDirection) {
//          Manual Authorization
//        String token = "";
//        //Validate the token using UserService.
//        UserDto userDto = authenticationCommons.validateToken(token);
//
//        if (userDto == null) {
//            //token is invalid
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }


        Page<Product> productList = productService.getAllProducts(pageNumber, pageSize, sortDirection);
        Page<ProductDto> productDtoPage = productList.map(DtoConverter::convertProductToProductDto);

        return new ResponseEntity<>(productDtoPage, HttpStatus.OK);
    }

    private List<ProductDto> getProductDtos(List<Product> productList) {
        List<ProductDto> productDto = new ArrayList<>();
        if (productList != null && !productList.isEmpty()) {
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

    @GetMapping("/eureka")
    public ResponseEntity<String> eureka() {

        // spring.application.name=UserService, instead of giving hard coded address, we will give name provided in
        // application.properties of UserService
        // ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:7020/users/eureka",
        return restTemplate.getForEntity("http://UserService/users/eureka",
                String.class);
    }
}
