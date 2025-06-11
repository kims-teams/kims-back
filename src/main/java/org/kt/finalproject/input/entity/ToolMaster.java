package org.kt.finalproject.input.entity;

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

    @Column(name = "tool_id")
    private String toolId;

    @Column(name = "tool_type")
    private String toolType;

    @Column(name = "tool_name")
    private String toolName;

    @Column(name = "site_id")
    private String siteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    private Resource resource;
}

