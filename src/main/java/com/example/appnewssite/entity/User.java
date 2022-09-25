package com.example.appnewssite.entity;

import com.example.appnewssite.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User extends AbstractEntity implements UserDetails {

    @Column(nullable = false)
    private String fullName;


    @Column(nullable = false, unique = true)
    private String username;


    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    private boolean enabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getPermissions().stream().map(
                permissions -> new SimpleGrantedAuthority(
                        permissions.name())).collect(Collectors.toSet());
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
