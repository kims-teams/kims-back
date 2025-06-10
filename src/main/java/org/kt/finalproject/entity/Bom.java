package org.kt.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String toSiteId;

    private String toPartId;

    private String bomCategory;

    private BigDecimal outQty;

    private String outUom;

    private String fromSiteId;

    private String fromPartId;

    private BigDecimal inQty;

    private String inUom;

    private LocalDateTime createDatetime;

    private LocalDateTime effStartDate;

    private String createBy;

    private String toPartName;

    private String fromPartName;

    private BigDecimal zseq;

    private BigDecimal bomVersion;

    private BigDecimal fromPartLevel;

    private BigDecimal toPartLevel;

    private String siteId2;

    @ManyToOne
    @JoinColumn(name = "bop_id")
    private Bop bop;
}
