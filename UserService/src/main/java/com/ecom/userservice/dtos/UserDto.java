package com.ecom.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends MessageDto {
    String name;
    String email;
}
