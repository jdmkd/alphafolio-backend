package com.alphafolio.backend.user.dto;

import com.alphafolio.backend.user.model.AccountRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long Id;

    private String email;
    private String password;
    private String username;
    private String fullName;
    private String bio;
    private String profilePhotoUrl;
    private String location;
    private String website;
    private String github;
    private String linkedin;
    private String instagram;
    private String twitter;

    private AccountRole accountRole;
    private Set<String> professionalRoles;

    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
