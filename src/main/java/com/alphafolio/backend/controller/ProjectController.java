package com.alphafolio.backend.controller;

import com.alphafolio.backend.dto.ProjectDTO;
import com.alphafolio.backend.model.Project;
import com.alphafolio.backend.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") // For CORS (frontend connection)
public class ProjectController {

    @Autowired
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<ProjectDTO> getAllProjects(){
        return projectService.getAllProjects();
    }

    @PostMapping("/projects")
    public ProjectDTO create(@RequestBody @Valid ProjectDTO projectDTO) {
        return projectService.saveProject(projectDTO);
    }

    @GetMapping("/projects/{id}")
    public ProjectDTO getById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectDTO projectDTO) {
        ProjectDTO updated = projectService.updateProject(id, projectDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/projects/{id}")
    public void delete(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
