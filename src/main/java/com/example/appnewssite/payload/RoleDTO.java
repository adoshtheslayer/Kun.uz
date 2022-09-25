package com.example.appnewssite.payload;

import com.example.appnewssite.entity.Role;
import com.example.appnewssite.entity.enums.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO {

    @NotBlank
    private String name;

    private String description;

    @NotEmpty
    private List<Permissions> permissions;
}
