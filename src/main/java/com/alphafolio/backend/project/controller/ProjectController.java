package com.alphafolio.backend.project.controller;

import com.alphafolio.backend.config.response.ApiResponseBuilder;
import com.alphafolio.backend.project.dto.ProjectDTO;
import com.alphafolio.backend.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // For CORS (frontend connection)
public class ProjectController {

    private final ProjectService projectService;

    // Get all projects
    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        return ApiResponseBuilder.success("All projects retrieved", projectService.getAllProjects());
    }

    // Create new project
    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody @Valid ProjectDTO projectDTO) {
        return ApiResponseBuilder.success("Project created successfully", projectService.saveProject(projectDTO));
    }

    // Get project by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        return ApiResponseBuilder.success("Project fetched", projectService.getProjectById(id));
    }

    // Update project
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectDTO projectDTO) {
        return ApiResponseBuilder.success("Project updated", projectService.updateProject(id, projectDTO));
    }

    // Delete project
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ApiResponseBuilder.success("Project deleted successfully", null);
    }
}
