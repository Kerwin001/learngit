package com.xmg.pss.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.pss.domain.Depot;
import com.xmg.pss.mapper.DepotMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.DepotQueryObject;
import com.xmg.pss.service.IDepotService;
import lombok.Setter;
public class DepotServiceImpl implements IDepotService {
	@Setter
	private DepotMapper depotMapper;
	
	public void  delete(Long id) {
		  depotMapper.delete(id);
	}

	public void save(Depot entity) {
		  depotMapper.save(entity);
	}

	public Depot get(Long id) {
		return depotMapper.get(id);
	}

	public List<Depot> list() {
		return depotMapper.list();
	}

	public void update(Depot entity) {
		  depotMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(DepotQueryObject qo) {
		Long count = depotMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<Depot> result = depotMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
}
