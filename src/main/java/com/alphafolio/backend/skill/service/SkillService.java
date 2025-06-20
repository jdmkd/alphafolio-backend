package com.alphafolio.backend.skill.service;

import com.alphafolio.backend.skill.dto.SkillDTO;
import com.alphafolio.backend.config.exception.ResourceNotFoundException;
import com.alphafolio.backend.skill.mapper.SkillMapper;
import com.alphafolio.backend.skill.model.Skill;
import com.alphafolio.backend.skill.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    // Create a new skill entry.
    public SkillDTO createSkill(SkillDTO skillDTO) {
        Skill skill = SkillMapper.toEntity(skillDTO);
        Skill saved = skillRepository.save(skill);
        return SkillMapper.toDTO(saved);
    }

    // Retrieve skills with optional search and pagination support.
    public Page<SkillDTO> getSkillsWithSearch(String search, Pageable pageable) {
        Page<Skill> skills;

        if (search == null || search.trim().isEmpty()) {
            skills = skillRepository.findAll(pageable);
        } else {
            skills = skillRepository.findByNameContainingIgnoreCase(search, pageable);
        }

        return skills.map(SkillMapper::toDTO);
    }

    // Retrieve all skills without pagination.
    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(SkillMapper::toDTO)
                .toList(); // Java 16+
    }

    // Get a specific skill by its ID.
    public SkillDTO getSkillById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        return SkillMapper.toDTO(skill);
    }
    // Update a skill by ID.
    public SkillDTO updateSkill(Long id, SkillDTO updatedDTO) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));

        skill.setName(updatedDTO.getName());
        skill.setCategory(updatedDTO.getCategory());
        skill.setProficiency(updatedDTO.getProficiency());
        skill.setIconUrl(updatedDTO.getIconUrl());
        skill.setDescription(updatedDTO.getDescription());
        skill.setFeatured(updatedDTO.isFeatured());

        return SkillMapper.toDTO(skillRepository.save(skill));
    }

    // Delete a skill by ID.
    public void deleteSkill(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        skillRepository.delete(skill);
    }
}