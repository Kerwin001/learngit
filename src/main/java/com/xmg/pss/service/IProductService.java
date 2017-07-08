package com.xmg.pss.service;
import java.util.List;
import com.xmg.pss.domain.Product;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.ProductQueryObject;

public interface IProductService {
	void delete(Long id);
	void save(Product entity);
    Product get(Long id);
    List<Product> list();
	void update(Product entity);
	PageResult pageQuery(ProductQueryObject qo);
}
