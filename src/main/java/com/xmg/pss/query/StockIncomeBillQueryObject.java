package com.xmg.pss.query;

import com.xmg.pss.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
public class StockIncomeBillQueryObject extends QueryObject {
    private Date beginDate;
    private Date endDate;
    @Getter
    private Long depotId=-1L;
    @Getter
    private int status=-1;

    public Date getBeginDate() {
        if (beginDate != null) {
            return DateUtil.getBeginDate(beginDate);
        }
        return beginDate;
    }

    public Date getEndDate() {
        if (endDate != null) {
            return DateUtil.getEndDate(endDate);
        }
        return endDate;
    }
}
