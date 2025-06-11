package org.kt.finalproject.input.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkcenterMasterDto {
    private Integer id;
    private String workcenterId;
    private String workcenterName;
    private String workcenterGroup;
    private String workcenterType;
    private String dispatcherType;
    private String workcenterState;
    private String automation;
    private String siteId;
    private String factorId;
    private Integer resourceId;
}
