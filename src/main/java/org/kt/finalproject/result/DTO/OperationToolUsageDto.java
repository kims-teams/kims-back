package org.kt.finalproject.result.DTO;

import lombok.*;
import org.kt.finalproject.domain.input.entity.Operation;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.kt.finalproject.domain.input.entity.ToolMap;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationToolUsageDto {
    private String operationId;
    private String toolId;
    private String startTime;
    private String endTime;
    private Integer durationMinute;
    private String remarks;
}