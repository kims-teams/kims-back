package org.kt.finalproject.input.entity;
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

    private String demandId;

    private String partId;      // 만약 외래키 관계 맺을거면 Part 엔티티로 교체 가능

    private String partName;

    private Date dueDate;

    private int demandQty;

    private Date headerCreationDate;

    @ManyToOne
    private Scenario scenario;

    @ManyToOne
    private Target target;
}
