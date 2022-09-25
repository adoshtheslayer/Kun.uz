package com.example.appnewssite.service;

import com.example.appnewssite.payload.ApiRespone;
import com.example.appnewssite.payload.UserDTO;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserService {
    public ApiRespone addUser(@Valid UserDTO registerDTO) {

        return null;
    }
}
