package com.xmg.pss.service.impl;

import com.xmg.pss.domain.Depot;
import com.xmg.pss.domain.Product;
import com.xmg.pss.domain.ProductStock;
import com.xmg.pss.mapper.ProductStockMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.ProductStockQueryObject;
import com.xmg.pss.service.IProductStockService;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ProductStockServiceImpl implements IProductStockService {
    @Setter
    private ProductStockMapper productStockMapper;

    public void delete(Long id) {
        productStockMapper.delete(id);
    }

    public void save(ProductStock entity) {
        productStockMapper.save(entity);
    }

    public ProductStock get(Long id) {
        return productStockMapper.get(id);
    }

    public List<ProductStock> list() {
        return productStockMapper.list();
    }

    public void update(ProductStock entity) {
        productStockMapper.update(entity);
    }

    @Override
    public PageResult pageQuery(ProductStockQueryObject qo) {
        Long count = productStockMapper.getTotalCount(qo);
        if (count <= 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        List<ProductStock> result = productStockMapper.pageQuery(qo);
        PageResult pageResult = new PageResult(result, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
        return pageResult;
    }

    @Override
    public void income(Product product, Depot depot,BigDecimal number,BigDecimal costPrice) {
        //根据仓库和商品查询是否已存在
        ProductStock ps = productStockMapper.getByDepotAndProduct(depot.getId(), product.getId());
        if (ps == null) {
            //库存中没有,直接保存信息
            ps = new ProductStock();
            ps.setPrice(costPrice);
            ps.setStoreNumber(number);
            ps.setAmount(costPrice.multiply(number).setScale(2,BigDecimal.ROUND_HALF_UP));
            ps.setProduct(product);
            ps.setDepot(depot);
            productStockMapper.save(ps);
        } else {
            //库存中已存在,根据库存更新信息
            ps.setAmount(ps.getAmount().add(costPrice.multiply(number)).setScale(2, BigDecimal.ROUND_HALF_UP));
            ps.setStoreNumber(ps.getStoreNumber().add(number).setScale(2,BigDecimal.ROUND_HALF_UP));
            ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(),2,BigDecimal.ROUND_HALF_UP));
            productStockMapper.update(ps);
        }
    }

    @Override
    public BigDecimal outcome(Product product,Depot depot,BigDecimal number) {
        ProductStock ps = productStockMapper.getByDepotAndProduct(depot.getId(), product.getId());
        //计算商品出库总数量,判断库存是否足够
        if (ps == null) {
            throw new RuntimeException("商品:"+product.getName() + "在" + depot.getName() + "没有库存!");
        }
        if (ps.getStoreNumber().compareTo(number) < 0) {
            throw new RuntimeException("商品:"+product.getName() + "在" + depot.getName() + "库存不足,仅剩:" + ps.getStoreNumber());
        }
        //更新库存
        ps.setStoreNumber(ps.getStoreNumber().subtract(number));
        ps.setAmount(ps.getStoreNumber().multiply(ps.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
        productStockMapper.update(ps);
        return ps.getPrice();
    }

}
