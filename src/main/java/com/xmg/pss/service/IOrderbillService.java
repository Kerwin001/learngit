package com.xmg.pss.service;
import com.xmg.pss.domain.Orderbill;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.OrderbillQueryObject;

import java.util.List;

public interface IOrderbillService {
	void delete(Long id);
	void save(Orderbill entity);
    Orderbill get(Long id);
    List<Orderbill> list();
	void update(Orderbill entity);
	PageResult pageQuery(OrderbillQueryObject qo);

	/**
	 * 订单审核操作
	 * @param orderbill
	 */
    void audit(Orderbill orderbill);
}
