package com.example.appnewssite.controller;

import com.example.appnewssite.aop.CheckPermission;
import com.example.appnewssite.payload.ApiRespone;
import com.example.appnewssite.payload.RoleDTO;
import com.example.appnewssite.payload.UserDTO;
import com.example.appnewssite.service.RoleService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

//    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @CheckPermission(value = "ADD_ROLE")
    @PostMapping
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDTO roleDTO) {
        ApiRespone apiRespone = roleService.addRole(roleDTO);
        return ResponseEntity.status(apiRespone.isSuccess() ? 200 : 409).body(apiRespone);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_ROLE')")
    @PutMapping("/{id}")
    public HttpEntity<?> editRole(@PathVariable Long id, RoleDTO roleDTO) {
        ApiRespone apiRespone = roleService.editRole(id, roleDTO);
        return ResponseEntity.status(apiRespone.isSuccess() ? 200 : 409).body(apiRespone);
    }
}
