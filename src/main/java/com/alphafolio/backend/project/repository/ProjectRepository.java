package com.alphafolio.backend.project.repository;

import com.alphafolio.backend.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
