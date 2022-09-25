package com.example.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDTO {

    @NotNull(message = "fullname must not be empty")
    private String fullName;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String prePassword;

}
