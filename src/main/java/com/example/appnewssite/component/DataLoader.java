package com.example.appnewssite.component;

import com.example.appnewssite.entity.Role;
import com.example.appnewssite.entity.User;
import com.example.appnewssite.repository.RoleRepository;
import com.example.appnewssite.repository.UserRepository;
import com.example.appnewssite.utils.AppConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

import static com.example.appnewssite.entity.enums.Permissions.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initialModeType;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (Objects.equals(initialModeType, "always")) {
            Role Admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.stream(values()).toList(),
                    "System owner"
            ));

            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(ADD_COMMENT,
                            DELETE_MY_COMMENT,
                            EDIT_COMMENT),
                    "System user"
            ));

            userRepository.save(new User(
                    AppConstants.ADMIN,
                    "admin",
                    passwordEncoder.encode("admin123"),
                    Admin,
                    true
            ));
            userRepository.save(new User(
                    AppConstants.USER,
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));
        }
    }
}
