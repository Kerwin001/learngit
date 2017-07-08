package com.xmg.pss.mapper;

import com.xmg.pss.query.SaleChartQueryObject;
import com.xmg.pss.vo.SaleChartVO;

import java.util.List;

public interface SaleChartMapper {
    List<SaleChartVO> query(SaleChartQueryObject qo);
}
