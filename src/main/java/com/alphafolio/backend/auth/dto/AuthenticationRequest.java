package com.alphafolio.backend.auth.dto;


import lombok.*;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
