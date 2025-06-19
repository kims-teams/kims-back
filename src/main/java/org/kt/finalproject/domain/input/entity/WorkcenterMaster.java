package org.kt.finalproject.domain.input.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "workcenter_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkcenterMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String workcenterId;

    private String workcenterName;

    private String workcenterGroup;

    private String workcenterState;

    private String automation;

    @ManyToOne
    private Scenario scenario;

    @ManyToOne
    private Resource resource;
}
