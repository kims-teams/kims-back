package org.kt.finalproject.domain.input.DTO;

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
