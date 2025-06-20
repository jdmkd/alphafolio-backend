package com.alphafolio.backend.user.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "professional_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessionalRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // e.g., "Fullstack Developer", "AI/ML Engineer", "Frontend Developer", "AI Engineer"
}
