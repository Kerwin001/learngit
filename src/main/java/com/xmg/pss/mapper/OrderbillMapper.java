package com.xmg.pss.mapper;

import com.xmg.pss.domain.Orderbill;
import com.xmg.pss.domain.OrderbillItem;
import com.xmg.pss.query.OrderbillQueryObject;
import java.util.List;

public interface OrderbillMapper {
	void save(Orderbill entity);
	void update(Orderbill entity);
	void delete(Long id);
    Orderbill get(Long id);
	List<Orderbill> list();
    Long getTotalCount(OrderbillQueryObject qo);
    List<Orderbill> pageQuery(OrderbillQueryObject qo);

	void saveBillItem(OrderbillItem item);

    void deleteBillItemByBillId(Long billId);

    /**
     * 跟新订单状态为审核
     * @param orderbill
     */
    void audit(Orderbill orderbill);
}