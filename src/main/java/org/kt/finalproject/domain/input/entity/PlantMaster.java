package org.kt.finalproject.domain.input.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlantMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int siteId;

    private String siteName;

    @ManyToOne
    private Scenario scenario;

    @ManyToOne
    @JoinColumn(name = "bop_id")
    private Bop bop;
}
