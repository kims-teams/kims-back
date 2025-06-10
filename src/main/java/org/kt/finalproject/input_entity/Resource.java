package org.kt.finalproject.input_entity;

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
public class Resource {


    @Id
    private int id;

    @OneToOne
    private InputData inputData;
}
