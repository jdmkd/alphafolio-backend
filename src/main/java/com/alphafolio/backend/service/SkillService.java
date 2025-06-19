package com.alphafolio.backend.service;

import com.alphafolio.backend.dto.SkillDTO;
import com.alphafolio.backend.exception.ResourceNotFoundException;
import com.alphafolio.backend.mapper.SkillMapper;
import com.alphafolio.backend.model.Skill;
import com.alphafolio.backend.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillDTO createSkill(SkillDTO skillDTO) {
        Skill skill = SkillMapper.toEntity(skillDTO);
        Skill saved = skillRepository.save(skill);
        return SkillMapper.toDTO(saved);
    }

    public Page<SkillDTO> getSkillsWithSearch(String search, Pageable pageable) {
        Page<Skill> skills;

        if (search == null || search.trim().isEmpty()) {
            skills = skillRepository.findAll(pageable);
        } else {
            skills = skillRepository.findByNameContainingIgnoreCase(search, pageable);
        }

        return skills.map(SkillMapper::toDTO);
    }

    // Get all skills
    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(SkillMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SkillDTO getSkillById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        return SkillMapper.toDTO(skill);
    }

    public SkillDTO updateSkill(Long id, SkillDTO updatedDTO) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));

        skill.setName(updatedDTO.getName());
        skill.setCategory(updatedDTO.getCategory());
        skill.setProficiency(updatedDTO.getProficiency());
        skill.setIconUrl(updatedDTO.getIconUrl());
        skill.setDescription(updatedDTO.getDescription());
        skill.setFeatured(updatedDTO.isFeatured());

        Skill updated = skillRepository.save(skill);
        return SkillMapper.toDTO(updated);
    }

    public void deleteSkill(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        skillRepository.delete(skill);
    }
}