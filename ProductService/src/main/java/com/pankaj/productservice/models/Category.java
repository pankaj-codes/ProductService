package com.pankaj.productservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.management.ConstructorParameters;

@Getter
@Setter
public class Category {
    private int id;
    private String description;

    public Category() {
    }

    public Category(String description) {
        this.description = description;
    }
}
