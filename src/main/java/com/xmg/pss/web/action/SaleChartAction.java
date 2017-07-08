package com.xmg.pss.web.action;

import com.alibaba.fastjson.JSON;
import com.xmg.pss.query.SaleChartQueryObject;
import com.xmg.pss.service.IBrandService;
import com.xmg.pss.service.IClientService;
import com.xmg.pss.service.ISaleChartService;
import com.xmg.pss.vo.SaleChartVO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class SaleChartAction extends BasicAction {
    @Setter
    private ISaleChartService saleChartService;
    @Setter
    private IClientService clientService;
    @Setter
    private IBrandService brandService;
@Getter
private SaleChartQueryObject qo=new SaleChartQueryObject();
    @Override
    public String execute() throws Exception {
        putContext("clients", clientService.list());
        putContext("brands",brandService.list());
        putContext("list",saleChartService.query(qo));
        putContext("groupTypes",SaleChartQueryObject.typeMap);
        return LIST;
    }
    public String saleChartByLine(){
        List<SaleChartVO> voList = saleChartService.query(qo);
        List<String> groupData = new ArrayList<>();
        List<BigDecimal> amounts = new ArrayList<>();
        for (SaleChartVO vo : voList) {
            groupData.add(vo.getGroupType());
            amounts.add(vo.getTotalAmount());
        }
        putContext("groupData", JSON.toJSONString(groupData));
        putContext("amounts", JSON.toJSONString(amounts));
        putContext("groupType",SaleChartQueryObject.typeMap.get(qo.getGroupType()));
        return "saleChartByLine";
    }
    public String saleChartByPie(){
        List<SaleChartVO> voList = saleChartService.query(qo);
        List<Object[]> data = new ArrayList<>();
        for (SaleChartVO vo : voList) {
            data.add(new Object[]{vo.getGroupType(),vo.getTotalAmount()});
        }
        putContext("data",JSON.toJSONString(data));
        putContext("groupType",SaleChartQueryObject.typeMap.get(qo.getGroupType()));
        return "saleChartByPie";
    }
}
