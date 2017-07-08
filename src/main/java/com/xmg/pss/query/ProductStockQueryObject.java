package com.xmg.pss.query;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductStockQueryObject extends QueryObject {
    private String keyword;//货品编码/货品名称
    private Long depotId = -1L;
    private Long brandId = -1L;
    private BigDecimal limitNum;//库存数量阈值(上限)
}
