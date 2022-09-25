package com.example.appnewssite.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourceNotFountException extends RuntimeException{

    private String resourceName;//role

    private String resourceField;//name

    private Object object; //USER, ADMIN
}
