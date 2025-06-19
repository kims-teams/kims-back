package org.kt.finalproject.domain.input.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigDto {
    private String configKey;
    private String configValue;
}
