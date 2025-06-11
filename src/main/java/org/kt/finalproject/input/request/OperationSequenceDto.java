package org.kt.finalproject.input.request;


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
