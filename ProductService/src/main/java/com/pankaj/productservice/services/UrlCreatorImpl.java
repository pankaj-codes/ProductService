package com.pankaj.productservice.services;

import org.springframework.stereotype.Component;

@Component
public class UrlCreatorImpl implements UrlCreator{
    @Override
    public String getUrl(URLS baseUrl, String... path) {
        StringBuilder sb = new StringBuilder(baseUrl.getValue());
        for (String pathVar : path) {
            sb.append("/").append(pathVar);
        }
        return sb.toString();
    }
}
