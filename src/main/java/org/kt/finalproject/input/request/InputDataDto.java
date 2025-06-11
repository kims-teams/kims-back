package org.kt.finalproject.input.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDataDto {
    private String dataType;
    private String value;
}
