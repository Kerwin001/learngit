package com.xmg.pss.query;

import com.xmg.pss.util.DateUtil;
import lombok.Getter;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class SaleChartQueryObject {
    public static final Map<String,String> typeMap = new LinkedHashMap<>();
    static{
        typeMap.put("e.name","销售人员");
        typeMap.put("p.name","货品名称");
        typeMap.put("c.name","客户");
        typeMap.put("b.name","货品品牌");
        typeMap.put("DATE_FORMAT(sa.vdate,'%Y-%m-%d')","订货日期(日)");
        typeMap.put("DATE_FORMAT(sa.vdate,'%Y-%m')","订货日期(月)");
    }
    private Date beginDate;
    private Date endDate;
    @Getter
    private String groupType="e.name";
    @Getter
    private Long clientId=-1L;
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
