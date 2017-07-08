package com.xmg.pss.web.action;

import com.xmg.pss.query.ProductStockQueryObject;
import com.xmg.pss.service.IBrandService;
import com.xmg.pss.service.IDepotService;
import com.xmg.pss.service.IProductStockService;
import com.xmg.pss.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class ProductStockAction extends BasicAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IProductStockService productStockService;
    @Setter
    private IDepotService depotService;
    @Setter
    private IBrandService brandService;
    @Getter
    private ProductStockQueryObject qo = new ProductStockQueryObject();
    @RequiredPermission("库存列表")
    public String execute() {
        putContext("depots",depotService.list());
        putContext("brands",brandService.list());
        putContext("page",productStockService.pageQuery(qo));
        return LIST;
    }

}
