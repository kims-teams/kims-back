package org.kt.finalproject.input_entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String routingId;

    private String routingType;

    private String routingName;

    private String siteId;

    @ManyToOne
    @JoinColumn(name = "bop_id")
    private Bop bop;
}
