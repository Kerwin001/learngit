package com.xmg.pss.mapper;

import com.xmg.pss.domain.ProductStock;
import com.xmg.pss.query.ProductStockQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {
	void save(ProductStock entity);
	void update(ProductStock entity);
	void delete(Long id);
    ProductStock get(Long id);
	List<ProductStock> list();
    Long getTotalCount(ProductStockQueryObject qo);
    List<ProductStock> pageQuery(ProductStockQueryObject qo);

    ProductStock getByDepotAndProduct(@Param("depotId") Long id,@Param("productId") Long id1);
}