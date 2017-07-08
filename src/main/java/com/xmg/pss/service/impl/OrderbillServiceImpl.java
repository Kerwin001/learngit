package com.xmg.pss.service.impl;

import com.xmg.pss.domain.Employee;
import com.xmg.pss.domain.Orderbill;
import com.xmg.pss.domain.OrderbillItem;
import com.xmg.pss.mapper.OrderbillMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.OrderbillQueryObject;
import com.xmg.pss.service.IOrderbillService;
import com.xmg.pss.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OrderbillServiceImpl implements IOrderbillService {
    @Setter
    private OrderbillMapper orderbillMapper;

    public void delete(Long id) {
        Orderbill orderbill = orderbillMapper.get(id);
        if (orderbill.getStatus() == Orderbill.AUDIT) {
            throw new RuntimeException("亲,订单已审核,不能删除!");
        }
        orderbillMapper.deleteBillItemByBillId(id);
        orderbillMapper.delete(id);
    }

    public void save(Orderbill entity) {
        try {
            List<OrderbillItem> items = entity.getItems();
            //设置录入人和当前时间及审审核状态
            entity.setInputUser(UserContext.getUserSession());
            entity.setInputTime(new Date());
            entity.setStatus(Orderbill.NORMAL);
            //设置订单的总金额和总数量
            BigDecimal totalNumber = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            for (OrderbillItem item : items) {
                BigDecimal costPrice = item.getCostPrice();
                BigDecimal number = item.getNumber();
                BigDecimal amount = costPrice.multiply(number);
                item.setAmount(amount);
                totalNumber = totalNumber.add(number);
                totalAmount = totalAmount.add(amount);
            }
            entity.setTotalNumber(totalNumber);
            entity.setTotalAmount(totalAmount.setScale(2,BigDecimal.ROUND_HALF_UP));
            //保存订单信息
            orderbillMapper.save(entity);
            //保存订单明细
            for (OrderbillItem item : items) {
                item.setBill(entity);
                orderbillMapper.saveBillItem(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("亲,保存失败,请正确填写表单及明细",e);
        }
    }

    public Orderbill get(Long id) {

        return orderbillMapper.get(id);
    }

    public List<Orderbill> list() {

        return orderbillMapper.list();
    }

    public void update(Orderbill entity) {
        try {
            Orderbill temp = orderbillMapper.get(entity.getId());
            if (temp.getStatus() == Orderbill.AUDIT) {
                throw new RuntimeException("亲,订单已审核,不能修改!");
            }
            orderbillMapper.deleteBillItemByBillId(entity.getId());
            List<OrderbillItem> items = entity.getItems();
            entity.setInputUser(UserContext.getUserSession());
            //设置订单的总金额和总数量
            BigDecimal totalNumber = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            for (OrderbillItem item : items) {
                BigDecimal costPrice = item.getCostPrice();
                BigDecimal number = item.getNumber();
                BigDecimal amount = costPrice.multiply(number);
                item.setAmount(amount);
                item.setBill(entity);
                orderbillMapper.saveBillItem(item);
                totalNumber = totalNumber.add(number);
                totalAmount = totalAmount.add(amount);
            }
            entity.setTotalNumber(totalNumber);
            entity.setTotalAmount(totalAmount.setScale(2,BigDecimal.ROUND_HALF_UP));
            orderbillMapper.update(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("亲,更新失败,请正确填写表单及明细",e);
        }
    }

    @Override
    public PageResult pageQuery(OrderbillQueryObject qo) {
        Long count = orderbillMapper.getTotalCount(qo);
        if (count <= 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        List<Orderbill> result = orderbillMapper.pageQuery(qo);
        PageResult pageResult = new PageResult(result, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
        return pageResult;
    }

    @Override
    public void audit(Orderbill orderbill) {
        orderbill = orderbillMapper.get(orderbill.getId());
        if(orderbill.getStatus()==Orderbill.AUDIT){
            throw new RuntimeException("订单已审核,不能重复操作!");
        }
        Employee user = UserContext.getUserSession();
        orderbill.setStatus(Orderbill.AUDIT);
        orderbill.setAuditor(user);
        orderbill.setAuditTime(new Date());
        orderbillMapper.audit(orderbill);
    }
}
