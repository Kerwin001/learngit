package com.xmg.pss.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class SaleChartVO {
    private String groupType;
    private BigDecimal totalNumber;
    private BigDecimal totalAmount;
    private BigDecimal grossProfit;
}
