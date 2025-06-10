package org.kt.finalproject.input_entity;
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

    @Column(name = "workcenter_id")
    private String workcenterId;

    @Column(name = "workcenter_name")
    private String workcenterName;

    @Column(name = "workcenter_group")
    private String workcenterGroup;

    @Column(name = "workcenter_type")
    private String workcenterType;

    @Column(name = "dispatcher_type")
    private String dispatcherType;

    @Column(name = "workcenter_state")
    private String workcenterState;

    @Column(name = "automation")
    private String automation;

    @Column(name = "site_id")
    private String siteId;

    @Column(name = "factor_id")
    private String factorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    private Resource resource;
}
