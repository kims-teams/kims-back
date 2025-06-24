package org.kt.finalproject.result.DTO;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationToolUsageDto {

    private Integer id;
    private Integer ExecutionLogId;
    private String toolId;
    private Integer usageTimeMinutes;
    private String remarks;
}