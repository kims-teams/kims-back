package org.kt.finalproject.result.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "operation_material_usage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationMaterialUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "execution_log_id")
    private OperationExecutionLog executionLog;

    private String materialId;

    private Double usedQuantity;

    private String uom;

    private String remarks;
}