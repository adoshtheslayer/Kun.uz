package com.example.appnewssite.controller;

import com.example.appnewssite.payload.ApiRespone;
import com.example.appnewssite.payload.RegisterDTO;
import com.example.appnewssite.payload.UserDTO;
import com.example.appnewssite.service.AuthService;
import com.example.appnewssite.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        ApiRespone apiRespone = userService.addUser(userDTO);
        return ResponseEntity.status(apiRespone.isSuccess() ? 200 : 409).body(apiRespone);
    }
}
