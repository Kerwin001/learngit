package com.xmg.pss.service;

import com.xmg.pss.query.OrderChartQueryObject;
import com.xmg.pss.vo.OrderChartVO;

import java.util.List;

public interface IOrderChartService {
    List<OrderChartVO> query(OrderChartQueryObject qo);
}
