package org.kt.finalproject.entity;

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

    private String siteId;

    private String siteName;

    @ManyToOne
    @JoinColumn(name = "bop_id")
    private Bop bop;
}
