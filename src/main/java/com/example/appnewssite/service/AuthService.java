package com.example.appnewssite.service;

import com.example.appnewssite.entity.User;
import com.example.appnewssite.exception.ResourceNotFountException;
import com.example.appnewssite.payload.ApiRespone;
import com.example.appnewssite.payload.RegisterDTO;
import com.example.appnewssite.repository.RoleRepository;
import com.example.appnewssite.repository.UserRepository;
import com.example.appnewssite.utils.AppConstants;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApiRespone registerUser(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getPrePassword())) {
            return new ApiRespone("Password is not same", false);
        }

        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ApiRespone("This user is already registered", false);
        }
        User user = new User(
                registerDTO.getFullName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(
                        () -> new ResourceNotFountException("role", "name", AppConstants.USER)),
                true
        );
        userRepository.save(user);
        return new ApiRespone("Successfully registered", true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
    }
}
