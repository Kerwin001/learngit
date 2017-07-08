package com.xmg.pss.mapper;

import com.xmg.pss.domain.Brand;
import com.xmg.pss.query.BrandQueryObject;
import java.util.List;

public interface BrandMapper {
	void save(Brand entity);
	void update(Brand entity);
	void delete(Long id);
    Brand get(Long id);
	List<Brand> list();
    Long getTotalCount(BrandQueryObject qo);
    List<Brand> pageQuery(BrandQueryObject qo);
}