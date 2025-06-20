package org.kt.finalproject.domain.input.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tool_map")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToolMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Scenario scenario;

    private String partId;

    private String toolId;

    private String partName;
    @ManyToOne
    private Resource resource;
}
