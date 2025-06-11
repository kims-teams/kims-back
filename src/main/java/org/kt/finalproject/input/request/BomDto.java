package org.kt.finalproject.input.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BomDto {
    private String materialCode;
    private String componentCode;
    private int quantity;
}
