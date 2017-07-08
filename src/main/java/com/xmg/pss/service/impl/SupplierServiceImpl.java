package com.xmg.pss.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.pss.domain.Supplier;
import com.xmg.pss.mapper.SupplierMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.SupplierQueryObject;
import com.xmg.pss.service.ISupplierService;
import lombok.Setter;
public class SupplierServiceImpl implements ISupplierService {
	@Setter
	private SupplierMapper supplierMapper;
	
	public void  delete(Long id) {
		  supplierMapper.delete(id);
	}

	public void save(Supplier entity) {
		  supplierMapper.save(entity);
	}

	public Supplier get(Long id) {
		return supplierMapper.get(id);
	}

	public List<Supplier> list() {
		return supplierMapper.list();
	}

	public void update(Supplier entity) {
		  supplierMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(SupplierQueryObject qo) {
		Long count = supplierMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<Supplier> result = supplierMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
}
