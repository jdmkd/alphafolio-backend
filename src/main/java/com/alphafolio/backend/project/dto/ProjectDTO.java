package com.alphafolio.backend.project.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
    private Long id;
    private String title;
    private String description;
    private String projectUrl;
    private String repoUrl;
    private String imageUrl;
    private String[] technologies;
    private boolean featured;
    private String role;
    private LocalDate startDate;
    private LocalDate endDate;
    private String highlights;
    private String status;
}
