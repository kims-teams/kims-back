package org.kt.finalproject.domain.input.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tool_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToolMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String toolId;

    private boolean toolState;

    @ManyToOne
    private Scenario scenario;

    private String toolName;

    @ManyToOne
    private Resource resource;
}

