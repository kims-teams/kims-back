package org.kt.finalproject.domain.input.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationSequenceDto {
    private String sequenceCode;
    private int order;
}
