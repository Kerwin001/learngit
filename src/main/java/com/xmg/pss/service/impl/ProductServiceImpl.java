package com.xmg.pss.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.pss.domain.Product;
import com.xmg.pss.mapper.ProductMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.ProductQueryObject;
import com.xmg.pss.service.IProductService;
import lombok.Setter;
public class ProductServiceImpl implements IProductService {
	@Setter
	private ProductMapper productMapper;
	
	public void  delete(Long id) {
		  productMapper.delete(id);
	}

	public void save(Product entity) {
		  productMapper.save(entity);
	}

	public Product get(Long id) {
		return productMapper.get(id);
	}

	public List<Product> list() {
		return productMapper.list();
	}

	public void update(Product entity) {
		  productMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(ProductQueryObject qo) {
		Long count = productMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<Product> result = productMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
}
