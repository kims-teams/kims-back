package org.kt.finalproject.result.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GanttTaskDto {
    @JsonProperty("TaskID")
    private int taskId;
    @JsonProperty("TaskName")
    private String taskName;
    @JsonProperty("StartDate")
    private LocalDateTime startDate;
    @JsonProperty("EndDate")
    private LocalDateTime endDate;
}
