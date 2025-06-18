package com.alphafolio.backend.mapper;


import com.alphafolio.backend.dto.ProjectDTO;
import com.alphafolio.backend.model.Project;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .projectUrl(project.getProjectUrl())
                .repoUrl(project.getRepoUrl())
                .imageUrl(project.getImageUrl())
                .technologies(project.getTechnologies())
                .featured(project.isFeatured())
                .role(project.getRole())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .highlights(project.getHighlights())
                .status(project.getStatus())
                .build();
    }

    public static Project toEntity(ProjectDTO dto) {
        return Project.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .projectUrl(dto.getProjectUrl())
                .repoUrl(dto.getRepoUrl())
                .imageUrl(dto.getImageUrl())
                .technologies(dto.getTechnologies())
                .featured(dto.isFeatured())
                .role(dto.getRole())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .highlights(dto.getHighlights())
                .status(dto.getStatus())
                .build();
    }
}
