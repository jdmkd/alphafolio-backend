package com.alphafolio.backend.auth.service;

import com.alphafolio.backend.auth.dto.AuthenticationRequest;
import com.alphafolio.backend.auth.dto.AuthenticationResponse;
import com.alphafolio.backend.auth.dto.RegisterRequest;
import com.alphafolio.backend.config.security.JwtService;
import com.alphafolio.backend.user.model.AccountRole;
import com.alphafolio.backend.user.model.User;
import com.alphafolio.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountRole(AccountRole.valueOf("USER")) // default role
                .active(true)
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken, "User registered successfully");
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid username or password =>"+e);
        }

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken, "Login successful");
    }
}
