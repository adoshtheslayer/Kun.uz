package com.example.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {

    @NotNull(message = "Password must not be null")
    private String username;

    @NotNull(message = "Password must not be null")
    private String password;
}
