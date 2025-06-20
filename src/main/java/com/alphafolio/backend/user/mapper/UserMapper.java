package com.alphafolio.backend.user.mapper;


import com.alphafolio.backend.user.dto.UserDTO;
import com.alphafolio.backend.user.model.ProfessionalRole;
import com.alphafolio.backend.user.model.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(User user){
        return UserDTO.builder()
                .Id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .bio(user.getBio())
                .profilePhotoUrl(user.getProfilePhotoUrl())
                .location(user.getLocation())
                .website(user.getWebsite())
                .github(user.getGithub())
                .linkedin(user.getLinkedin())
                .instagram(user.getInstagram())
                .twitter(user.getTwitter())
                .accountRole(user.getAccountRole())
                .professionalRoles(
                        user.getProfessionalRoles()
                                .stream()
                                .map(ProfessionalRole::getName)
                                .collect(Collectors.toSet())
                )
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
