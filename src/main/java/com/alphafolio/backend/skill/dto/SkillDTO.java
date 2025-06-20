package com.alphafolio.backend.skill.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillDTO {

    private Long Id;
    private String name;
    private String category;
    private int proficiency;
    private String iconUrl;
    private String description;
    private boolean featured;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}