package org.kt.finalproject.domain.result.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExecutionManageDto {
    private String version;
    private String scenarioName;
    private String status;         // "Complete"
    private String duration;       // "00:10:00"
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String schedule;       // 시작시간 (또는 서버 처리시간)
    private String userId;
    private String errorMessage;   // (현재는 null)
}
