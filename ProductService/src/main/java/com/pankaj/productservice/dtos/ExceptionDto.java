package com.pankaj.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class ExceptionDto {
    private String message;

    public ExceptionDto() {
    }

    public ExceptionDto(String exceptionOccurred) {
        this.message = exceptionOccurred;
    }
}
