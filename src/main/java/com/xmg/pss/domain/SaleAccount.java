package com.xmg.pss.domain;

import com.xmg.pss.web.action.BasicAction;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class SaleAccount extends BasicAction {
    private Date vdate;
    private BigDecimal number;
    private BigDecimal costPrice;
    private BigDecimal costAmount;
    private BigDecimal salePrice;
    private BigDecimal saleAmount;
    private Employee saleman;
    private Product product;
    private Client client;
}
