package com.xmg.pss.service.impl;

import com.xmg.pss.domain.SaleAccount;
import com.xmg.pss.mapper.SaleAccountMapper;
import com.xmg.pss.service.ISaleAccountService;
import lombok.Setter;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class SaleAccountServiceImpl implements ISaleAccountService {
    @Setter
    private SaleAccountMapper saleAccountMapper;
    @Override
    public void save(SaleAccount saleAccount) {
        saleAccountMapper.save(saleAccount);
    }
}
