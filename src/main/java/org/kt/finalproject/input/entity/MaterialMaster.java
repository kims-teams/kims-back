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

    private String partType;

    private String partName;

    private String uom;

    private String routingId;

    private String siteId2;

    private String partId;

    @ManyToOne
    @JoinColumn(name = "bop_id")
    private Bop bop;
}
