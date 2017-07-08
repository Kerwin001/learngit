package com.xmg.pss.service;

import com.xmg.pss.domain.Depot;
import com.xmg.pss.domain.Product;
import com.xmg.pss.domain.ProductStock;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.ProductStockQueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface IProductStockService {
	void delete(Long id);
	void save(ProductStock entity);
    ProductStock get(Long id);
    List<ProductStock> list();
	void update(ProductStock entity);
	PageResult pageQuery(ProductStockQueryObject qo);

	/**
	 * 所有的入库操作
	 * @param product 商品
	 * @param depot 仓库
	 * @param number 数量
	 * @param costPrice 采购价格
	 */
	void income(Product product, Depot depot, BigDecimal number, BigDecimal costPrice);

	/**
	 * 所有的出库操作
	 * @param product 商品
	 * @param depot 仓库
	 * @param number 数量
	 */
	BigDecimal outcome(Product product,Depot depot,BigDecimal number);
}
