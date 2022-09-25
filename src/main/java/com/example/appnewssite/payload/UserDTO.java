package com.example.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    @NotNull(message = "fullname must not be empty")
    private String fullName;

    @NotNull(message = "username must not be empty")
    private String username;

    @NotNull(message = "password must not be empty")
    private String password;

    @NotNull(message = "role must not be empty")
    private Integer roleId;

}
