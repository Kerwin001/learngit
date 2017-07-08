package com.xmg.pss.service;
import com.xmg.pss.domain.StockIncomeBill;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.StockIncomeBillQueryObject;

import java.util.List;

public interface IStockIncomeBillService {
	void delete(Long id);
	void save(StockIncomeBill entity);
    StockIncomeBill get(Long id);
    List<StockIncomeBill> list();
	void update(StockIncomeBill entity);
	PageResult pageQuery(StockIncomeBillQueryObject qo);

	/**
	 * 采购入库单的审核操作
	 * @param stockIncomeBill 要进行审核操作的入库单
	 */
    void audit(StockIncomeBill stockIncomeBill);
}
