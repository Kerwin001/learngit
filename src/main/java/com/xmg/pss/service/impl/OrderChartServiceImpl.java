package com.xmg.pss.service.impl;

import com.xmg.pss.mapper.OrderChartMapper;
import com.xmg.pss.query.OrderChartQueryObject;
import com.xmg.pss.service.IOrderChartService;
import com.xmg.pss.vo.OrderChartVO;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class OrderChartServiceImpl implements IOrderChartService{
    @Setter
    private OrderChartMapper orderChartMapper;
    @Override
    public List<OrderChartVO> query(OrderChartQueryObject qo) {
        return orderChartMapper.query(qo);
    }
}
