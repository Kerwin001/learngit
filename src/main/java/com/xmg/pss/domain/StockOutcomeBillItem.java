package com.xmg.pss.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class StockOutcomeBillItem extends BasicDomain {
    private BigDecimal salePrice;
    private BigDecimal number;
    private BigDecimal amount;
    private String remark;
    private Product product;
    private StockOutcomeBill bill;
}
