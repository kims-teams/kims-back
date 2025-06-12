package org.kt.finalproject.input.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderDto {
    private Integer id;
    private String demandId;
    private String partId;
    private String partName;
    private Integer customerId;
    private Date dueDate;
    private Float demandQty;
    private String uom;
    private String orderType;
    private String orderTypeName;
    private Date headerCreationDate;
    private String siteId;
    private Integer targetId; // target 객체 대신 id만
}
