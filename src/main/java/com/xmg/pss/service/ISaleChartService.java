package com.xmg.pss.service;

import com.xmg.pss.query.SaleChartQueryObject;
import com.xmg.pss.vo.SaleChartVO;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public interface ISaleChartService {
    List<SaleChartVO> query(SaleChartQueryObject qo);
}
