package com.xmg.pss.service;
import java.util.List;
import com.xmg.pss.domain.Brand;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.BrandQueryObject;

public interface IBrandService {
	void delete(Long id);
	void save(Brand entity);
    Brand get(Long id);
    List<Brand> list();
	void update(Brand entity);
	PageResult pageQuery(BrandQueryObject qo);
}
