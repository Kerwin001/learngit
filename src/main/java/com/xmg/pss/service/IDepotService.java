package com.xmg.pss.service;
import java.util.List;
import com.xmg.pss.domain.Depot;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.DepotQueryObject;

public interface IDepotService {
	void delete(Long id);
	void save(Depot entity);
    Depot get(Long id);
    List<Depot> list();
	void update(Depot entity);
	PageResult pageQuery(DepotQueryObject qo);
}
