package com.alphafolio.backend.project.service;

import com.alphafolio.backend.project.dto.ProjectDTO;
import com.alphafolio.backend.config.exception.ResourceNotFoundException;
import com.alphafolio.backend.project.mapper.ProjectMapper;
import com.alphafolio.backend.project.model.Project;
import com.alphafolio.backend.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    // Get all projects
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectMapper::toDTO)
                .toList();
    }

    // Get a single project by ID
    public ProjectDTO getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(ProjectMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
    }

    // Create/save a new project
    public ProjectDTO saveProject(ProjectDTO dto) {
        Project saved = projectRepository.save(ProjectMapper.toEntity(dto));
        return ProjectMapper.toDTO(saved);
    }

    // Update existing project
    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update: Project not found with ID: " + id));

        existingProject.setTitle(projectDTO.getTitle());
        existingProject.setDescription(projectDTO.getDescription());
        existingProject.setProjectUrl(projectDTO.getProjectUrl());
        existingProject.setRepoUrl(projectDTO.getRepoUrl());
        existingProject.setImageUrl(projectDTO.getImageUrl());
        existingProject.setTechnologies(projectDTO.getTechnologies());
        existingProject.setFeatured(projectDTO.isFeatured());
        existingProject.setRole(projectDTO.getRole());
        existingProject.setStartDate(projectDTO.getStartDate());
        existingProject.setEndDate(projectDTO.getEndDate());
        existingProject.setHighlights(projectDTO.getHighlights());
        existingProject.setStatus(projectDTO.getStatus());

        Project updated = projectRepository.save(existingProject);
        return ProjectMapper.toDTO(updated);
    }

    // Delete a project by ID
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete: Project not found with ID: " + id);
        }
        projectRepository.deleteById(id);
    }
}
