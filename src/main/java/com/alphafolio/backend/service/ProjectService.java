package com.alphafolio.backend.service;

import com.alphafolio.backend.dto.ProjectDTO;
import com.alphafolio.backend.exception.ResourceNotFoundException;
import com.alphafolio.backend.mapper.ProjectMapper;
import com.alphafolio.backend.model.Project;
import com.alphafolio.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(ProjectMapper::toDTO)
                .orElse(null);
    }

    public ProjectDTO saveProject(ProjectDTO dto) {
        Project saved = projectRepository.save(ProjectMapper.toEntity(dto));
        return ProjectMapper.toDTO(saved);
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

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

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

}
