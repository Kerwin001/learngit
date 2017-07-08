package com.xmg.pss.domain;

import com.xmg.pss.generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ObjectProp("采购入库单")
public class StockIncomeBill extends BasicDomain {

    public static final int NORMAL = 0;
    public static final int AUDIT = 1;
    @ObjectProp("入库单编号")
    private String sn;
    @ObjectProp("业务时间")
    private Date vdate;
    @ObjectProp("审核状态")
    private int status = NORMAL;
    @ObjectProp("入库总数量")
    private BigDecimal totalNumber;
    @ObjectProp("入库总金额")
    private BigDecimal totalAmount;
    @ObjectProp("录入时间")
    private Date inputTime;
    @ObjectProp("审核时间")
    private Date auditTime;
    @ObjectProp("录入人")
    private Employee inputUser;
    @ObjectProp("审核人")
    private Employee auditor;
    @ObjectProp("仓库")
    private Depot depot;
    private List<StockIncomeBillItem> items = new ArrayList<>();

}
