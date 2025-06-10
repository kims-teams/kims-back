package org.kt.finalproject.input_entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "priority")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "factor_id")
    private String factorId;

    @Column(name = "field")
    private String field;

    @Column(name = "order_type", length = 90)
    private String orderType;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "config_id")
    private Config config;
}
