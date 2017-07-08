package com.xmg.pss.service.impl;

import com.xmg.pss.mapper.SaleChartMapper;
import com.xmg.pss.query.SaleChartQueryObject;
import com.xmg.pss.service.ISaleChartService;
import com.xmg.pss.vo.SaleChartVO;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class SaleChartServiceImpl implements ISaleChartService {
    @Setter
    private SaleChartMapper saleChartMapper;
    @Override
    public List<SaleChartVO> query(SaleChartQueryObject qo) {
       return saleChartMapper.query(qo);
    }
}
