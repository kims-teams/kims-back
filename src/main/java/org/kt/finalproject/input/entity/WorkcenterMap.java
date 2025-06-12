package org.kt.finalproject.input.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "workcenter_map")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkcenterMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String routingId;

    private String partId;

    private String operationId;

    private String routingGroup;

    private String workcenterId;

    private Double tactTime;

    private String tactTimeUom;

    @ManyToOne
    private Scenario scenario;

    @ManyToOne
    private Resource resource;

}
