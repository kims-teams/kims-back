package org.kt.finalproject.domain.input.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bop {

    @Id
    private int id;

    @OneToOne
    private Scenario scenario;
}
