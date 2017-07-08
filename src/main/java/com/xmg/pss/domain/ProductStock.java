package com.xmg.pss.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductStock extends BasicDomain {
    private BigDecimal price;
    private BigDecimal storeNumber;
    private BigDecimal amount;
    private Product product;
    private Depot depot;
}
