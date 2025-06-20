package com.alphafolio.backend.user.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // For login
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Public-facing profile
    @Column(nullable = false, unique = true)
    private String username; // used in URL like /@john_doe

    private String fullName;
    private String bio;
    private String profilePhotoUrl;
    private String location;
    private String website;
    private String github;
    private String linkedin;
    private String instagram;
    private String twitter;

    // Account type
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountRole accountRole; // USER or ADMIN

    // Professional roles: "Fullstack Developer", "ML Engineer", etc.
    @ManyToMany
    @JoinTable(
            name = "user_professional_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<ProfessionalRole> professionalRoles = new HashSet<>();

    private boolean active = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

