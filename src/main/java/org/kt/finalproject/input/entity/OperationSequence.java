package org.kt.finalproject.input.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String routingId;

    private String operationId;

    private String operationName;

    private Integer operationSeq;

    private String operationType;

    @ManyToOne
    private Scenario scenario;

    @ManyToOne
    private Bop bop;
}
