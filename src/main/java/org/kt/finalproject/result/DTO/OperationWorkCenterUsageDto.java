package org.kt.finalproject.result.DTO;
import jakarta.persistence.*;
import lombok.*;
import org.kt.finalproject.result.entity.OperationExecutionLog;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationWorkCenterUsageDto {

    private Integer id;
    private Integer executionLogId;
    private String workCenterId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String remarks;
}