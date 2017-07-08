package com.xmg.pss.mapper;

import com.xmg.pss.query.OrderChartQueryObject;
import com.xmg.pss.vo.OrderChartVO;

import java.util.List;

public interface OrderChartMapper {
	
	List<OrderChartVO> query(OrderChartQueryObject qo);

}
