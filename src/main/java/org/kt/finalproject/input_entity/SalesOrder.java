package org.kt.finalproject.input_entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "sales_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "demand_id")
    private String demandId;

    @Column(name = "part_id")
    private String partId;      // 만약 외래키 관계 맺을거면 Part 엔티티로 교체 가능

    @Column(name = "part_name")
    private String partName;

    @Column(name = "customer_id")
    private Integer customerId; // 외래키 연결시 Customer 엔티티로 교체 가능

    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Column(name = "demand_qty")
    private Float demandQty;

    @Column(name = "uom")
    private String uom;

    @Column(name = "order_type")
    private String orderType;   // 외래키 연결시 OrderType 엔티티로 교체 가능

    @Column(name = "order_type_name")
    private String orderTypeName;

    @Column(name = "header_creation_date")
    @Temporal(TemporalType.DATE)
    private Date headerCreationDate;

    @Column(name = "site_id2")
    private String siteId2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private Target target;
}
