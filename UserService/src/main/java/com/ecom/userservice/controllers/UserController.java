package com.ecom.userservice.controllers;

import com.ecom.userservice.dtos.LoginRequestDto;
import com.ecom.userservice.dtos.LogoutRequestDto;
import com.ecom.userservice.dtos.SignupRequestDto;
import com.ecom.userservice.dtos.UserDto;
import com.ecom.userservice.exceptions.UserDetailsException;
import com.ecom.userservice.models.Token;
import com.ecom.userservice.models.User;
import com.ecom.userservice.services.UserService;
import com.ecom.userservice.utilities.DtoConverter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupRequestDto request){
        User user = DtoConverter.fromSignUpUserDto(request);
        User newUser = null;
        try {
           newUser = userService.signUp(user);
        } catch (UserDetailsException e) {
            int httpSattusCode = 400;
            if(e.getMessage() != null){
                if(e.getMessage().equals("User Exists")){
                    httpSattusCode = 409;
                } else if (e.getMessage().equals("User Email not passed")) {
                    httpSattusCode = 406;
                }
                else if(e.getMessage().equals("User Password not passed")){
                    httpSattusCode = 406;
                }
                else if (e.getMessage().equals("Username not passed")){
                    httpSattusCode = 406;
                }
                UserDto userDto = new UserDto();
                userDto.setMessage(e.getMessage());

                return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(httpSattusCode));
            }
        }
        if(newUser != null){
            UserDto userDto = DtoConverter.fromUser(newUser);
            return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(201));
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginRequestDto loginRequestDto){
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        return null;
    }
}
