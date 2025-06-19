package org.kt.finalproject.result.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "operation_tool_usage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationToolUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "execution_log_id")
    private OperationExecutionLog executionLog;

    private String toolId;

    private Integer usageTimeMinutes;

    private String remarks;
}