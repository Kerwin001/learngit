package com.xmg.pss.mapper;

import com.xmg.pss.domain.Depot;
import com.xmg.pss.query.DepotQueryObject;
import java.util.List;

public interface DepotMapper {
	void save(Depot entity);
	void update(Depot entity);
	void delete(Long id);
    Depot get(Long id);
	List<Depot> list();
    Long getTotalCount(DepotQueryObject qo);
    List<Depot> pageQuery(DepotQueryObject qo);
}