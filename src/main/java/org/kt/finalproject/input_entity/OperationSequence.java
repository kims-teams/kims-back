package org.kt.finalproject.input_entity;

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

    private String operationId;

    private Integer operationSeq;

    private String operationType;

    private String operationName;

    private String routingId;

    private String siteId;

    @ManyToOne
    @JoinColumn(name = "bop_id")
    private Bop bop;
}
