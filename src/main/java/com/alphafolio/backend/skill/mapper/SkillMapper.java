package com.alphafolio.backend.skill.mapper;

import com.alphafolio.backend.skill.dto.SkillDTO;
import com.alphafolio.backend.skill.model.Skill;

public class SkillMapper {
    public static SkillDTO toDTO(Skill skill){
        return SkillDTO.builder()
                .Id(skill.getId())
                .name(skill.getName())
                .category(skill.getCategory())
                .proficiency(skill.getProficiency())
                .iconUrl(skill.getIconUrl())
                .description(skill.getDescription())
                .featured(skill.isFeatured())
                .createdAt(skill.getCreatedAt())
                .updatedAt(skill.getUpdatedAt())
                .build();
    }

    public static Skill toEntity(SkillDTO dto){
        return Skill.builder()
                .Id(dto.getId())
                .name(dto.getName())
                .category(dto.getCategory())
                .proficiency(dto.getProficiency())
                .iconUrl(dto.getIconUrl())
                .description(dto.getDescription())
                .featured(dto.isFeatured())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}
