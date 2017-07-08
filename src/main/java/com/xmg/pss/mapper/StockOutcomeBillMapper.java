package com.xmg.pss.mapper;

import com.xmg.pss.domain.StockOutcomeBill;
import com.xmg.pss.domain.StockOutcomeBillItem;
import com.xmg.pss.query.StockOutcomeBillQueryObject;

import java.util.List;

public interface StockOutcomeBillMapper {
	void save(StockOutcomeBill entity);
	void update(StockOutcomeBill entity);
	void delete(Long id);
    StockOutcomeBill get(Long id);
	List<StockOutcomeBill> list();
    Long getTotalCount(StockOutcomeBillQueryObject qo);
    List<StockOutcomeBill> pageQuery(StockOutcomeBillQueryObject qo);

    void saveItem(StockOutcomeBillItem item);

    void deleteItemsByBillId(Long id);

    void audit(StockOutcomeBill stockOutcomeBill);
}