package com.alphafolio.backend.skill.controller;

import com.alphafolio.backend.config.response.ApiResponseBuilder;
import com.alphafolio.backend.skill.dto.SkillDTO;
import com.alphafolio.backend.skill.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Update with Vercel URL in prod
public class SkillController {

    private final SkillService skillService;

    // Get all skills
    @GetMapping
    public ResponseEntity<?> getSkills() {
        List<SkillDTO> skills = skillService.getAllSkills();
        return ApiResponseBuilder.success("Skills fetched successfully", skills);
    }

    // Create a new Skill
    @PostMapping
    public ResponseEntity<?> createSkill(@RequestBody SkillDTO skillDTO) {
        SkillDTO created = skillService.createSkill(skillDTO);
        return ApiResponseBuilder.created("Skill created successfully", created);
    }

    // Get a single skill by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSkill(@PathVariable Long id) {
        SkillDTO skill = skillService.getSkillById(id);
        return ApiResponseBuilder.success("Skill retrieved successfully", skill);
    }

    // Update a skill
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSkill(@PathVariable Long id, @RequestBody SkillDTO skillDTO) {
        SkillDTO updated = skillService.updateSkill(id, skillDTO);
        return ApiResponseBuilder.success("Skill updated successfully", updated);
    }

    // Delete a skill
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ApiResponseBuilder.success("Skill deleted successfully",null);
    }
}