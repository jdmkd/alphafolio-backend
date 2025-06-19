package com.alphafolio.backend.controller;

import com.alphafolio.backend.dto.SkillDTO;
import com.alphafolio.backend.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Update with Vercel URL in prod
public class SkillController {

    private final SkillService skillService;

    // ✅ Get all skills (with optional search and pagination)
    @GetMapping
    public List<SkillDTO> getSkills() {
        return skillService.getAllSkills();
    }

    // ✅ Create a new Skill
    @PostMapping
    public SkillDTO createSkill(@RequestBody SkillDTO skillDTO) {
        return skillService.createSkill(skillDTO);
    }

    // ✅ Get a single skill by ID
    @GetMapping("/{id}")
    public SkillDTO getSkill(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    // ✅ Update a skill
    @PutMapping("/{id}")
    public SkillDTO updateSkill(@PathVariable Long id, @RequestBody SkillDTO skillDTO) {
        return skillService.updateSkill(id, skillDTO);
    }

    // ✅ Delete a skill
    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
    }
}