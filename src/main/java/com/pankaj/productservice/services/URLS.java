package com.pankaj.productservice.services;

public enum URLS {
    FAKESTORE("https://fakestoreapi.com"), PRODUCTS("products");
    private String value;

    URLS(String argBaseUrl) {
        value = argBaseUrl;
    }

    public static String getUrl(URLS baseUrl, String... path) {
        StringBuilder sb = new StringBuilder(baseUrl.getValue());
        for (String pathVar : path) {
            sb.append("/").append(pathVar);
        }
        return sb.toString();
    }

    public String getValue() {
        return value;
    }
}
