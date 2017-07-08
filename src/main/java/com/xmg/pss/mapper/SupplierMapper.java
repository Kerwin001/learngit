package com.xmg.pss.mapper;

import com.xmg.pss.domain.Supplier;
import com.xmg.pss.query.SupplierQueryObject;
import java.util.List;

public interface SupplierMapper {
	void save(Supplier entity);
	void update(Supplier entity);
	void delete(Long id);
    Supplier get(Long id);
	List<Supplier> list();
    Long getTotalCount(SupplierQueryObject qo);
    List<Supplier> pageQuery(SupplierQueryObject qo);
}