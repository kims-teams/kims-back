package org.kt.finalproject.input.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanMasterDto {
    private String plantCode;
    private String plantName;
    private String location;
}
