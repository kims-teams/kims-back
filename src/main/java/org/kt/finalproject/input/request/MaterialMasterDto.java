package org.kt.finalproject.input.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialMasterDto {
    private String materialName;
    private String materialCode;
    private String unit;
}
