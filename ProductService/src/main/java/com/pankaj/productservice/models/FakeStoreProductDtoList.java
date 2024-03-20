package com.pankaj.productservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FakeStoreProductDtoList {

  List<FakeStoreProductDto> fakeStoreProductDtoList;

  public FakeStoreProductDtoList(List<FakeStoreProductDto> fakeStoreProductDtoList) {
    this.fakeStoreProductDtoList = fakeStoreProductDtoList;
  }
}
