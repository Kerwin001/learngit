package com.xmg.pss.vo;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderChartVO {
    private String groupType;
    private BigDecimal totalNumber;
    private BigDecimal totalAmount;
}
