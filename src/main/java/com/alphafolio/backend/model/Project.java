package com.alphafolio.backend.model;


import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import javax.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
    private String title;

    @Column(length = 1000)
    private String description;

    private String projectUrl;
    private String repoUrl;          // GitHub, GitLab, etc.

    private String imageUrl;         // Thumbnail or main image

    private String[] technologies;   // Use custom converter or serialize to JSON

    private boolean featured;        // If it's shown on homepage or spotlighted

    private String role;             // "Frontend Developer", "Fullstack Developer", etc.

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(length = 3000)
    private String highlights;       // Key achievements or challenges

    private String status;           // "In Progress", "Completed", "Planned"

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
