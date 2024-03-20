package com.pankaj.productservice.services;

public enum URLS {
    FAKESTORE("https://fakestoreapi.com"), PRODUCTS("products");
    String value;

    URLS(String argBaseUrl){
       value = argBaseUrl;
    }

    public String getValue() {
        return value;
    }
}
