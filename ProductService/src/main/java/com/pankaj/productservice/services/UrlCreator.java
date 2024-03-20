package com.pankaj.productservice.services;

public interface UrlCreator {
    String getUrl(URLS baseUrl, String... path);
}
