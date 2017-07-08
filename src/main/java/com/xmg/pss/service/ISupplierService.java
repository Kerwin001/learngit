package com.xmg.pss.service;
import java.util.List;
import com.xmg.pss.domain.Supplier;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.SupplierQueryObject;

public interface ISupplierService {
	void delete(Long id);
	void save(Supplier entity);
    Supplier get(Long id);
    List<Supplier> list();
	void update(Supplier entity);
	PageResult pageQuery(SupplierQueryObject qo);
}
