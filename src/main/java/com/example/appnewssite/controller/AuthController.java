package com.example.appnewssite.controller;

import com.example.appnewssite.entity.User;
import com.example.appnewssite.payload.ApiRespone;
import com.example.appnewssite.payload.LoginDTO;
import com.example.appnewssite.payload.RegisterDTO;
import com.example.appnewssite.security.JwtProvider;
import com.example.appnewssite.service.AuthService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthController(
            @Lazy AuthService authService,
            @Lazy AuthenticationManager authenticationManager,
            @Lazy JwtProvider jwtProvider) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        ApiRespone apiRespone = authService.registerUser(registerDTO);
        return ResponseEntity.status(apiRespone.isSuccess() ? 200 : 409).body(apiRespone);
    }

    @PostMapping("/login")
    public HttpEntity<?> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        if (!authentication.isAuthenticated()) {
            return ResponseEntity.status(409).body("Username or passwor wrong");
        }

        User user = (User) authentication.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(token);
    }
}
