package com.pankaj.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private List<RoleDto> roles;
    private Boolean isEmailVerified;
}
