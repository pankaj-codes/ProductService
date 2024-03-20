package com.pankaj.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
