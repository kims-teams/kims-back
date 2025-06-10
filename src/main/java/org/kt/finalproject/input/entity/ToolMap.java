package org.kt.finalproject.input.entity;
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

    @Column(name = "tool_size")
    private Integer toolSize;

    @Column(name = "part_name")
    private String partName;

    @Column(name = "tool_id")
    private String toolId;

    @Column(name = "site_id")
    private String siteId;

    @Column(name = "port_id")
    private String portId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    private Resource resource;
}
