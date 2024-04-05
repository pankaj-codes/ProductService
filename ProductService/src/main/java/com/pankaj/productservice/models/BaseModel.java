package com.pankaj.productservice.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private Date createdAt;
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    private Date updatedAt;
}
