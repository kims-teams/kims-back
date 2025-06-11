package org.kt.finalproject.input.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolMasterDto {
    private Integer id;
    private String toolId;
    private String toolName;
    private String toolType;
    private String siteId2;
    private Integer resourceId;
}
