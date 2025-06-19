package org.kt.finalproject.domain.input.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturingProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String routingType;

    private String routingName;

    private String routingId;

    @ManyToOne
    private Scenario scenario;

    @ManyToOne
    private Bop bop;
}
