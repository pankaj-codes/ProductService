package com.pankaj.productservice.advices;

import com.pankaj.productservice.dtos.ExceptionDto;
import lombok.Getter;
import lombok.Setter;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ProductServiceAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDto> handleNullPointerException() {
        ExceptionDto exceptionDto = new ExceptionDto("Null Pointer Exception occurred.");
        return new ResponseEntity<>(exceptionDto, HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleException() {
        ExceptionDto exceptionDto = new ExceptionDto("Exception occurred");
        return new ResponseEntity<>(exceptionDto, HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionDto> handleNoSuchElementException(){
        ExceptionDto exceptionDto = new ExceptionDto("No element exist to fetch.");
                return new ResponseEntity<>(exceptionDto, HttpStatusCode.valueOf(500));
    }
}
