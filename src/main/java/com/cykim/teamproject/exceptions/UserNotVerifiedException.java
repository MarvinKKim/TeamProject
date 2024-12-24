package com.cykim.teamproject.exceptions;

import com.cykim.teamproject.entities.UserEntity;
import org.springframework.security.core.AuthenticationException;

public class UserNotVerifiedException extends AuthenticationException {
    private final UserEntity user;

    public UserNotVerifiedException(String msg, UserEntity user) {
        super(msg);
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
}
