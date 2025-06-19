package org.kt.finalproject.result.entity;

import jakarta.persistence.*;
import lombok.*;
import org.kt.finalproject.input.entity.Operation;
import org.kt.finalproject.input.entity.Scenario;

import java.time.LocalDateTime;

@Entity
@Table(name = "operation_execution_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationExecutionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Scenario scenario;

    @ManyToOne
    private Operation operation;

    private String workcenterId;

    private String toolId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer durationMinutes;

    private String remarks;
}