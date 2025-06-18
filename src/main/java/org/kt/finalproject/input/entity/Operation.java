package org.kt.finalproject.input.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private String operationId;

    private String operationName;

    private int runTime;

    private String runTimeUom;

    private int operationSeq;

    private String operationType;

    private String sourcingType;

    @ManyToOne
    private Scenario scenario;

    @ManyToOne
    @JoinColumn(name = "bop_id")
    private Bop bop;
}
