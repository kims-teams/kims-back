package org.kt.finalproject.result.DTO;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationToolUsageDto {

    private Integer id;
    private Integer executionLogId;
    private String toolId;
    private Integer usageTimeMinutes;
    private String remarks;
}