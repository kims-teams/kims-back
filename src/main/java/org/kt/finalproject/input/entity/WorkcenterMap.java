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

    @Column(name = "part_id")
    private String partId;

    @Column(name = "operation_id")
    private String operationId;

    @Column(name = "routing_group")
    private String routingGroup;

    @Column(name = "routing_version")
    private Integer routingVersion;

    @Column(name = "tact_time")
    private Double tactTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "tact_time_uom")
    private TimeUom tactTimeUom;

    @Column(name = "proc_time")
    private Double procTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "proc_time_uom")
    private TimeUom procTimeUom;

    @Column(name = "routing_id")
    private String routingId;

    @Column(name = "workcenter_id")
    private String workcenterId;

    @Column(name = "site_id")
    private String siteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    // TimeUom Enum 정의
    public enum TimeUom {
        SEC, MIN, HOUR
    }
}
