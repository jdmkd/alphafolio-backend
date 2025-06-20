package com.alphafolio.backend.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    private String token;
    private String message;
}
