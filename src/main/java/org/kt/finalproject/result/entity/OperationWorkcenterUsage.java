package org.kt.finalproject.result.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "operation_workcenter_usage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationWorkcenterUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "execution_log_id")
    private OperationExecutionLog executionLog;

    private String workcenterId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String remarks;
}