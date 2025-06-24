package org.kt.finalproject.result.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExecutionResultDto {
    private int no;                  // 순번
    private String versionNo;       // 실행 버전
    private String toolId;          // 도구 ID
    private String workcenterId;    // 작업장 코드
    private LocalDateTime seizeTime;    // 사용 시작 시간
    private LocalDateTime releaseTime;  // 사용 종료 시간
    private String toolList;        // 전체 도구 목록 (옵션)
    private String availables;      // 도구 사용 가능 시간 (옵션)
    private String capacity;        // 도구 용량 (옵션)
    private String scenarioName;    // 시나리오 이름
    private int toolSeizeLogId;     // ToolUsage 레코드 ID
}
