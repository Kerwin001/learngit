package com.xmg.pss.service;
import com.xmg.pss.domain.StockOutcomeBill;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.StockOutcomeBillQueryObject;

import java.util.List;

public interface IStockOutcomeBillService {
	void delete(Long id);
	void save(StockOutcomeBill entity);
    StockOutcomeBill get(Long id);
    List<StockOutcomeBill> list();
	void update(StockOutcomeBill entity);
	PageResult pageQuery(StockOutcomeBillQueryObject qo);

	/**
	 * 采购入库单的审核操作
	 * @param stockOutcomeBill 要进行审核操作的入库单
	 */
    void audit(StockOutcomeBill stockOutcomeBill);
}
