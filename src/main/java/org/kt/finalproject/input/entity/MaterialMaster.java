package org.kt.finalproject.input.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String partId;

    private String partType;

    private String routingId;

    private String partName;

    @ManyToOne
    private Scenario scenario;

    @ManyToOne
    @JoinColumn(name = "bop_id")
    private Bop bop;
}
