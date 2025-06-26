package org.kt.finalproject.result.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GanttTaskDto {
    private int taskId;
    private String taskName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
