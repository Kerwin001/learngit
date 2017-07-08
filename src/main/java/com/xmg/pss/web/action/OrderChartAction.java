package com.xmg.pss.web.action;

import com.xmg.pss.query.OrderChartQueryObject;
import com.xmg.pss.service.IBrandService;
import com.xmg.pss.service.IOrderChartService;
import com.xmg.pss.service.ISupplierService;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class OrderChartAction extends BasicAction {
    @Setter
    private IOrderChartService orderChartService;
    @Setter
    private ISupplierService supplierService;
    @Setter
    private IBrandService brandService;
    @Getter
    private OrderChartQueryObject qo = new OrderChartQueryObject();

    @Override
    public String execute() throws Exception {
        putContext("groupTypes", OrderChartQueryObject.typeMap);
        putContext("brands", brandService.list());
        putContext("suppliers", supplierService.list());
        putContext("list", orderChartService.query(qo));
        return LIST;
    }
}
