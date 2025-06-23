package org.kt.finalproject.domain.input.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolMapDto {
    private Integer id;
    private Integer toolSize;
    private String partName;
    private String toolId;
    private String siteId;
    private String partId;
    private Integer resourceId; // 참조 객체 대신 id만

    //추가
    private String operationId;
}
