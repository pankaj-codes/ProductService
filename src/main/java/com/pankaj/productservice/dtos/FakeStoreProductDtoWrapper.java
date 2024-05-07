package com.pankaj.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FakeStoreProductDtoWrapper {

    List<FakeStoreProductDto> fakeStoreProductDtoList;

    public FakeStoreProductDtoWrapper(List<FakeStoreProductDto> fakeStoreProductDtoList) {
        this.fakeStoreProductDtoList = fakeStoreProductDtoList;
    }
}
