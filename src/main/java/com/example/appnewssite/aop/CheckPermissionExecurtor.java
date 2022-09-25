package com.example.appnewssite.aop;

import com.example.appnewssite.entity.User;
import com.example.appnewssite.exception.ForbiddenException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CheckPermissionExecurtor {
    @Before(value = "@annotaion(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist = false;

        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.value())) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new ForbiddenException(checkPermission.value(), "No permission");
        }
    }
}
