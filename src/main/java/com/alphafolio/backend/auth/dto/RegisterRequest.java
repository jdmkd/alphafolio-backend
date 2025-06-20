package com.alphafolio.backend.auth.dto;

import lombok.*;

@Data
public class RegisterRequest {
    private String fullName;
    private String username;
    private String email;
    private String password;
}
