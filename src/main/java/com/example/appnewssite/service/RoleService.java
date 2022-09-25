package com.example.appnewssite.service;

import com.example.appnewssite.entity.Role;
import com.example.appnewssite.payload.ApiRespone;
import com.example.appnewssite.payload.RoleDTO;
import com.example.appnewssite.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ApiRespone addRole(RoleDTO roleDTO) {

        if (roleRepository.existsByName(roleDTO.getName())) {
            return new ApiRespone("Role with this name already exists", false);
        }
        Role role = new Role(
                roleDTO.getName(),
                roleDTO.getPermissions(),
                roleDTO.getDescription()
        );
        roleRepository.save(role);
        return new ApiRespone("Saved", true);
    }

    public ApiRespone editRole(Long id, RoleDTO roleDTO) {

        return null;
    }
}
