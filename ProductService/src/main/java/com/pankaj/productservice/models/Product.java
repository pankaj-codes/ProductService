package com.pankaj.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO)//This will create a sequence table in DB
    private Long id;
    private String title;
    private Double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private String description;
    private String image;
}
