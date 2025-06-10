package org.kt.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer runTime;

    private String runTimeUom;

    private String waitTimeUom;

    private String transferTimeUom;

    private String sourcingType;

    private String operationId;

    private String operationName;

    private String operationType;

    private Integer operationSeq;

    private String siteId2;

    @ManyToOne
    @JoinColumn(name = "bop_id")
    private Bop bop;
}
