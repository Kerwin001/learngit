package com.xmg.pss.service.impl;

import com.xmg.pss.domain.Depot;
import com.xmg.pss.domain.StockIncomeBill;
import com.xmg.pss.domain.StockIncomeBillItem;
import com.xmg.pss.mapper.StockIncomeBillMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.StockIncomeBillQueryObject;
import com.xmg.pss.service.IProductStockService;
import com.xmg.pss.service.IStockIncomeBillService;
import com.xmg.pss.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
    @Setter
    private StockIncomeBillMapper stockIncomeBillMapper;
    @Setter
    private IProductStockService productStockService;

    public void delete(Long id) {
        StockIncomeBill bill = stockIncomeBillMapper.get(id);
        if (bill.getStatus() == StockIncomeBill.AUDIT) {
            throw new RuntimeException("亲,订单已审核,不能删除!");
        }
        //删除明细
        stockIncomeBillMapper.deleteItemsByBillId(id);
        stockIncomeBillMapper.delete(id);
    }

    public void save(StockIncomeBill entity) {
        try {
            //设置录入时间和录入人
            entity.setInputUser(UserContext.getUserSession());
            entity.setInputTime(new Date());
            //设置审核状态
            entity.setStatus(StockIncomeBill.NORMAL);
            //计算总金额和总数量
            BigDecimal totalNumber = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            List<StockIncomeBillItem> items = entity.getItems();
            for (StockIncomeBillItem item : items) {
                BigDecimal costPrice = item.getCostPrice();
                BigDecimal number = item.getNumber();
                BigDecimal amount = number.multiply(costPrice);
                item.setAmount(amount.setScale(2, BigDecimal.ROUND_HALF_UP));
                totalNumber = totalNumber.add(number);
                totalAmount = totalAmount.add(amount);
            }

            entity.setTotalNumber(totalNumber.setScale(2, BigDecimal.ROUND_HALF_UP));
            entity.setTotalAmount(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
            stockIncomeBillMapper.save(entity);
            //保存明细
            for (StockIncomeBillItem item : items) {
                item.setBill(entity);
                stockIncomeBillMapper.saveItem(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("保存失败,请正确填写入库单及明细!", e);
        }
    }

    public StockIncomeBill get(Long id) {

        return stockIncomeBillMapper.get(id);
    }

    public List<StockIncomeBill> list() {

        return stockIncomeBillMapper.list();
    }

    public void update(StockIncomeBill entity) {
        try {
            //查看状态是否已审核
            StockIncomeBill temp = stockIncomeBillMapper.get(entity.getId());
            if (temp.getStatus() == StockIncomeBill.AUDIT) {
                throw new RuntimeException("亲,该入库单已审核,不能修改!");
            }
            //删除已有的订单明细
            stockIncomeBillMapper.deleteItemsByBillId(entity.getId());
            //计算总金额和总数量,保存新的明细
            BigDecimal totalNumber = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            List<StockIncomeBillItem> items = entity.getItems();
            for (StockIncomeBillItem item : items) {
                BigDecimal costPrice = item.getCostPrice();
                BigDecimal number = item.getNumber();
                BigDecimal amount = number.multiply(costPrice);
                item.setBill(entity);
                item.setAmount(amount.setScale(2, BigDecimal.ROUND_HALF_UP));
                stockIncomeBillMapper.saveItem(item);
                totalNumber = totalNumber.add(number);
                totalAmount = totalAmount.add(amount);
            }
            //更新入库单
            entity.setTotalNumber(totalNumber.setScale(2, BigDecimal.ROUND_HALF_UP));
            entity.setTotalAmount(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
            stockIncomeBillMapper.update(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("亲,更新失败,请正确填写入库单及明细!", e);
        }
    }

    @Override
    public PageResult pageQuery(StockIncomeBillQueryObject qo) {
        Long count = stockIncomeBillMapper.getTotalCount(qo);
        if (count <= 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        List<StockIncomeBill> result = stockIncomeBillMapper.pageQuery(qo);
        PageResult pageResult = new PageResult(result, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
        return pageResult;
    }

    @Override
    public void audit(StockIncomeBill stockIncomeBill) {
        stockIncomeBill = stockIncomeBillMapper.get(stockIncomeBill.getId());
        if (stockIncomeBill.getStatus() == StockIncomeBill.AUDIT) {
            throw new RuntimeException("该订单已审核,不能重复操作!");
        }
        Depot depot = stockIncomeBill.getDepot();
        //遍历明细,进行入库操作
        List<StockIncomeBillItem> items = stockIncomeBill.getItems();
        for (StockIncomeBillItem item : items) {
            productStockService.income(item.getProduct(), depot, item.getNumber(), item.getCostPrice());
        }
        //设置审核时间,审核人和审核状态
        stockIncomeBill.setAuditor(UserContext.getUserSession());
        stockIncomeBill.setAuditTime(new Date());
        stockIncomeBill.setStatus(StockIncomeBill.AUDIT);
        stockIncomeBillMapper.audit(stockIncomeBill);
    }
}
