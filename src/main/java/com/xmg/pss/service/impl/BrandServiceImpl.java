package com.xmg.pss.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.pss.domain.Brand;
import com.xmg.pss.mapper.BrandMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.BrandQueryObject;
import com.xmg.pss.service.IBrandService;
import lombok.Setter;
public class BrandServiceImpl implements IBrandService {
	@Setter
	private BrandMapper brandMapper;
	
	public void  delete(Long id) {
		  brandMapper.delete(id);
	}

	public void save(Brand entity) {
		  brandMapper.save(entity);
	}

	public Brand get(Long id) {
		return brandMapper.get(id);
	}

	public List<Brand> list() {
		return brandMapper.list();
	}

	public void update(Brand entity) {
		  brandMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(BrandQueryObject qo) {
		Long count = brandMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<Brand> result = brandMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
}
