package com.xmg.pss.service.impl;

import com.xmg.pss.domain.Depot;
import com.xmg.pss.domain.SaleAccount;
import com.xmg.pss.domain.StockOutcomeBill;
import com.xmg.pss.domain.StockOutcomeBillItem;
import com.xmg.pss.mapper.StockOutcomeBillMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.StockOutcomeBillQueryObject;
import com.xmg.pss.service.IProductStockService;
import com.xmg.pss.service.ISaleAccountService;
import com.xmg.pss.service.IStockOutcomeBillService;
import com.xmg.pss.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
    @Setter
    private StockOutcomeBillMapper stockOutcomeBillMapper;
    @Setter
    private IProductStockService productStockService;
    @Setter
    private ISaleAccountService saleAccountService;

    public void delete(Long id) {
        StockOutcomeBill bill = stockOutcomeBillMapper.get(id);
        if (bill.getStatus() == StockOutcomeBill.AUDIT) {
            throw new RuntimeException("亲,该出库单已审核,不能删除!");
        }
        //删除明细
        stockOutcomeBillMapper.deleteItemsByBillId(id);
        stockOutcomeBillMapper.delete(id);
    }

    public void save(StockOutcomeBill entity) {
        try {
            //设置录入时间和录入人
            entity.setInputUser(UserContext.getUserSession());
            entity.setInputTime(new Date());
            //设置审核状态
            entity.setStatus(StockOutcomeBill.NORMAL);
            //计算总金额和总数量
            BigDecimal totalNumber = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            List<StockOutcomeBillItem> items = entity.getItems();
            for (StockOutcomeBillItem item : items) {
                BigDecimal salePrice = item.getSalePrice();
                BigDecimal number = item.getNumber();
                BigDecimal amount = number.multiply(salePrice);
                item.setAmount(amount.setScale(2, BigDecimal.ROUND_HALF_UP));
                totalNumber = totalNumber.add(number);
                totalAmount = totalAmount.add(amount);
            }

            entity.setTotalNumber(totalNumber.setScale(2, BigDecimal.ROUND_HALF_UP));
            entity.setTotalAmount(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
            stockOutcomeBillMapper.save(entity);
            //保存明细
            for (StockOutcomeBillItem item : items) {
                item.setBill(entity);
                stockOutcomeBillMapper.saveItem(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("保存失败,请正确填写出库单及明细!", e);
        }
    }

    public StockOutcomeBill get(Long id) {

        return stockOutcomeBillMapper.get(id);
    }

    public List<StockOutcomeBill> list() {

        return stockOutcomeBillMapper.list();
    }

    public void update(StockOutcomeBill entity) {
        try {
            //查看状态是否已审核
            StockOutcomeBill temp = stockOutcomeBillMapper.get(entity.getId());
            if (temp.getStatus() == StockOutcomeBill.AUDIT) {
                throw new RuntimeException("亲,该出库单已审核,不能修改!");
            }
            //删除已有的订单明细
            stockOutcomeBillMapper.deleteItemsByBillId(entity.getId());
            //计算总金额和总数量,保存新的明细
            BigDecimal totalNumber = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            List<StockOutcomeBillItem> items = entity.getItems();
            for (StockOutcomeBillItem item : items) {
                BigDecimal SalePrice = item.getSalePrice();
                BigDecimal number = item.getNumber();
                BigDecimal amount = number.multiply(SalePrice);
                item.setBill(entity);
                item.setAmount(amount.setScale(2, BigDecimal.ROUND_HALF_UP));
                stockOutcomeBillMapper.saveItem(item);
                totalNumber = totalNumber.add(number);
                totalAmount = totalAmount.add(amount);
            }
            //更新出库单
            entity.setTotalNumber(totalNumber.setScale(2, BigDecimal.ROUND_HALF_UP));
            entity.setTotalAmount(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
            stockOutcomeBillMapper.update(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("亲,更新失败,请正确填写出库单及明细!", e);
        }
    }

    @Override
    public PageResult pageQuery(StockOutcomeBillQueryObject qo) {
        Long count = stockOutcomeBillMapper.getTotalCount(qo);
        if (count <= 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        List<StockOutcomeBill> result = stockOutcomeBillMapper.pageQuery(qo);
        PageResult pageResult = new PageResult(result, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
        return pageResult;
    }

    @Override
    public void audit(StockOutcomeBill stockOutcomeBill) {
        StockOutcomeBill bill = stockOutcomeBillMapper.get(stockOutcomeBill.getId());
        if (bill.getStatus() == StockOutcomeBill.AUDIT) {
            throw new RuntimeException("该出库单已审核,不能重复操作!");
        }
        Depot depot = bill.getDepot();
        List<StockOutcomeBillItem> items = bill.getItems();
        for (StockOutcomeBillItem item : items) {
            BigDecimal costPrice = productStockService.outcome(item.getProduct(), depot, item.getNumber());
            SaleAccount saleAccount = new SaleAccount();
            saleAccount.setVdate(bill.getVdate());
            saleAccount.setNumber(item.getNumber());
            saleAccount.setCostPrice(costPrice);
            saleAccount.setCostAmount(costPrice.multiply(saleAccount.getNumber())
                    .setScale(2, BigDecimal.ROUND_HALF_UP));
            saleAccount.setSalePrice(item.getSalePrice());
            saleAccount.setSaleAmount(saleAccount.getSalePrice()
                    .multiply(saleAccount.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP));
            saleAccount.setProduct(item.getProduct());
            saleAccount.setSaleman(bill.getInputUser());
            saleAccount.setClient(bill.getClient());
            saleAccountService.save(saleAccount);
        }
        //设置审核人,审核时间,审核状态
        bill.setAuditTime(new Date());
        bill.setAuditor(UserContext.getUserSession());
        bill.setStatus(StockOutcomeBill.AUDIT);
        //更新出库单
        stockOutcomeBillMapper.audit(bill);
    }
}
