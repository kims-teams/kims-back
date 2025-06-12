package org.kt.finalproject.input.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String toPartId;

    private BigDecimal outQty;

    private String outUom;

    private String fromPartId;

    private BigDecimal inQty;

    private String inUom;

    private String toPartName;

    private BigDecimal zseq;

    @ManyToOne
    private Scenario scenario;

    private BigDecimal fromPartLevel;

    private BigDecimal toPartLevel;

    @ManyToOne
    private Bop bop;
}
