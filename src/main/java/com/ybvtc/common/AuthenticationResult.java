package com.ybvtc.common;

import com.ybvtc.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResult {
    private String message;
    private User user;

    public static AuthenticationResult success(User user) {
        return new AuthenticationResult("Validation successful", user);
    }

    public static AuthenticationResult userNotFound() {
        return new AuthenticationResult("User not found", null);
    }

    public static AuthenticationResult incorrectPassword() {
        return new AuthenticationResult("Incorrect password", null);
    }
}