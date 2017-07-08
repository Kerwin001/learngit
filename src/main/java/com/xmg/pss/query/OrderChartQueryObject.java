package com.xmg.pss.query;

import com.xmg.pss.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Setter
public class OrderChartQueryObject extends QueryObject {
    public static final Map<String,String> typeMap = new LinkedHashMap<>();
    static{
        typeMap.put("e.name","订货人员");
        typeMap.put("p.name","货品名称");
        typeMap.put("s.name","供应商");
        typeMap.put("b.name","货品品牌");
        typeMap.put("DATE_FORMAT(bill.vdate,'%Y-%m-%d')","订货日期(日)");
        typeMap.put("DATE_FORMAT(bill.vdate,'%Y-%m')","订货日期(月)");
    }
    private Date beginDate;
    private Date endDate;
    @Getter
    private String groupType="e.name";
    @Getter
    private Long supplierId=-1L;
    @Getter
    private int brandId=-1;
    @Getter
    private String keyword;//货品名称和编码
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
