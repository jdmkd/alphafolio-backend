package com.alphafolio.backend.repository;

import com.alphafolio.backend.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Page<Skill> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

