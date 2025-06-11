package org.kt.finalproject.input.request;

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
    private String portId;
    private Integer resourceId; // 참조 객체 대신 id만
}
