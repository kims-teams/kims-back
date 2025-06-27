package org.kt.finalproject.result.DTO;
import lombok.*;
import org.kt.finalproject.domain.input.entity.Operation;
import org.kt.finalproject.domain.input.entity.Scenario;
import org.kt.finalproject.domain.input.entity.WorkcenterMap;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationWorkCenterUsageDto {
    private String operationId;
    private String workcenterId;
    private String startTime;
    private String endTime;
    private Integer durationMinute;
    private String remarks;
}