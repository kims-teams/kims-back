package org.kt.finalproject.input.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkcenterMapDto {
    private Integer id;
    private String partId;
    private String operationId;
    private String routingGroup;
    private Integer routingVersion;
    private Double tactTime;
    private String tactTimeUom; // enum값을 String으로 전달
    private Double procTime;
    private String procTimeUom;
    private String routingId;
    private String workcenterId;
    private String siteId;
    private Integer resourceId; // 참조 객체 대신 id만
}
